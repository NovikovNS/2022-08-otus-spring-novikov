package ru.otus.finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wishes")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "definition")
    private String definition;

    @Column(name = "link")
    private String link;

    @Column(name = "price")
    private String price;

    @Column(name = "reason")
    private String reason;

    @Column(name = "note")
    private String note;

    @Column(name = "reservation")
    private Boolean reservation;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "need_id")
    private Need need;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "availability_id")
    private Availability availability;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "wish_id")
    private List<Comment> comments = new ArrayList<>();
}
