package com.bakulin.trialtaskquotes.mapper;

import com.bakulin.trialtaskquotes.dto.FullQuoteDto;
import com.bakulin.trialtaskquotes.entity.Quote;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FullQuoteDtoMapper {
    @Mapping(source = "publisher.id", target = "publisher")
    @Mapping(source = "text", target = "quote")
    FullQuoteDto quoteToFullQuoteDto(Quote quote);
}
