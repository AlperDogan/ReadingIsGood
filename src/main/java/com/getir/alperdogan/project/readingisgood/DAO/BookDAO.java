package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDAO {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findBook(Integer bookId)
    {
        return bookRepository.findById(bookId);
    }

    public Book insertBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }


}
