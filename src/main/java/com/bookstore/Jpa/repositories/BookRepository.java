package com.bookstore.Jpa.repositories;

import com.bookstore.Jpa.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID> { //passar a entidade e o tipo de indentificador

    BookModel findBookModelByTitle(String title);


    //QUANDO PRECISAR DE CONSULTAS UM POUCO MAIS COMPLEXAS
    @Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true) //@query nativas
    List<BookModel> findBookByPublisher(@Param("id") UUID id);
}
