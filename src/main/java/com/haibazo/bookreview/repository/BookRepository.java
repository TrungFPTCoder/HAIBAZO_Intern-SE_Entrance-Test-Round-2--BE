package com.haibazo.bookreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haibazo.bookreview.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    long countByAuthorId(Integer authorId);
    Page<Book> findByAuthorId(Integer authorId, Pageable pageable);
}
