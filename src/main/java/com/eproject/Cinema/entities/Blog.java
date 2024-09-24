package com.eproject.Cinema.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_blogs")
public class Blog extends BaseEntity {
      private String title;
      @Column(columnDefinition = "text")
      private String content;
      private boolean status;
      private String thumbnail;
}
