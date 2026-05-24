package com.haibazo.bookreview.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haibazo.bookreview.dto.request.AuthorRequest;
import com.haibazo.bookreview.dto.response.AuthorResponse;
import com.haibazo.bookreview.entity.Author;
import com.haibazo.bookreview.exception.ResourceNotFoundException;
import com.haibazo.bookreview.mapper.AuthorMapper;
import com.haibazo.bookreview.repository.AuthorRepository;
import com.haibazo.bookreview.repository.BookRepository;
import com.haibazo.bookreview.service.AuthorService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    AuthorMapper authorMapper;
    BookRepository bookRepository;

    @Override
    @Transactional
    public AuthorResponse createAuthor(AuthorRequest request) {
        Author author = authorMapper.toAuthor(request);
        Author savedAuthor = authorRepository.save(author);
        return toResponseWithCount(savedAuthor);
    }

    @Override
    @Transactional
    public AuthorResponse updateAuthor(Integer id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        author.setName(request.getName());
        Author savedAuthor = authorRepository.save(author);
        return toResponseWithCount(savedAuthor);
    }

    @Override
    public Page<AuthorResponse> getAllAuthor(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(this::toResponseWithCount);
    }

    @Override
    public AuthorResponse getAuthorById(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        return toResponseWithCount(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorRepository.delete(author);
    }

    private AuthorResponse toResponseWithCount(Author author) {
        AuthorResponse response = authorMapper.toAuthorResponse(author);
        response.setBooksCount((int) bookRepository.countByAuthorId(author.getId()));
        return response;
    }

}
