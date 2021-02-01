package org.example.api.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
}
