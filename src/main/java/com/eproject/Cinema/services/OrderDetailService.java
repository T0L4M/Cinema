package com.eproject.Cinema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.OrderDetail;
import com.eproject.Cinema.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
      @Autowired
      OrderDetailRepository _orderDetailRepository;

      public List<OrderDetail> getAll(){
            return _orderDetailRepository.findAll();
      }

      public OrderDetail create(OrderDetail orderdt){
            try {
                  return _orderDetailRepository.save(orderdt);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public OrderDetail detail(Long id){
            return _orderDetailRepository.findById(id).get();
      }

      public OrderDetail update(OrderDetail orderdt){
            try {
                  return _orderDetailRepository.save(orderdt);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

      public boolean delete(Long id){
            try {
                  OrderDetail orederDetail = _orderDetailRepository.findById(id).get();
                  if (orederDetail != null) {
                        _orderDetailRepository.delete(orederDetail);
                        return true;
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return false;
      }

}
