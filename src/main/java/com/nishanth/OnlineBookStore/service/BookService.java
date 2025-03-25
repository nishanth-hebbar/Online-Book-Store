package com.nishanth.OnlineBookStore.service;

import com.nishanth.OnlineBookStore.model.Book;
import com.nishanth.OnlineBookStore.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//A class being created called BookService
@Service
public class BookService {

    //An Autowired annotation for supporting automatic dependency injection
    @Autowired
    private BookRepository bookRepository;

    //A method to add new book in the bookstore
    public Book addBook(Book book)
    {
        return bookRepository.save(book);
    }

    // A method to retrieve all the books from the bookstore
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // A method to retrieve the specific book based on the id of the book
    public Optional<Book> getBookById(Long id)
    {
        return bookRepository.findById(id);
    }

    // A method to update the book based on the id of the book
    public Book updateBook(Long id, Book bookstore)
    {
        return bookRepository.findById(id).map(book->{
            book.setTitle(bookstore.getTitle());
            book.setAuthor(bookstore.getAuthor());
            book.setPrice(bookstore.getPrice());
            book.setPublishedDate(bookstore.getPublishedDate());
            return bookRepository.save(book);
        }).orElseThrow(()->new RuntimeException("Book is not found"));
    }

    // A method to delete the book based on the id of the book
    public void deleteBook(Long id)
    {
        bookRepository.deleteById(id);
    }
}
