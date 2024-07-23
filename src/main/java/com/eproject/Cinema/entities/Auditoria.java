package com.eproject.Cinema.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

      @OneToMany(mappedBy = "auditoria", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @JsonIgnore
      @OnDelete(action = OnDeleteAction.CASCADE)
      private List<Showtime> showtime;
}
