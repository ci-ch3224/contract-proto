package org.example.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "template_id", nullable = false)
    private long templateId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sub_title", nullable = false)
    private String subTitle;

    @Column(name = "contract_date")
    private LocalDateTime contractDate;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_amount")
    private String contractAmount;

    @Column(name = "contract_condition")
    private String contractCondition;

    @Column(name = "contract_start_date")
    private LocalDateTime contractStartDate;

    @Column(name = "contract_end_date")
    private LocalDateTime contractEndDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "contract_contract_paragraph",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_paragraph_id"))
    private List<ContractParagraph> bigParagraphs;

    @Builder
    public Contract(long id, String title, String subTitle, long templateId, LocalDateTime contractDate,
                    String contractName,
                    String contractAmount,
                    String contractCondition,
                    LocalDateTime contractStartDate,
                    LocalDateTime contractEndDate,
                    List<ContractParagraph> bigParagraphs) {
        this.id = id;
        this.templateId = templateId;
        this.title = title;
        this.subTitle = subTitle;
        this.contractDate = contractDate;
        this.contractName = contractName;
        this.contractAmount = contractAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractCondition = contractCondition;
        this.bigParagraphs = bigParagraphs;
    }
}
