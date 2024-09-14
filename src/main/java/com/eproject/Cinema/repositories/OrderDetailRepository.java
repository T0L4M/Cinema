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
      public List<OrderDetail> findByOrderId(Long orderId);

      @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId AND od.product.id = :productId")
      public OrderDetail findByOrderIdAndProductId(@Param("orderId") Long orderId,
                  @Param("productId") Long productId);

      @Query(value = "SELECT p.name as name, SUM(od.product_quantity) as total_quantity " +
                  "FROM tb_order_details od " +
                  "JOIN tb_products p ON p.id = od.product_id " +
                  "GROUP BY p.name", nativeQuery = true)
      List<Object> findProductSummaries();
}
