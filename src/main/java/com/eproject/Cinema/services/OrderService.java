package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.Order;
import com.eproject.Cinema.repositories.OrderRepository;

@Service
public class OrderService {
      @Autowired
      OrderRepository _orderRepository;

      public List<Order> getAll() {
            return _orderRepository.findAll();
      }

      public Order create(Order order) {
            try {
                  return _orderRepository.save(order);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public Order detail(Long id) {
            return _orderRepository.findById(id).get();
      }

      public Order update(Order order) {
            try {
                  return _orderRepository.save(order);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public boolean delete(Long id) {
            try {
                  Order order = _orderRepository.findById(id).get();
                  if (order != null) {
                        _orderRepository.delete(order);
                        return true;
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }
}
