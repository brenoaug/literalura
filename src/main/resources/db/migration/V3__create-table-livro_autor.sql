create table livro_autor
(
    id_livro bigint not null,
    id_autor bigint not null,
    constraint pk_livro_autor primary key (id_livro, id_autor),
    constraint fk_livro foreign key (id_livro) references livros (id) on delete cascade,
    constraint fk_autor foreign key (id_autor) references autores (id) on delete cascade
);