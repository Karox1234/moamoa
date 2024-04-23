package com.teamsparta.moamoa.domain.image.model

import com.teamsparta.moamoa.domain.product.model.Product
import com.teamsparta.moamoa.domain.review.model.Review
import jakarta.persistence.*
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "image")
class Image(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    val review: Review?,
    @Column(name = "image_path")
    var imagePath: String? = null,
    @Column(name = "file_name")
    var imageName: String? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
