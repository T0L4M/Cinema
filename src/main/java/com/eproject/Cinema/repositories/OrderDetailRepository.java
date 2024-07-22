package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.OrderDetail;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
