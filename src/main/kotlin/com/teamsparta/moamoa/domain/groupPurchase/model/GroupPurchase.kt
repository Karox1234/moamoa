package com.teamsparta.moamoa.domain.groupPurchase.model

import com.teamsparta.moamoa.infra.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "group_purchases")
@Entity
class GroupPurchase(
    @Column(name = "product_id")
    val productId: Long,
    @Column(name = "user_limit")
    val userLimit: Int,
    @Column(name = "user_count")
    var userCount: Int,
    @Column(name = "time_limit")
    val timeLimit: LocalDateTime?,
    @OneToMany(mappedBy = "groupPurchase", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val groupPurchaseUsers: MutableList<GroupPurchaseJoinUser> = mutableListOf(),
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
