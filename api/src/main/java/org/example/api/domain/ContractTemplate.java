package org.example.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "contract_template")
public class ContractTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sub_title", nullable = false)
    private String subTitle;

    @Column(name = "template_name", nullable = false)
    private String templateName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "contract_template_paragraph",
        joinColumns = @JoinColumn(name = "contract_template_id"),
        inverseJoinColumns = @JoinColumn(name = "contract_paragraph_id"))
    private List<ContractParagraph> bigParagraphs;

    @Builder
    public ContractTemplate(long id, String title, String subTitle, String templateName, List<ContractParagraph> bigParagraphs) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.templateName = templateName;
        this.bigParagraphs = bigParagraphs;
    }
}
