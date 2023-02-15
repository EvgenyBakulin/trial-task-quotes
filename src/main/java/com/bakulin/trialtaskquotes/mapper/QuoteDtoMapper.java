package com.bakulin.trialtaskquotes.mapper;

import com.bakulin.trialtaskquotes.dto.FullQuoteDto;
import com.bakulin.trialtaskquotes.dto.QuoteDto;
import com.bakulin.trialtaskquotes.entity.Quote;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuoteDtoMapper {
    @Mapping(source = "publisher", target = "publisher.id")
    @Mapping(source = "quote", target = "text")
    Quote qouteDtoToQuote(QuoteDto quoteDto);

    @Mapping(source = "publisher.id", target = "publisher")
    @Mapping(source = "text", target = "quote")
    QuoteDto qouteToQuoteDto(Quote quote);
}
