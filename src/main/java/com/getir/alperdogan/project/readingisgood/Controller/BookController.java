package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.Book;
import com.getir.alperdogan.project.readingisgood.Exception.BookNotFoundException;
import com.getir.alperdogan.project.readingisgood.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book registerBook(@RequestBody Book book) {
        return bookService.registerBook(book);
    }


    @PutMapping("updateBook/{bookId}/{stockNumber}")
    public Book updateBook(@PathVariable Integer bookId, @PathVariable Integer stockNumber) throws BookNotFoundException {
        Book book = bookService.findBook(bookId).orElseThrow(BookNotFoundException::new);
        book.setStockNumber(stockNumber);
        return bookService.updateBook(book);
    }

}


