package com.bookstore.Jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PUBLISHER") //NOME DA TABELA NO BANCO
public class PublisherModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //GERAÇÃO AUTOMATICA DO ID
    private UUID id;

    @Column(nullable = false,unique = true) //TITULO TEM QUE SER OBRIGATORIO ,TITULO TEM QUE SER UNICO
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY) //@OneToMany UMA EDITORA ELA PODE TER VARIOS LIVROS
    private Set<BookModel> books = new HashSet<>();             //FetchType.LAZY = PARA FAZER CARREGAMENTO LENTO



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }


}
