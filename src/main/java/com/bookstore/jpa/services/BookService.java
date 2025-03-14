package com.bookstore.jpa.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

   private final BookRepository repository;
   private final AuthorRepository authorRepository;
   private final PublisherRepository publisherRepository;

   public List<BookModel> findAll() {
      return repository.findAll();
   }

   @Transactional
   public BookModel saveBook(BookRecordDto bookDto) throws BadRequestException {
     try {
      BookModel book = new BookModel();
      book.setTitle(bookDto.title());
      book.setPublisher(publisherRepository.findById(bookDto.publisherId()).get());
      book.setAuthors(authorRepository.findAllById(bookDto.authorIds()).stream().collect(Collectors.toSet()));
      book.setReview(creatReviewModel(bookDto.reviewComment(), book));

      return repository.save(book);
     } catch (Exception e) {
         throw new BadRequestException("publisher or author not found");
     }

   }

   public ReviewModel creatReviewModel(String comment, BookModel book) {
      ReviewModel review = new ReviewModel();
      review.setComment(comment);
      review.setBook(book);
      return review;
   }

   @Transactional
   public void deleteBook(UUID bookId) {
      repository.deleteById(bookId);
   }

}
