create table livros
(
    id               bigserial not null,
    titulo           varchar(255),
    idioma           varchar(2),
    numero_downloads int,
    constraint pk_livros primary key (id)
);