INSERT INTO cozinha (id,nome) values (1,'Tailandesa');
INSERT INTO cozinha (id,nome) values (2,'Indiana');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Señior Dutra', 9.90,1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Ampola Otiep', 6.90,2);

INSERT INTO forma_pagamento (id,descricao) values (1,'Dinheiro');
INSERT INTO forma_pagamento (id,descricao) values (2,'Cartão de crédito');

INSERT INTO permissao (id,nome,descricao) values (1,'CONSULTAR_COZINHAS','Permite consultar cozinhas');
INSERT INTO permissao (id,nome,descricao) values (2,'GERENCIAR_COZINHAS','Permite gerenciar cozinhas');

INSERT INTO estado (id,nome) values (1,'São Paulo');
INSERT INTO estado (id,nome) values (2,'Minas Gerais');


INSERT INTO cidade (id,nome,estado_id) values (1,'São Paulo',1);
INSERT INTO cidade (id,nome,estado_id) values (2,'Campinas',1);


INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (2, 1), (2, 2);