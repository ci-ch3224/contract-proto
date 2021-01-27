package org.example.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.api.domain.ContractTemplate;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContractTemplateDto {
    private long id;
    private String title;
    private String subTitle;
    private String templateName;
    private String createdBy;
    private String modifiedDate;
    private List<ContractParagraphDto> bigParagraphs;

    @Builder
    public ContractTemplateDto(long id, String title, String subTitle, String templateName, String createdBy, String modifiedDate, List<ContractParagraphDto> bigParagraphs) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.templateName = templateName;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.bigParagraphs = bigParagraphs;
    }

    public static ContractTemplateDto of(ContractTemplate entity) {
        return ContractTemplateDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subTitle(entity.getSubTitle())
                .templateName(entity.getTemplateName())
                .createdBy(entity.getCreatedBy().getUserName())
                .modifiedDate(entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .bigParagraphs(ContractParagraphDto.of(entity.getBigParagraphs()))
                .build();
    }

    public ContractTemplate toEntity() {
        return ContractTemplate.builder()
                .id(this.id)
                .title(this.title)
                .subTitle(this.subTitle)
                .templateName(this.templateName)
                .bigParagraphs(Stream.ofNullable(this.bigParagraphs).flatMap(Collection::stream)
                        .map(dto -> dto.toEntity())
                        .collect(Collectors.toList()))
                .build();
    }
}
