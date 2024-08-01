package com.eproject.Cinema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.OrderDetail;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
      // List<OrderDetail> findByOrderIdAndProductId(@Nullable Long orderId, @Nullable
      // Long productId);

      // @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId AND
      // od.product.id = :productId")
      // List<OrderDetail> findByOrderIdAndProductId(@Param("orderId") Long orderId,
      // @Param("productId") Long productId);
}
