package com.getir.alperdogan.project.readingisgood.Service;

import com.getir.alperdogan.project.readingisgood.DAO.BookDAO;
import com.getir.alperdogan.project.readingisgood.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public Optional<Book> findBook(Integer bookId)
    {
        return bookDAO.findBook(bookId);
    }

    public Book registerBook(Book book) {
        return bookDAO.insertBook(book);
    }

    public Book updateBook(Book book)
    {
        return bookDAO.updateBook(book);
    }

}
