package org.khanguhizi.insurancedetails.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "limits_of_liability", uniqueConstraints = @UniqueConstraint(columnNames = {"description", "limits_of_liability"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimitsOfLiability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "limits_of_liability_master_table", referencedColumnName = "description")
    private LimitsOfLiabilityMasterTable description;

    @Column
    private String limitsOfLiability;

    @Column
    private String excess;

    @ManyToOne
    @JoinColumn(name= "policy_details", referencedColumnName = "insurerPolicyNumber")
    private PolicyDetails policyNumber;
}
