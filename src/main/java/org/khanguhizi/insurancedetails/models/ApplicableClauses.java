package org.khanguhizi.insurancedetails.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "applicable_clauses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicableClauses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clauseId;

    @Column(nullable = false, unique = true)
    private String clause;
}
