package com.it.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.reservation.entities.RefreshTokenEntities;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntities,Long>{

	@Query("select t from RefreshTokenEntities t where t.token = ?1")
    public RefreshTokenEntities findByToken(String token);
	
	@Query("select t from RefreshTokenEntities t where t.userId = ?1")
    public RefreshTokenEntities findByUserId(Long userId);
    
    @Modifying
    @Query("delete from RefreshTokenEntities t where t.userId = ?1")
    void deleteByUserId(Long userId);
}
 