package com.teamsparta.moamoa.domain.order.service

import com.teamsparta.moamoa.domain.order.dto.*
import com.teamsparta.moamoa.domain.order.model.OrderStatus
import com.teamsparta.moamoa.infra.security.UserPrincipal
import org.springframework.data.domain.Page

interface OrderService {
    fun createOrder(
        user: UserPrincipal, createOrderRequest: CreateOrderRequest
    ): OrderResponse

    // 테스트용 락 없는 코드
    fun createOrderNoLock(
        userId: Long,
        productId: Long,
        quantity: Int,
        address: String,
        phoneNumber: String,
    ): OrderResponse

    fun createGroupOrder(
        user: UserPrincipal, createOrderRequest: CreateOrderRequest
    ): OrderResponse

    fun updateOrder(
        user: UserPrincipal,
        orderId: Long,
        updateOrderRequest: UpdateOrderRequest,
    ): OrderResponse

    fun cancelOrder(
        user: UserPrincipal,
        orderId: Long,
    ): CancelResponse

    fun getOrder(
        user: UserPrincipal,
        orderId: Long,
    ): OrderResponse

    fun getOrderList(
        user: UserPrincipal,
    ): List<OrderResponse>

    fun getOrderPage(
        userId: Long,
        page: Int,
        size: Int,
    ): Page<OrderResponse>

    fun orderStatusChange(
        orderId: Long,
        sellerId: Long,
        status: OrderStatus,
    ): OrderResponse

    fun getOrderBySellerId(
        sellerId: Long,
        orderId: Long,
    ): OrderResponse

    fun getOrderPageBySellerId(
        sellerId: Long,
        page: Int,
        size: Int,
    ): Page<OrderResponse>

    fun trollOrderDelete(orderUId: String)

    fun getOrderByOrderUid(
        orderUId: String,
    ): OrderResponse

    fun createOrderWithLock(
        userId: Long,
        productId: Long,
        quantity: Int,
        address: String,
        phoneNumber: String,
    ): OrderResponse
}
