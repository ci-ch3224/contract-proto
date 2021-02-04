package org.example.api.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@DiscriminatorValue("company")
@Table(name = "company")
public class Company extends Contractor {
    @Column(name = "representative", nullable = false)
    private String representative;

    @Builder
	public Company(long id, String code, String name, String address, String representative) {
		super(id, code, name, address);
		this.representative = representative;
	}

}
