package com.bakulin.trialtaskquotes.mapper;

import com.bakulin.trialtaskquotes.dto.VoteDto;
import com.bakulin.trialtaskquotes.entity.Vote;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VoteDtoMapper {
    @Mapping(source = "voter", target = "voter.id")
    @Mapping(source = "quote", target = "quote.id")
    Vote voteDtoToVote(VoteDto voteDto);
}
