package com.seyran.refundriskengine.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="buyers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    private String email;

    private int totalOrders;
    private int totalRefunds;
    private boolean blocked;


    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm.ss")
    private LocalDateTime createdAt =LocalDateTime.now();

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
}
