package com.haibazo.bookreview.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haibazo.bookreview.dto.request.AuthorRequest;
import com.haibazo.bookreview.dto.response.APIResponse;
import com.haibazo.bookreview.dto.response.AuthorResponse;
import com.haibazo.bookreview.dto.response.Pagination;
import com.haibazo.bookreview.service.AuthorService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthorController {
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<APIResponse<List<AuthorResponse>>> getAllAuthors(Pageable pageable) {
        Page<AuthorResponse> page = authorService.getAllAuthor(pageable);
        return ResponseEntity.ok(APIResponse.<List<AuthorResponse>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all authors successfully")
                .data(page.getContent())
                .pagination(Pagination.from(page))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AuthorResponse>> getAuthorById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.<AuthorResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get author successfully")
                .data(authorService.getAuthorById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<APIResponse<AuthorResponse>> createAuthor(@Valid @RequestBody AuthorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.<AuthorResponse>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Create author successfully")
                .data(authorService.createAuthor(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AuthorResponse>> updateAuthor(@PathVariable Integer id,
            @Valid @RequestBody AuthorRequest request) {
        return ResponseEntity.ok(APIResponse.<AuthorResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Update author successfully")
                .data(authorService.updateAuthor(id, request))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(APIResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Delete author successfully")
                .build());
    }

}
