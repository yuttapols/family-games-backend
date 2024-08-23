package com.it.reservation.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.it.reservation.entities.ReservationEntities;

public interface ReservationRepository extends JpaRepository<ReservationEntities, Long>{

	@Query("select t from ReservationEntities t where t.userId = ?1 and t.revStatus = '1'")
	public Optional<ReservationEntities> findByUserId(Long userId);
	
    @Modifying
    @Query("delete from ReservationEntities t where t.userId = ?1")
    void deleteByUserId(Long userId);
    
	@Query("select count(*) from ReservationEntities t where DATE(t.revTime) = CURDATE() and t.revStatus in ('3','4') and t.userId = ?1")
	public Integer findMaxCancelByUserIdInDay(Long userId);
	
	@Query("select count(*) from ReservationEntities t where DATE(t.revTime) = CURDATE() and t.revNo = ?1 ")
	public Integer findMaxUsedRevNoInDay(String revNo);
	
	@Query("select count(*) from ReservationEntities t where t.revStatus = '1'")
	public Integer findUserStatusWaitingAll();
	
	@Query("select t from ReservationEntities t where t.revStatus = '1' order by t.revTime ")
	public List<ReservationEntities> findByStatusWaiting();
	
	@Query("select t from ReservationEntities t where t.userId = ?1 order by t.revTime desc ")
	public List<ReservationEntities> findByByUserId(Long userId);
	
	@Query("select distinct t.revNo from ReservationEntities t where DATE(t.revTime) = ?1 ORDER BY t.revNo DESC LIMIT 1")
	public String findRevNoBeforeDay(Date currenDateInt1Day);
}
