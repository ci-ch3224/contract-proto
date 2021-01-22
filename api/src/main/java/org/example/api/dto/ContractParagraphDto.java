package org.example.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.api.domain.ContractParagraph;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContractParagraphDto {
    private long id;
    private int seq;
    private String title;
    private String contents;
    private List<ContractParagraphDto> smallParagraphs;

    @Builder
    public ContractParagraphDto(long id, int seq, String title, String contents, List<ContractParagraphDto> smallParagraphs) {
        this.id = id;
        this.seq = seq;
        this.title = title;
        this.contents = contents;
        this.smallParagraphs = smallParagraphs;
    }

    public static ContractParagraphDto of(ContractParagraph entity) {
        return ContractParagraphDto.builder()
                .id(entity.getId())
                .seq(entity.getSeq())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .smallParagraphs(ContractParagraphDto.of(entity.getSmallParagraphs()))
                .build();
    }

    public static List<ContractParagraphDto> of(List<ContractParagraph> entities) {
        return Stream.ofNullable(entities).flatMap(Collection::stream)
                .map(ContractParagraphDto::of)
                .collect(Collectors.toList());
    }

    public ContractParagraph toEntity() {
        return ContractParagraph.builder()
                .id(this.id)
                .seq(this.seq)
                .title(this.title)
                .contents(this.contents)
                .smallParagraphs(Optional.ofNullable(this.smallParagraphs).orElse(null).stream()
                    .map(dto -> dto.toEntity()).collect(Collectors.toList()))
                .build();
    }
}
