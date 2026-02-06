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

    public Livro(String titulo, List<Autor> autores, String idioma, int numeroDownloads) {
        this.titulo = titulo;
        this.autores = autores;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    public Livro() {
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return """
                Título: %s;
                """.formatted(titulo);
    }
}
