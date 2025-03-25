package com.nishanth.OnlineBookStore.controller;

import com.nishanth.OnlineBookStore.model.Book;
import com.nishanth.OnlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// A BookController class being created for handling HTTP requests
@RestController
@RequestMapping("/books")
public class BookController {

    //An Autowired annotation for supporting automatic dependency injection
    @Autowired
    private BookService bookService;

    // A PostMapping annotation for addBook method to add the respective book in the bookstore
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        Book bookObj = bookService.addBook(book);
        return new ResponseEntity<>(bookObj,HttpStatus.CREATED);
    }

    // A GetMapping annotation for getAllBooks method to retrieve all the books from bookstore
    @GetMapping
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }

    // A GetMapping annotation for getBookById method to retrieve the respective book from the bookstore
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
    {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // A PutMapping annotation for updateBookById method to update the book based on the respective id of the  book in the bookstore
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id,@RequestBody Book newBookData)
    {
        try {
            Book updatedBook = bookService.updateBook(id, newBookData);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // A DeleteMapping annotation for deleteBookById method to delete a book based on the id of the book in bookstore
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id)
    {
        if (bookService.getBookById(id).isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
