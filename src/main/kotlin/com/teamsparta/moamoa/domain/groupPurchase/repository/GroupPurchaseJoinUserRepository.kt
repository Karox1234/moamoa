package com.teamsparta.moamoa.domain.groupPurchase.repository

import com.teamsparta.moamoa.domain.groupPurchase.model.GroupPurchaseJoinUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GroupPurchaseJoinUserRepository : JpaRepository<GroupPurchaseJoinUser, Long> , CustomGroupPurchaseJoinUserRepository {
    fun findByUserIdAndGroupPurchaseId(
        userId: Long,
        groupPurchaseId: Long,
    ): GroupPurchaseJoinUser?

    @Modifying(clearAutomatically = true)
    @Query("UPDATE GroupPurchaseJoinUser u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.groupPurchase.id = :groupPurchaseId")
    fun softDeleteByGroupPurchaseId(@Param("groupPurchaseId") groupPurchaseId: Long)

    fun findByOrderId(orderId: Long): GroupPurchaseJoinUser?
}
