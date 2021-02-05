package org.example.api.dto;
import org.example.api.domain.Sign;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignDto {
    private long id;
    private long contractId;
    private long contractorId;
    private String signImage;

    @Builder
    public SignDto(long id, long contractId, long contractorId, String signImage) {
        this.id = id;
        this.contractId = contractId;
        this.contractorId = contractorId;
        this.signImage = signImage;
    }

    public static SignDto of(Sign entity) {
        return SignDto.builder()
                .id(entity.getId())
                .contractId(entity.getContractId())
                .contractorId(entity.getContractorId())
                .signImage(entity.getSignImage())
                .build();
    }

    public Sign toEntity() {
        return Sign.builder()
                .id(this.getId())
                .contractId(this.getContractId())
                .contractorId(this.getContractorId())
                .signImage(this.getSignImage())
                .build();
    }

}
