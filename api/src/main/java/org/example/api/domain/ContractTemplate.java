package org.example.api.domain;

import lombok.*;
import org.example.api.global.model.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "contract_template")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "modified_by", updatable = false)
    private User modifiedBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

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
