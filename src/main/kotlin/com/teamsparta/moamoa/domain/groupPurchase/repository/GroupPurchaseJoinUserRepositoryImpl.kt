package com.teamsparta.moamoa.domain.groupPurchase.repository

import com.teamsparta.moamoa.domain.groupPurchase.model.QGroupPurchaseJoinUser
import com.teamsparta.moamoa.infra.QueryDslSupport
import java.time.LocalDateTime

class GroupPurchaseJoinUserRepositoryImpl : CustomGroupPurchaseJoinUserRepository, QueryDslSupport() {

    private val groupPurchaseJoinUser = QGroupPurchaseJoinUser.groupPurchaseJoinUser

    override fun findByGroupPurchaseIdAndSoftDelete(purchaseId: Long) {
        queryFactory.update(groupPurchaseJoinUser)
            .where(groupPurchaseJoinUser.groupPurchase.id.eq(purchaseId))
            .set(groupPurchaseJoinUser.deletedAt, LocalDateTime.now())
            .execute()

        entityManager.flush()
        entityManager.clear()
    }
}
