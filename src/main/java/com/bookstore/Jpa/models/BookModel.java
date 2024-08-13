package com.bookstore.Jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_BOOK") //NOME DA TABELA NO BANCO
public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id    //CHAVE PRIMARIA DA TEBELA
    @GeneratedValue(strategy =  GenerationType.AUTO)   //GERAÇÃO AUTOMATICA DO ID
    private UUID id;

    @Column(nullable = false,unique = true)  // TITULO TEM QUE SER OBRIGATORIO ,TITULO TEM QUE SER UNICO
    private String title;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")  // CHAVE ESTRANGEIRA PARA FAZER O RELACIONAMENTO
    private PublisherModel publisher;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY) //esse carregamento(lento) nao vai trazer os detalhes tanto de autores quanto de editoras
    @JoinTable(
            name = "tb_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorModel> authors = new HashSet<>();


    @OneToOne(mappedBy = "book",cascade = CascadeType.ALL)  //quando criar ou deletar um book automaticamente  cira ou exclui para review !tomar cuidado ao usar
    private ReviewModel review;






    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }

    public ReviewModel getReview() {
        return review;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }
}
