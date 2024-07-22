package com.eproject.Cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eproject.Cinema.response.HttpResponse;
import com.eproject.Cinema.services.AuditoriaService;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController extends BaseController {
      @Autowired
      AuditoriaService _auditoriaService;

      @Autowired
      HttpResponse _httpResponse;
}
