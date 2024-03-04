package com.teamsparta.moamoa.product.repository

import com.teamsparta.moamoa.product.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByIdAndDeletedAtIsNull(id: Long): Optional<Product>


}