package com.bakulin.trialtaskquotes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "dateOfPublication")
    private OffsetDateTime dateOfPublication;
    @ManyToOne
    @JoinColumn(name = "publisher")
    @JsonIgnore
    private Users publisher;
    @OneToMany(mappedBy = "quote")
    @JsonIgnore
    private List<Vote> votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote quote)) return false;
        return Objects.equals(getId(), quote.getId()) && Objects.equals(getText(), quote.getText()) && Objects.equals(getDateOfPublication(), quote.getDateOfPublication()) && Objects.equals(getPublisher(), quote.getPublisher()) && Objects.equals(getVotes(), quote.getVotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getDateOfPublication(), getPublisher(), getVotes());
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", publisher=" + publisher +
                ", votes=" + votes +
                '}';
    }
}
