package org.example.api.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("sign")
@Table(name = "sign")
public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "contract_id", nullable = false)
    private long contractId;

    @Column(name = "contractor_id", nullable = false)
    private long contractorId;

    @Lob
    @Column(name = "sign_image", nullable = false, columnDefinition = "LONGTEXT")
    private String signImage;

    @Builder
    public Sign(long id, long contractId, long contractorId, String signImage) {
        this.id = id;
        this.contractId = contractId;
        this.contractorId = contractorId;
        this.signImage = signImage;
    }
}
