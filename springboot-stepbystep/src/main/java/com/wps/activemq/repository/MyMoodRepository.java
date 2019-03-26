package com.wps.activemq.repository;

import com.wps.activemq.entity.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMoodRepository extends JpaRepository<AyMood,String> {
}
