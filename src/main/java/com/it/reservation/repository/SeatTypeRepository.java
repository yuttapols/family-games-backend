package com.it.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.reservation.entities.SeatTypeEntities;

public interface SeatTypeRepository extends JpaRepository<SeatTypeEntities, Long>{

}
