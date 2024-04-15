package com.teamsparta.moamoa.domain.payment.repository

import com.teamsparta.moamoa.domain.payment.model.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long>
