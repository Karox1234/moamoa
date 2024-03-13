package com.teamsparta.moamoa.domain.product.controller

import com.teamsparta.moamoa.domain.product.dto.ProductRequest
import com.teamsparta.moamoa.domain.product.dto.ProductResponse
import com.teamsparta.moamoa.domain.product.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping
    fun getAllProducts(): List<ProductResponse> {
        return productService.getAllProducts()
    }

    @GetMapping("/{productId}")
    fun getProduct(
        @PathVariable productId: Long,
    ): ProductResponse {
        return productService.getProductById(productId)
    }

    @PostMapping("/{sellerId}")
    fun createProduct(
        @PathVariable sellerId: Long,
        @RequestBody request: ProductRequest,
    ): ResponseEntity<ProductResponse> {
        val product = productService.createProduct(sellerId, request)
        val response = ProductResponse(product)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{productId}/{sellerId}")
    fun updateProduct(
        @PathVariable productId: Long,
        @PathVariable sellerId: Long,
        @RequestBody productRequest: ProductRequest,
    ): ResponseEntity<ProductResponse> {
        val updatedProduct = productService.updateProduct(productId, sellerId, productRequest)
        return ResponseEntity.ok(ProductResponse(updatedProduct))
    }

    @PutMapping("/{productId}/{sellerId}/delete")
    fun deleteProduct(
        @PathVariable productId: Long,
        @PathVariable sellerId: Long,
    ): ResponseEntity<ProductResponse> {
        val product = productService.deleteProduct(productId, sellerId)
        return ResponseEntity.ok(ProductResponse(product))
    }

    @GetMapping("/pages")
    fun getPaginatedProductList(
        @PageableDefault(size = 15, sort = ["id"]) pageable: Pageable,
    ): ResponseEntity<Page<ProductResponse>> {
        val products = productService.getPaginatedProductList(pageable)
        return ResponseEntity.status(HttpStatus.OK).body(products)
    }
}
