package com.haibazo.bookreview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.haibazo.bookreview.dto.request.AuthorRequest;
import com.haibazo.bookreview.dto.response.AuthorResponse;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorRequest request);

    AuthorResponse updateAuthor(Integer id, AuthorRequest request);

    Page<AuthorResponse> getAllAuthor(Pageable pageable);

    AuthorResponse getAuthorById(Integer id);

    void deleteAuthor(Integer id);
}
