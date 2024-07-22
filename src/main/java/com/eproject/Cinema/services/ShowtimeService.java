package com.eproject.Cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.repositories.ShowtimeRepository;

@Service
public class ShowtimeService {
      @Autowired
      ShowtimeRepository _showtimeRepository;
}
