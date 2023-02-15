package com.bakulin.trialtaskquotes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "voter")
    @JsonIgnore
    private Users voter;
    @ManyToOne
    @JoinColumn(name = "quote")
    @JsonIgnore
    private Quote quote;
    @Column(name = "voting")
    private Integer vote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote vote1)) return false;
        return Objects.equals(getId(), vote1.getId()) && Objects.equals(getVoter(), vote1.getVoter()) && Objects.equals(getQuote(), vote1.getQuote()) && Objects.equals(getVote(), vote1.getVote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVoter(), getQuote(), getVote());
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voter=" + voter +
                ", quote=" + quote +
                ", vote=" + vote +
                '}';
    }
}
