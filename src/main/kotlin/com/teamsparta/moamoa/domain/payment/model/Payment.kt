package com.teamsparta.moamoa.domain.payment.model

import com.teamsparta.moamoa.infra.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var price: Double,
    @Enumerated(EnumType.STRING)
    var status: PaymentStatus? = null,
    var paymentUid: String? = null, // 결제 고유 번호

    override var deletedAt: LocalDateTime?
) : BaseTimeEntity() {
    fun changePaymentBySuccess(
        status: PaymentStatus,
        paymentUid: String,
        deletedAt: LocalDateTime?
    ): Payment {
        this.status = status
        this.paymentUid = paymentUid
        this.deletedAt = null
        return this
    }
}
