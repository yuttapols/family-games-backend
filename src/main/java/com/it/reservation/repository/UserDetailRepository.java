package com.it.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.reservation.entities.UserDetailEntities;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntities, Long>{

	@Query("select t from UserDetailEntities t where t.userId = ?1")
    public UserDetailEntities findByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserDetailEntities t where t.userId = ?1")
    void deleteByUserId(Long userId);
}
