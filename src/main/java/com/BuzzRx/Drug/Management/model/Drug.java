package com.BuzzRx.Drug.Management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="drugs",uniqueConstraints = {@UniqueConstraint(columnNames = "ndc")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String ndc;

    @Column(nullable = false)
    private String strength;

    @Column(nullable = false)
    private String form;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
