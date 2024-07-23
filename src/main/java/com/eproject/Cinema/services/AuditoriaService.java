package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Auditoria;
import com.eproject.Cinema.repositories.AuditoriaRepository;

@Service
public class AuditoriaService {
      @Autowired
      AuditoriaRepository _auditoriaRepository;

      public List<Auditoria> getAll() {
            return _auditoriaRepository.findAll();
      }

      public Auditoria create(Auditoria audi) {
            try {
                  return _auditoriaRepository.save(audi);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Auditoria detail(Long id) {
            return _auditoriaRepository.findById(id).get();
      }

      public Auditoria update(Auditoria item) {
            try {
                  return _auditoriaRepository.save(item);
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return null;
      }

      public boolean delete(Long id) {
            try {
                  Auditoria audi = _auditoriaRepository.findById(id).get();
                  if (audi != null) {
                        _auditoriaRepository.delete(audi);
                        return true;
                  }
                  return false;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }
}
