package org.khanguhizi.insurancedetails.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "exclusion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String exclusion;

}
