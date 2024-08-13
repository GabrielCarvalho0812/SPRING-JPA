package com.bookstore.Jpa.services;

import com.bookstore.Jpa.dto.BookRecordDto;
import com.bookstore.Jpa.models.BookModel;
import com.bookstore.Jpa.models.ReviewModel;
import com.bookstore.Jpa.repositories.AuhtorRepository;
import com.bookstore.Jpa.repositories.BookRepository;
import com.bookstore.Jpa.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    // CRIAR UM PONTO DE INJEÇÃO PARA CADA UM

    private final BookRepository bookRepository;
    private final AuhtorRepository auhtorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuhtorRepository auhtorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.auhtorRepository = auhtorRepository;
        this.publisherRepository = publisherRepository;
    }



    //@Transactional porque dentro do metódo save vai ter varias execuções na base de dados
    // e se falhar umas destas transações com o @transactional garante um roubek ou seja
    //dessa forma não ira salvar um review sem existir um book por exemplo
    //se der erro em algumas das partes teremos um roubek e volta tudo do começo
    //assim preservando os estados de todos esses dados
    @Transactional
    //MÉTODO PARA SALVAR
    public BookModel saveBook(BookRecordDto bookRecordDto){
        BookModel book = new BookModel();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(auhtorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

     //MÉTODO PARA LISTA TODOS OS LIVROS
    public List<BookModel> getallBooks(){
        return bookRepository.findAll();
    }


    //MÉTODO PARA DELETAR
    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }






}
