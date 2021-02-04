package org.example.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.example.api.domain.Company;
import org.example.api.domain.Contract;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private String contractDate;
    private String contractName;
    private String contractAmount;
    private String contractCondition;
    private String contractStartDate;
    private String contractEndDate;
    private List<ContractParagraphDto> bigParagraphs;
    private Company gap;
    private Company eul;

    @Builder
    public ContractDto(long id, long templateId, String title, String subTitle, String contractDate,
                       String contractName, String contractAmount, String contractCondition,
                       String contractStartDate, String contractEndDate,
                       List<ContractParagraphDto> bigParagraphs,
                       Company gap,
                       Company eul) {
        this.id = id;
        this.templateId = templateId;
        this.title = title;
        this.subTitle = subTitle;
        this.contractDate = contractDate;
        this.contractName = contractName;
        this.contractAmount = contractAmount;
        this.contractCondition = contractCondition;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.bigParagraphs = bigParagraphs;
        this.gap = gap;
        this.eul = eul;
    }

    public static ContractDto of(Contract entity) {
        return ContractDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subTitle(entity.getSubTitle())
                .templateId(entity.getTemplateId())
                .contractDate(entity.getContractDate() != null ? entity.getContractDate().format(DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .contractName(entity.getContractName())
                .contractAmount(entity.getContractAmount())
                .contractCondition(entity.getContractCondition())
                .contractStartDate(entity.getContractStartDate() != null ? entity.getContractStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .contractEndDate(entity.getContractEndDate() != null ? entity.getContractEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .bigParagraphs(ContractParagraphDto.of(entity.getBigParagraphs()))
                .gap(entity.getGap())
                .eul(entity.getEul())
                .build();
    }

    public Contract toEntity() {
        return Contract.builder()
                .id(this.id)
                .title(this.title)
                .subTitle(this.subTitle)
                .templateId(this.templateId)
                .contractDate(this.contractDate != null ? LocalDate.parse(this.contractDate, DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .contractName(this.contractName)
                .contractAmount(this.contractAmount)
                .contractCondition(this.contractCondition)
                .contractStartDate(this.contractStartDate != null ? LocalDate.parse(this.contractStartDate, DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .contractEndDate(this.contractEndDate != null ? LocalDate.parse(this.contractEndDate, DateTimeFormatter.ISO_LOCAL_DATE) : null)
                .bigParagraphs(Stream.ofNullable(this.bigParagraphs).flatMap(Collection::stream)
                        .map(dto -> dto.toEntity())
                        .collect(Collectors.toList()))
                .gap(this.gap)
                .eul(this.eul)
                .build();
    }
}
