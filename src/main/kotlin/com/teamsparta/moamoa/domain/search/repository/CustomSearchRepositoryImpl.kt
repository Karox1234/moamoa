package com.teamsparta.moamoa.domain.search.repository

import com.teamsparta.moamoa.domain.product.model.QProduct
import com.teamsparta.moamoa.domain.review.model.QReview
import com.teamsparta.moamoa.domain.search.dto.ProductSearchResponse
import com.teamsparta.moamoa.domain.search.dto.ReviewSearchResponse
import com.teamsparta.moamoa.infra.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CustomSearchRepositoryImpl : CustomSearchRepository, QueryDslSupport() {
    private val product = QProduct.product
    private val review = QReview.review

    override fun searchProductsByLikes(pageable: Pageable): Page<ProductSearchResponse> {
        val totalCount =
            queryFactory.select(product.count())
                .from(product)
                .where(product.deletedAt.isNull)
                .fetchOne() ?: 0L

        val products =
            queryFactory.selectFrom(product)
                .where(product.deletedAt.isNull)
                .orderBy(product.likes.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()

        val contents =
            products.map {
                ProductSearchResponse(
                    productId = it.id!!,
                    title = it.title,
                    content = it.content,
                    imageUrl = it.imageUrl,
                    price = it.price,
                    ratingAverage = it.ratingAverage,
                    likes = it.likes,
                )
            }

        return PageImpl(contents, pageable, totalCount)
    }

    override fun searchReviewsByLikes(pageable: Pageable): Page<ReviewSearchResponse> {
        val totalCount =
            queryFactory.select(review.count())
                .from(review)
                .where(review.deletedAt.isNull)
                .fetchOne() ?: 0L

        val reviews =
            queryFactory.selectFrom(review)
                .where(review.deletedAt.isNull)
                .orderBy(review.likes.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()

        val contents =
            reviews.map {
                ReviewSearchResponse(
                    reviewId = it.id!!,
                    productId = it.product.id!!,
                    title = it.title,
                    content = it.content,
                    imageUrl = it.imageUrl,
                    name = it.socialUser.nickname,
                    likes = it.likes,
                )
            }

        return PageImpl(contents, pageable, totalCount)
    }
}
