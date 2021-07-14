package com.harb.ratelimiter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    List<Book> getAllBooks(){
        return bookService.getBooks();
    }

    @RequestMapping(method = RequestMethod.POST,value="/book")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }
}
