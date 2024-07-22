package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Auditoria;

@Component
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

}
