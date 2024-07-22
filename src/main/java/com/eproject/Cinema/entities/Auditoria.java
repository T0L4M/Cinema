package com.eproject.Cinema.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_auditorias")
public class Auditoria extends BaseEntity {
      private String name;
      private int colNum;
      private int rowNum;

      @OneToOne(mappedBy = "auditoria")
      private Showtime showtime;
}
