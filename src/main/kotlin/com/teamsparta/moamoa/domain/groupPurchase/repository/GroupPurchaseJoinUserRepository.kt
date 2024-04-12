package com.teamsparta.moamoa.domain.groupPurchase.repository

import com.teamsparta.moamoa.domain.groupPurchase.model.GroupPurchaseJoinUser
import org.springframework.data.jpa.repository.JpaRepository

interface GroupPurchaseJoinUserRepository : JpaRepository<GroupPurchaseJoinUser, Long> {
    fun findByUserIdAndGroupPurchaseId(
        userId: Long,
        groupPurchaseId: Long,
    ): GroupPurchaseJoinUser?

    fun findByGroupPurchaseId(purchaseId: Long): List<GroupPurchaseJoinUser>

    fun findByOrderId(orderId: Long): GroupPurchaseJoinUser?
}
