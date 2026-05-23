package com.haibazo.bookreview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.haibazo.bookreview.dto.request.BookRequest;
import com.haibazo.bookreview.dto.response.BookResponse;

public interface BookService {
    BookResponse createBook(BookRequest request);

    BookResponse updateBook(Integer id, BookRequest request);

    Page<BookResponse> getAllBooks(Pageable pageable);

    BookResponse getBookById(Integer id);

    void deleteBook(Integer id);
}
