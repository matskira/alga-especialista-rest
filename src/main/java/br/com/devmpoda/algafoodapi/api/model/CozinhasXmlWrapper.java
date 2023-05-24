package br.com.devmpoda.algafoodapi.api.model;


import br.com.devmpoda.algafoodapi.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasXmlWrapper {

    @NonNull
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cozinha> cozinhas;


}
