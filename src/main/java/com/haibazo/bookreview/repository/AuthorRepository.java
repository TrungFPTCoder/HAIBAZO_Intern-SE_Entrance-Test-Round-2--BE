package com.haibazo.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haibazo.bookreview.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
