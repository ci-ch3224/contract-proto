package org.example.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "contract_paragraph")
public class ContractParagraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "seq", nullable = false)
    private int seq;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "necessary")
    private Character necessary;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "contract_paragraph_ref",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private List<ContractParagraph> smallParagraphs;

    @Builder
    public ContractParagraph(long id, int seq, String title, String contents, Character necessary, List<ContractParagraph> smallParagraphs) {
        this.id = id;
        this.seq = seq;
        this.title = title;
        this.contents = contents;
        this.necessary = necessary;
        this.smallParagraphs = smallParagraphs;
    }
}
