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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haibazo.bookreview.dto.request.ReviewRequest;
import com.haibazo.bookreview.dto.response.APIResponse;
import com.haibazo.bookreview.dto.response.Pagination;
import com.haibazo.bookreview.dto.response.ReviewResponse;
import com.haibazo.bookreview.service.ReviewService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ReviewController {
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ReviewResponse>>> getAllReviews(
            @RequestParam(required = false) Integer bookId, Pageable pageable) {
        Page<ReviewResponse> page = reviewService.getAllReviews(bookId, pageable);
        return ResponseEntity.ok(APIResponse.<List<ReviewResponse>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get all review successfully")
                .data(page.getContent())
                .pagination(Pagination.from(page))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ReviewResponse>> getReviewById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.<ReviewResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Get review successfully")
                .data(reviewService.getReviewById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<APIResponse<ReviewResponse>> createReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.<ReviewResponse>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message("Review created successfully")
                .data(reviewService.createReview(request))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ReviewResponse>> updateReview(@PathVariable Integer id,
            @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(APIResponse.<ReviewResponse>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Review updated successfully")
                .data(reviewService.updateReview(id, request))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteAuthor(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(APIResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Review deleted successfully")
                .build());
    }
}
