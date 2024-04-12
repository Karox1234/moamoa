package com.teamsparta.moamoa.domain.order.model

import com.teamsparta.moamoa.domain.order.dto.ResponseOrderDto
import com.teamsparta.moamoa.domain.payment.model.PaymentEntity
import com.teamsparta.moamoa.domain.product.model.Product
import com.teamsparta.moamoa.domain.socialUser.model.SocialUser
import com.teamsparta.moamoa.infra.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrdersEntity(
    @Column(name = "product_name")
    var productName: String,
    @Column(name = "total_price")
    var totalPrice: Double,
    @Column(name = "address")
    var address: String,
    @Column(name = "discount")
    var discount: Double,
    @Column(name = "quantity")
    var quantity: Int,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var socialUser: SocialUser,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    var payment: PaymentEntity,
    var orderUid: String?,
    @Column
    val phoneNumber: String,
    @Column
    val sellerId: Long?,
    @Column
    var reviewId: Long?,

    override var deletedAt: LocalDateTime?

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: OrderStatus = OrderStatus.NOTPAYD

    fun changeOrderBySuccess(
        status: OrderStatus,
        deletedAt: LocalDateTime?
    ): OrdersEntity {
        this.status = status
        this.deletedAt = null
        return this
    }
}

fun OrdersEntity.toResponse(): ResponseOrderDto {
    return ResponseOrderDto(
        orderId = id!!,
        productName = productName,
        totalPrice = totalPrice,
        address = address,
        createdAt = createdAt,
        updatedAt = updatedAt,
        status = status.toString(),
        discount = discount,
        quantity = quantity,
        orderUid = orderUid!!,
    )
}
