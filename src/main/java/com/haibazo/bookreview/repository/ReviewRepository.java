package com.haibazo.bookreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haibazo.bookreview.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    long countByBookId(Integer bookId);
    Page<Review> findByBookId(Integer bookId, Pageable pageable);
}
