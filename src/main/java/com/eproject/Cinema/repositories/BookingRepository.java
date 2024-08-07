package com.eproject.Cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.eproject.Cinema.entities.Booking;

@Component
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
