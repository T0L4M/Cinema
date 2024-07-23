package com.eproject.Cinema.entities;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_movies")
public class Movie extends BaseEntity {
      private String title;
      private String director;
      private String casts;
      private String genre;
      @Column(columnDefinition = "text")
      private String description;
      private String poster;
      // @DateTimeFormat(pattern = "dd/MM/yyyy")
      @Temporal(TemporalType.DATE)
      @Future(message = "Release date cannot be in the Past")
      private Date release_date;

      @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
      @Transient
      @OnDelete(action = OnDeleteAction.CASCADE)
      private List<Showtime> showtime;
}
