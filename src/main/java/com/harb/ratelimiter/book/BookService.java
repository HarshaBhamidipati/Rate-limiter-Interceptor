package com.harb.ratelimiter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

     public List<Book> getBooks(){
        return (List<Book>) bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
