package com.teamsparta.moamoa.domain.order.repository

import com.teamsparta.moamoa.domain.order.model.OrdersEntity
import com.teamsparta.moamoa.domain.order.model.QOrdersEntity
import com.teamsparta.moamoa.domain.product.model.QProductEntity
import com.teamsparta.moamoa.domain.seller.model.QSellerEntity
import com.teamsparta.moamoa.infra.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class OrderRepositoryImpl : CustomOrderRepository, QueryDslSupport() {
    private val orders = QOrdersEntity.ordersEntity
    private val product = QProductEntity.productEntity
    private val seller = QSellerEntity.sellerEntity

    override fun getOrderPage(
        userId: Long,
        page: Int,
        size: Int,
    ): Page<OrdersEntity> {
        val result =
            queryFactory.selectFrom(orders)
                .where(orders.userId.id.eq(userId))
                .offset((page - 1).toLong())
                .limit(size.toLong())
                .orderBy(orders.userId.id.asc())
                .fetch()
        return PageImpl(result)
    }

    override fun getOrderPageBySellerId(
        sellerId: Long,
        page: Int,
        size: Int,
    ): Page<OrdersEntity> {
//        val findProduct = queryFactory.selectFrom(product)
//            .where(product.seller.sellerId.eq(sellerId))
//        val result = queryFactory.selectFrom(orders)
//            .where(orders.product.eq(findProduct))
//            .offset((page-1).toLong())
//            .limit(size.toLong())
//            .orderBy(orders.product.seller.sellerId.asc())
//            .fetch()

        // 쿼리를 두개 쪼개서 쓰고 있기 때문에 오류가 남 join을 사용해보자

        val join =
            queryFactory.selectFrom(orders)
                .join(orders.product, product).fetchJoin()
                .join(product.seller, seller).fetchJoin()
                .where(seller.sellerId.eq(sellerId))
        val result =
            join
                .offset((page - 1).toLong())
                .limit(size.toLong())
                .fetch()
        return PageImpl(result)
    }
}
