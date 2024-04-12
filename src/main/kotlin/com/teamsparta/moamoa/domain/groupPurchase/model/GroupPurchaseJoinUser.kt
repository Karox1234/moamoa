package com.teamsparta.moamoa.domain.groupPurchase.model

import com.teamsparta.moamoa.infra.BaseTimeEntity
import jakarta.persistence.*

@Table(name = "group_purchases_users")
@Entity
class GroupPurchaseJoinUser(
    @Column(name = "user_id")
    val userId: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_purchase_id")
    val groupPurchase: GroupPurchase,
    @Column(name = "order_id")
    val orderId: Long,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
