package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Autor")
@Table(name = "autores")
public class Autor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros = new ArrayList<>();
    @Column(name = "nome")
    private String nome;
    @Column(name = "ano_nascimento")
    private int anoNascimento;
    @Column(name = "ano_falecimento")
    private int anoFalecimento;

    public Autor(String nome, int anoNascimento, int anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public Autor() {

    }
}
