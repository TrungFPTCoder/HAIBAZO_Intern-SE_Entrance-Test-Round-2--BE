package com.haibazo.bookreview.mapper;

import com.haibazo.bookreview.dto.request.BookRequest;
import com.haibazo.bookreview.dto.response.BookResponse;
import com.haibazo.bookreview.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Book toBook(BookRequest request);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "reviewsCount", ignore = true)
    BookResponse toBookResponse(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    void updateBook(@MappingTarget Book book, BookRequest request);
}
