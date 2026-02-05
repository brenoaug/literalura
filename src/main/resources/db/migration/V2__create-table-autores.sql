create table autores
(
    id              bigserial not null,
    nome            varchar(255),
    ano_nascimento  int,
    ano_falecimento int,
    constraint pk_autores primary key (id)
);