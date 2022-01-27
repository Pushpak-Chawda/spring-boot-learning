package com.pc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.springboot.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

	

}
