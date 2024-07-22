package com.eproject.Cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.repositories.AuditoriaRepository;

@Service
public class AuditoriaService {
      @Autowired
      AuditoriaRepository _auditoriaRepository;
}
