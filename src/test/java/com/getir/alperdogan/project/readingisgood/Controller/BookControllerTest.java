package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.Book;
import com.getir.alperdogan.project.readingisgood.Exception.BookNotFoundException;
import com.getir.alperdogan.project.readingisgood.Service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

    @InjectMocks
    private BookController mockBookController;

    @Mock
    private BookService mockBookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerBook() {
        Book book = new Book(
                1,
                "Intibah",
                "Namık Kemal",
                "Roman",
                22.0,
                5);
        when(mockBookService.registerBook(ArgumentMatchers.any(Book.class))).thenReturn(book);

        Book foundBook = mockBookController.registerBook(book);

        Assertions.assertThat(foundBook.getBookId()).isEqualTo(1);
    }

    @Test
    public void updateBook() throws BookNotFoundException {
        Book book = new Book(
                1,
                "Intibah",
                "Namık Kemal",
                "Roman",
                22.0,
                5);

        when(mockBookService.findBook(1)).thenReturn(Optional.of(book));
        when(mockBookService.updateBook(book)).thenReturn(book);

        Book updatedBook = mockBookController.updateBook(1,10);
                Assertions.assertThat(updatedBook.getStockNumber()).isEqualTo(10);
    }
}