package com.haibazo.bookreview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.haibazo.bookreview.dto.request.ReviewRequest;
import com.haibazo.bookreview.dto.response.ReviewResponse;
import com.haibazo.bookreview.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewRequest request);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookName", source = "book.name")
    @Mapping(target = "authorId", source = "book.author.id")
    @Mapping(target = "authorName", source = "book.author.name")
    ReviewResponse toReviewResponse(Review review);
}
