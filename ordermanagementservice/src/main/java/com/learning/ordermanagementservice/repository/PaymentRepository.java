package com.learning.ordermanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.learning.ordermanagementservice.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
