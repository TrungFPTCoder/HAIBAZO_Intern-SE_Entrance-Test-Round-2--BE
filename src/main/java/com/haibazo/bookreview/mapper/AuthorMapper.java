package com.haibazo.bookreview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.haibazo.bookreview.dto.request.AuthorRequest;
import com.haibazo.bookreview.dto.response.AuthorResponse;
import com.haibazo.bookreview.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorRequest request);

    @Mapping(target = "booksCount", ignore = true)
    AuthorResponse toAuthorResponse(Author author);
}
