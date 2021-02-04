package org.example.api.dto;

import org.example.api.domain.Company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CompanyDto {
	private long id;
    private String code;
    private String name;
    private String address;
    private String representative;
    
    @Builder
    public CompanyDto(long id, String code, String name, String address, String representative) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
		this.representative = representative;
	}
    
    public static CompanyDto of(Company entity) {
        return CompanyDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .address(entity.getAddress())
                .representative(entity.getRepresentative())
                .build();
    }

    public Company toEntity() {
        return Company.builder()
        		.id(this.getId())
                .code(this.getCode())
                .name(this.getName())
                .address(this.getAddress())
                .representative(this.getRepresentative())
                .build();
    }
    
}
