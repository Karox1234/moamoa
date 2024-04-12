package com.teamsparta.moamoa.domain.groupPurchase.repository

import com.teamsparta.moamoa.domain.groupPurchase.model.GroupPurchase
import org.springframework.data.jpa.repository.JpaRepository

interface GroupPurchaseRepository : JpaRepository<GroupPurchase, Long> {
    fun findByProductIdAndDeletedAtIsNull(productId: Long): GroupPurchase?

    fun findByIdAndDeletedAtIsNull(groupPurchaseId: Long): GroupPurchase?
}
