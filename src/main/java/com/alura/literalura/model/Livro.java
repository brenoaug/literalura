package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Livro")
@Table(name = "livros")
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @ManyToMany
    @JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "id_livro"), inverseJoinColumns = @JoinColumn(name = "id_autor"))
    private List<Autor> autores = new ArrayList<>();
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "numero_downloads")
    private int numeroDownloads;

}
