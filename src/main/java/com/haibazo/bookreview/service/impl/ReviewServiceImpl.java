package com.haibazo.bookreview.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haibazo.bookreview.dto.request.ReviewRequest;
import com.haibazo.bookreview.dto.response.ReviewResponse;
import com.haibazo.bookreview.entity.Book;
import com.haibazo.bookreview.entity.Review;
import com.haibazo.bookreview.exception.ResourceNotFoundException;
import com.haibazo.bookreview.mapper.ReviewMapper;
import com.haibazo.bookreview.repository.BookRepository;
import com.haibazo.bookreview.repository.ReviewRepository;
import com.haibazo.bookreview.service.ReviewService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    BookRepository bookRepository;
    ReviewMapper reviewMapper;

    @Override
    @Transactional
    public ReviewResponse createReview(ReviewRequest request){
        Book book = bookRepository.findById(request.getBookId())
        .orElseThrow(()-> new ResourceNotFoundException("Book with id :" + request.getBookId() + "not found!"));

        Review review = reviewMapper.toReview(request);
        review.setBook(book);
        Review createdReview = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(createdReview);
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Integer id, ReviewRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + request.getBookId() + " not found"));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
        review.setBook(book);
        review.setReview(request.getReview());
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewResponse(savedReview);
    }

    @Override
    public ReviewResponse getReviewById(Integer id) {
        return reviewMapper.toReviewResponse(reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found")));
    }

    @Override
    public Page<ReviewResponse> getAllReviews(Integer bookId, Pageable pageable) {
        if (bookId != null) {
            return reviewRepository.findByBookId(bookId, pageable)
                    .map(reviewMapper::toReviewResponse);
        }
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::toReviewResponse);
    }

    @Override
    @Transactional
    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
        reviewRepository.delete(review);
    }
    
}
