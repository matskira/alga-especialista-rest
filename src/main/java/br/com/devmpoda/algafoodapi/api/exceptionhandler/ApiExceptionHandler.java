package br.com.devmpoda.algafoodapi.api.exceptionhandler;

import br.com.devmpoda.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.devmpoda.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.devmpoda.algafoodapi.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido";

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PROPRIEDADES_INVALIDAS;

        Throwable cause = ExceptionUtils.getRootCause(rootCause);

        if (cause instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedPropertyException((UnrecognizedPropertyException) cause, headers, status, request);
        }

        if (cause instanceof IgnoredPropertyException) {
            return handleIgnoredPropertyException((IgnoredPropertyException) cause, headers, status, request);
        }

        String detail = String.format("Propriedade inválida");
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(rootCause, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException cause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PROPRIEDADES_INVALIDAS;

        String path = joinPath(cause.getPath());

        String detail = String.format("Propriedade '%s' não está habilitada como propriedade de corpo. Corrija ou remova essa propriedade e tente novamente.", path);
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(cause, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException cause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PROPRIEDADES_INVALIDAS;

        String path = joinPath(cause.getPath());

        String detail = String.format("Propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(cause, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException rootCause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String path = rootCause.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        String detail = String.format("A propriedade '%s' recebeu o valor '%s'," +
                        "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", path,
                rootCause.getValue(), rootCause.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(rootCause, problem, headers, status, request);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = e.getMessage();

        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADO;
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return this.handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String detail = e.getMessage();

        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADO;
        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return this.handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handlerNegocioException(NegocioException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = e.getMessage();

        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        Problem problem = createProblemBuilder(status, problemType, detail).build();
        return this.handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType type, String detail) {
        return Problem.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }
}
