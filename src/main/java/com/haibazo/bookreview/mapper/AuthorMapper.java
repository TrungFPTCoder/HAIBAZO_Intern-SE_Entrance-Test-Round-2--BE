package com.haibazo.bookreview.mapper;

import com.haibazo.bookreview.dto.request.AuthorRequest;
import com.haibazo.bookreview.dto.response.AuthorResponse;
import com.haibazo.bookreview.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Author toAuthor(AuthorRequest request);

    @Mapping(target = "booksCount", ignore = true)
    AuthorResponse toAuthorResponse(Author author);
}
