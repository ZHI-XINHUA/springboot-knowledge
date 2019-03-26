package com.wps.jpa.repository;


import com.wps.jpa.entity.UserBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnyUserRepository extends JpaRepository<UserBO,String> {
}
