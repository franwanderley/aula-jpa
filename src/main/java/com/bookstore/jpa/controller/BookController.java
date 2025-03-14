package com.bookstore.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.services.BookService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
   private final BookService service;

   @PostMapping
   public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookDto) throws BadRequestException {
       return ResponseEntity.status(201).body(service.saveBook(bookDto));   
   }

   @GetMapping
   public ResponseEntity<List<BookModel>> findAll() {
       return ResponseEntity.ok(service.findAll());
   }

   @DeleteMapping("/{bookId}")
   public void deleteBook(@PathVariable UUID bookId){
      service.deleteBook(bookId);
   }
   
   
}
