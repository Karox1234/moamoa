package com.teamsparta.moamoa.domain.order.controller
import com.teamsparta.moamoa.domain.order.dto.CancelResponse
import com.teamsparta.moamoa.domain.order.dto.CreateOrderRequest
import com.teamsparta.moamoa.domain.order.dto.OrderResponse
import com.teamsparta.moamoa.domain.order.dto.UpdateOrderRequest
import com.teamsparta.moamoa.domain.order.model.OrderStatus
import com.teamsparta.moamoa.domain.order.service.OrderService
import com.teamsparta.moamoa.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/orders")
@RestController
@Validated
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/create/swagger")
    fun createOrderAtSwagger(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody @Valid createOrderRequest: CreateOrderRequest
    ): ResponseEntity<OrderResponse> {
        val responseOrderDto = orderService.createOrder(user,createOrderRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseOrderDto)
    }

    @PostMapping("/create")
    fun createOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody @Valid createOrderRequest: CreateOrderRequest
    ): ResponseEntity<OrderResponse> {
        val responseOrderDto = orderService.createOrder(user, createOrderRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseOrderDto)
    }


    @PostMapping("/group/create")
    fun createGroupOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody @Valid createOrderRequest: CreateOrderRequest
    ): ResponseEntity<OrderResponse> {
        val responseOrderDto = orderService.createGroupOrder(user,createOrderRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseOrderDto)
    }

    @PostMapping("/group/create/swagger")
    fun creatGroupOrderAtSwagger(
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody @Valid createOrderRequest: CreateOrderRequest
    ): ResponseEntity<OrderResponse> {
        val responseOrderDto = orderService.createGroupOrder(user,createOrderRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseOrderDto)
    }

    @PutMapping("/update/{orderId}")
    fun updateOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable orderId: Long,
        @RequestBody updateOrderRequest: UpdateOrderRequest,
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.updateOrder(user, orderId, updateOrderRequest))
    }

    @PutMapping("/cancel/{orderId}")
    fun cancelOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable orderId: Long,
    ): ResponseEntity<CancelResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.cancelOrder(user, orderId))
    }

    // 주문 취소니까 수정으로 함 삭제면 삭제지!

    @GetMapping("/getOne/{orderId}")
    fun getOrder(
        @AuthenticationPrincipal user: UserPrincipal,
        @PathVariable orderId: Long,
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrder(user, orderId))
    }

    // 주문 조회

    @GetMapping("/getAllOrders")
    fun getAllOrders(
        @AuthenticationPrincipal user: UserPrincipal,
    ): ResponseEntity<List<OrderResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderList(user))
    }

    @GetMapping("/{userId}")
    fun getOrderPage(
        @PathVariable userId: Long,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "2") size: Int,
    ): ResponseEntity<Page<OrderResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderPage(userId, page, size))
    }

    // 주문 전체 조회

    // 주문 추적상태 업데이트
    @PutMapping("/status/{orderId}/{sellerId}")
    fun orderStatusChange(
        @PathVariable orderId: Long,
        @PathVariable sellerId: Long,
        @RequestParam status: OrderStatus,
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.orderStatusChange(orderId, sellerId, status))
    }
    // 주문상태 변경도 정보를 다 보여주는게 맞는거 같음

    @GetMapping("/seller{sellerId}/{orderId}")
    fun getOrderBySellerId(
        @PathVariable sellerId: Long,
        @PathVariable orderId: Long,
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderBySellerId(sellerId, orderId))
    }

    // 판매 내역 조회
    @GetMapping("/seller/{sellerId}")
    fun getOrderPageBySellerId(
        @PathVariable sellerId: Long,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "2") size: Int,
    ): ResponseEntity<Page<OrderResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderPageBySellerId(sellerId, page, size))
    }

    @GetMapping("/getOneByOrderUId/{orderUId}")
    fun getOrderByOrderUid(
        @PathVariable orderUId: String
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderByOrderUid(orderUId))
    }
}
