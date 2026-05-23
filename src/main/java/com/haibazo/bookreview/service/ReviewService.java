package com.haibazo.bookreview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.haibazo.bookreview.dto.request.ReviewRequest;
import com.haibazo.bookreview.dto.response.ReviewResponse;

public interface ReviewService {
    ReviewResponse createReview(ReviewRequest request);

    ReviewResponse updateReview(Integer id, ReviewRequest request);

    Page<ReviewResponse> getAllReviews(Pageable pageable);

    ReviewResponse getReviewById(Integer id);

    void deleteReview(Integer id);
}
