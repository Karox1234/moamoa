package com.teamsparta.moamoa.domain.order.repository

import com.teamsparta.moamoa.domain.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<Order, Long>, CustomOrderRepository {
    @Query(
        "select o from Order o" +
                " left join fetch o.payment p" +
                " left join fetch o.socialUser m" +
                " where o.orderUid = :orderUid",
    )
    fun findOrderAndPaymentAndSocialUser(orderUid: String): Optional<Order>

    @Query(
        "select o from Order o" +
                " left join fetch o.payment p" +
                " where o.orderUid = :orderUid",
    )
    fun findOrderAndPayment(orderUid: String): Optional<Order>


    fun findByProductIdAndSocialUserId(
        productId: Long,
        socialUserId: Long?,
    ): Optional<Order>

    fun findByIdAndDeletedAtIsNull(orderId: Long): Order?

    fun findBySellerIdAndDeletedAtIsNull(sellerId: Long): List<Order>

    // 주문당 리뷰가 상품당 주문당 리뷰로 바꿈
    fun findByIdAndSocialUserIdAndProductId(
        orderId: Long,
        socialUserId: Long,
        productId: Long,
    ): Optional<Order>

    fun findBySocialUserIdAndDeletedAtIsNull(userId: Long): List<Order>

    fun findByOrderUidAndDeletedAtIsNull(orderUid: String): Order?
}
