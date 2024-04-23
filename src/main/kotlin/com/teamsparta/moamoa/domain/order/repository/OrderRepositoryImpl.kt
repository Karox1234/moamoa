package com.teamsparta.moamoa.domain.order.repository

import com.teamsparta.moamoa.domain.order.model.Order
import com.teamsparta.moamoa.domain.order.model.QOrder
import com.teamsparta.moamoa.domain.product.model.QProduct
import com.teamsparta.moamoa.domain.seller.model.QSeller
import com.teamsparta.moamoa.infra.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class OrderRepositoryImpl : CustomOrderRepository, QueryDslSupport() {
    private val order = QOrder.order
    private val product = QProduct.product
    private val seller = QSeller.seller

    override fun getOrderPage(
        userId: Long,
        page: Int,
        size: Int,
    ): Page<Order> {
        val result =
            queryFactory.selectFrom(order)
                .where(order.socialUser.id.eq(userId))
                .offset((page - 1).toLong())
                .limit(size.toLong())
                .orderBy(order.createdAt.desc())
                .fetch()
        return PageImpl(result)
    }

    override fun getOrderPageBySellerId(
        sellerId: Long,
        page: Int,
        size: Int,
    ): Page<Order> {
        val join =
            queryFactory.selectFrom(order)
                .join(order.product, product).fetchJoin()
                .where(seller.id.eq(sellerId))
        val result =
            join
                .offset((page - 1).toLong())
                .limit(size.toLong())
                .orderBy(order.product.id.asc())
                .fetch()
        return PageImpl(result)
    }
}
