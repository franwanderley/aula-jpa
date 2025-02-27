package com.bookstore.jpa.services;

import org.springframework.stereotype.Service;

import com.bookstore.jpa.repositories.BookRepository;

@Service
public class BookService {
   private final BookRepository repository;

}
