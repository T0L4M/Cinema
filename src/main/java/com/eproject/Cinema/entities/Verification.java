package com.eproject.Cinema.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="tbl_verifications")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Verification extends BaseEntity{
    @Column(name="email")
    private String email;

    @Column(name="token")
    private String token;

    @Column(name="expired_at")
    private Timestamp expiredAt;
}
