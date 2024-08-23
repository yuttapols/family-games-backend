package com.it.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.reservation.entities.ReasonCancelEntities;

public interface ReasonCancelRepository extends JpaRepository<ReasonCancelEntities, Long>{

}
