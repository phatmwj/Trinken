package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.PaymentMethod;

@Repository
public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer>  {

}
