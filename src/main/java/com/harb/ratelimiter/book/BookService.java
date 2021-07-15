package com.harb.ratelimiter.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class BookService {


     public List<Book> getBooks(){
        return Arrays.asList(
                new Book("1","Alchemist","Self Help Book"),
                new Book("2","Kite Runner", "Self Help Book"),
                new Book("3","Scion of Ikshvaku","Mythological Tale")
        );
    }

}
