package com.it.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.reservation.entities.RoleEntities;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntities, Long>{

    @Query("select t from RoleEntities t where t.status = '1' ")
    public List<RoleEntities> findByIsActive();
}
