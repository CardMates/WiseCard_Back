package com.wisecard.api.infra.grpc

import com.google.protobuf.Timestamp
import com.sub.grpc.CardPromotionServiceGrpc
import com.wisecard.api.domain.promotion.model.Promotion
import com.wisecard.api.domain.promotion.service.PromotionDomainService
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.ZoneId
import com.sub.grpc.Promotion as PromotionData

@GrpcService
class PromotionGrpcServer(
    private val promotionDomainService: PromotionDomainService
) : CardPromotionServiceGrpc.CardPromotionServiceImplBase() {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun savedPromotions(
        request: PromotionData.CardPromotionList,
        responseObserver: StreamObserver<PromotionData.PromotionSaveResponse>?
    ) {
        try {
            val promotions = request.cardPromotionList.map {
                Promotion(
                    cardCompany = CardCompany.valueOf(it.cardCompany.name),
                    description = it.description,
                    imgUrl = it.imgUrl,
                    url = it.url,
                    startDate = fromProtoTimestamp(it.startDate),
                    endDate = fromProtoTimestamp(it.endDate)
                )
            }

            promotionDomainService.deleteAllPromotions()
            promotionDomainService.savePromotions(promotions)

            val response = PromotionData.PromotionSaveResponse.newBuilder()
                .setSuccess(true)
                .setMessage("프로모션 ${promotions.size}건 저장 완료")
                .setSavedCount(promotions.size)
                .build()

            responseObserver?.onNext(response)
            responseObserver?.onCompleted()

            logger.info("프로모션 ${promotions.size}건 저장 완료")

        } catch (e: Exception) {
            responseObserver?.onError(
                io.grpc.Status.INTERNAL
                    .withDescription("프로모션 저장 중 오류 발생: ${e.message}")
                    .asRuntimeException()
            )
        }
    }

    private fun fromProtoTimestamp(timestamp: Timestamp?): Instant? {
        if (timestamp == null || timestamp == Timestamp.getDefaultInstance()) return null
        return Instant.ofEpochSecond(timestamp.seconds, timestamp.nanos.toLong())
            .atZone(ZoneId.of("Asia/Seoul"))
            .toInstant()
    }
}