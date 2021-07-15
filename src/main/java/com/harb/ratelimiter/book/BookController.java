package com.harb.ratelimiter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    List<Book> getAllBooks(){
        return bookService.getBooks();
    }
}
