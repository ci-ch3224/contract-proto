package org.example.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.api.domain.Contract;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContractDto {
    private long id;
    private long templateId;
    private String title;
    private String subTitle;
    private List<ContractParagraphDto> bigParagraphs;

    @Builder
    public ContractDto(long id, long templateId, String title, String subTitle, String templateName, List<ContractParagraphDto> bigParagraphs) {
        this.id = id;
        this.templateId = templateId;
        this.title = title;
        this.subTitle = subTitle;
        this.bigParagraphs = bigParagraphs;
    }

    public static ContractDto of(Contract entity) {
        return ContractDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subTitle(entity.getSubTitle())
                .templateId(entity.getTemplateId())
                .bigParagraphs(ContractParagraphDto.of(entity.getBigParagraphs()))
                .build();
    }

    public Contract toEntity() {
        return Contract.builder()
                .id(this.id)
                .title(this.title)
                .subTitle(this.subTitle)
                .templateId(this.templateId)
                .bigParagraphs(Stream.ofNullable(this.bigParagraphs).flatMap(Collection::stream)
                        .map(dto -> dto.toEntity())
                        .collect(Collectors.toList()))
                .build();
    }
}
