package com.haibazo.bookreview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.haibazo.bookreview.dto.request.ReviewRequest;
import com.haibazo.bookreview.dto.response.ReviewResponse;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest request);

    ReviewResponse updateReview(Integer id, ReviewRequest request);

    // optional pass param id (book id) to filter by bookid
    Page<ReviewResponse> getAllReviews(Integer id, Pageable pageable);

    ReviewResponse getReviewById(Integer id);

    void deleteReview(Integer id);
}
