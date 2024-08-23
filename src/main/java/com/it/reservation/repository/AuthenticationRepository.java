package com.it.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.reservation.entities.AuthenticationEntities;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntities, Long>{

	@Query("select t from AuthenticationEntities t where t.userName =?1 and t.password = ?2")
    public AuthenticationEntities findByUserNameAndPassword(String userName, String password);
	
	@Query("select t from AuthenticationEntities t where t.userName =?1")
    Optional<AuthenticationEntities> findByUserName(String userName);
	
	@Query("select t from AuthenticationEntities t where t.roleId = 2")
    public List<AuthenticationEntities> findAllByEmployee();
	
	@Query("select t from AuthenticationEntities t where t.roleId = 3")
    public List<AuthenticationEntities> findAllByCustomer();
	
	@Modifying(clearAutomatically = true)
    @Query("delete from AuthenticationEntities t where t.id = ?1")
    void deleteByUserId(Long userId);
}
