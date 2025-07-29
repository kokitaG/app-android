package com.example.libros.Models;

import java.io.Serializable;

public class libros implements Serializable { // Implementar Serializable

    private String titulo;
    private String autor;
    private String genero;
    private int ano;
    private String sinopsis;

    public libros(String titulo, String autor, String genero, int ano, String sinopsis) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.ano = ano;
        this.sinopsis = sinopsis;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}