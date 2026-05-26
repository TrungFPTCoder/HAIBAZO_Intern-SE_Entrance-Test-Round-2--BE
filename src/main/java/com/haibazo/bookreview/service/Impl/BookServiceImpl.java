package com.haibazo.bookreview.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haibazo.bookreview.dto.request.BookRequest;
import com.haibazo.bookreview.dto.response.BookResponse;
import com.haibazo.bookreview.entity.Author;
import com.haibazo.bookreview.entity.Book;
import com.haibazo.bookreview.exception.ResourceNotFoundException;
import com.haibazo.bookreview.mapper.BookMapper;
import com.haibazo.bookreview.repository.AuthorRepository;
import com.haibazo.bookreview.repository.BookRepository;
import com.haibazo.bookreview.repository.ReviewRepository;
import com.haibazo.bookreview.service.BookService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;
    AuthorRepository authorRepository;
    ReviewRepository reviewRepository;

    @Override
    @Transactional
    public BookResponse createBook(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + request.getAuthorId() + " not found"));
        Book book = bookMapper.toBook(request);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return toResponseWithCount(savedBook);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Integer id, BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + request.getAuthorId() + " not found"));
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.updateBook(book, request);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return toResponseWithCount(savedBook);
    }

    @Override
    public Page<BookResponse> getAllBooks(Integer authorId, Pageable pageable) {
        if (authorId != null) {
            return bookRepository.findByAuthorId(authorId, pageable).map(this::toResponseWithCount);
        }
        return bookRepository.findAll(pageable).map(this::toResponseWithCount);
    }

    @Override
    public BookResponse getBookById(Integer id) {
        return toResponseWithCount(bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found")));
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookRepository.delete(book);
    }

    private BookResponse toResponseWithCount(Book book) {
        BookResponse response = bookMapper.toBookResponse(book);
        response.setReviewsCount((int) reviewRepository.countByBookId(book.getId()));
        return response;
    }
}
