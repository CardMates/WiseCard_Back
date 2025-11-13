package com.wisecard.api.infra.grpc

import com.sub.grpc.CardData
import com.sub.grpc.CardDataServiceGrpc
import com.wisecard.api.domain.card.model.*
import com.wisecard.api.domain.card.service.CardDomainService
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.slf4j.LoggerFactory

@GrpcService
class CardBenefitGrpcServer(
    private val cardBenefitService: CardDomainService
) : CardDataServiceGrpc.CardDataServiceImplBase() {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun saveCardData(
        request: CardData.CardBenefitList,
        responseObserver: StreamObserver<CardData.CardSaveResponse>
    ) {
        try {
            val cardBenefits = request.cardBenefitsList.map { proto ->
                CardBenefit(
                    cardId = proto.cardId,
                    cardCompany = com.wisecard.api.infra.mongo.card.constant.CardCompany.valueOf(proto.cardCompany.name),
                    cardName = proto.cardName,
                    imgUrl = proto.imgUrl,
                    cardType = com.wisecard.api.infra.mongo.card.constant.CardType.valueOf(proto.cardType.name),
                    benefits = proto.benefitsList.map { b ->
                        Benefit(
                            discounts = b.discountsList.map {
                                DiscountBenefit(it.rate, it.amount, it.minimumAmount, it.benefitLimit, it.minimumSpending,
                                    com.wisecard.api.infra.mongo.card.constant.ChannelType.valueOf(it.channel.name))
                            },
                            points = b.pointsList.map {
                                PointBenefit(it.name, it.amount, it.rate, it.minimumAmount, it.benefitLimit, it.minimumSpending,
                                    com.wisecard.api.infra.mongo.card.constant.ChannelType.valueOf(it.channel.name))
                            },
                            cashbacks = b.cashbacksList.map {
                                CashbackBenefit(it.rate, it.amount, it.minimumAmount, it.benefitLimit, it.minimumSpending,
                                    com.wisecard.api.infra.mongo.card.constant.ChannelType.valueOf(it.channel.name))
                            },
                            categories = b.categoriesList,
                            targets = b.targetsList,
                            summary = b.summary
                        )
                    }
                )
            }

            cardBenefitService.deleteAllCardBenefits()
            cardBenefitService.saveCardBenefits(cardBenefits)

            responseObserver.onNext(
                CardData.CardSaveResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("카드 혜택 ${cardBenefits.size}건 저장 완료")
                    .setSavedCount(cardBenefits.size)
                    .build()
            )
            responseObserver.onCompleted()

            logger.info("카드 혜택 ${cardBenefits.size}건 저장 완료")

        } catch (e: Exception) {
            responseObserver.onError(
                io.grpc.Status.INTERNAL.withDescription("저장 중 오류 발생: ${e.message}").asRuntimeException()
            )
        }
    }
}