package com.wisecard.api.application.card

import com.wisecard.api.application.card.dto.*
import com.wisecard.api.domain.card.service.CardDomainService
import com.wisecard.api.infra.kakao.KakaoMapClient
import com.wisecard.api.infra.kakao.KakaoPlace
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import com.wisecard.api.infra.mongo.card.constant.CardType
import com.wisecard.api.infra.mongo.card.constant.Category
import com.wisecard.api.infra.mongo.card.constant.ChannelType
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardDomainService: CardDomainService,
    private val kakaoMapClient: KakaoMapClient
) {
    fun getAllCards(cardCompany: CardCompany?, cardType: CardType?, cardName: String?): List<CardResponse> {
        val allCards = cardDomainService.getAllCardBenefits()
        val filtered = allCards.filter { card ->
            (cardCompany == null || card.cardCompany == cardCompany) &&
                    (cardType == null || card.cardType == cardType) &&
                    (cardName.isNullOrBlank() || card.cardName.contains(cardName, ignoreCase = true))
        }

        return filtered.map { it.toResponseDto() }
    }

    fun getMyCard(
        cardCompany: CardCompany?,
        cardType: CardType?,
        cardName: String?,
        cardIdRequest: CardIdRequest
    ): List<CardResponse> {
        val allCards = cardIdRequest.cardIds.map { id ->
            cardDomainService.getCardBenefitById(id)
        }
        val filtered = allCards.filter { card ->
            (cardCompany == null || card.cardCompany == cardCompany) &&
                    (cardType == null || card.cardType == cardType) &&
                    (cardName.isNullOrBlank() || card.cardName.contains(cardName, ignoreCase = true))
        }

        return filtered.map { it.toResponseDto() }
    }

    fun getOffline(
        x: String,
        y: String,
        query: String?,
        category: Category?,
        cardIdRequest: CardIdRequest
    ): List<PlaceResponse> {
        val allCards = cardIdRequest.cardIds.map { id ->
            cardDomainService.getCardBenefitById(id)
        }

        // 1. OFFLINE 또는 BOTH 채널 혜택만 추출
        val filtered = allCards.map { card ->
            val benefits = card.benefits.map { benefit ->
                benefit.copy(
                    discounts = benefit.discounts.filter { discount ->
                        discount.channel == ChannelType.OFFLINE || discount.channel == ChannelType.BOTH
                    },
                    points = benefit.points.filter { point ->
                        point.channel == ChannelType.OFFLINE || point.channel == ChannelType.BOTH
                    },
                    cashbacks = benefit.cashbacks.filter { cashback ->
                        cashback.channel == ChannelType.OFFLINE || cashback.channel == ChannelType.BOTH
                    },
                )
            }
            card.copy(benefits = benefits)
        }

        // 2. target Map
        val targetMap = mutableMapOf<KakaoPlace, MutableList<CardBenefitResponse>>()

        filtered.forEach { card ->
            card.benefits.forEach { benefit ->
                val response = CardBenefitResponse(
                    cardId = card.cardId,
                    cardCompany = card.cardCompany,
                    cardName = card.cardName,
                    imgUrl = card.imgUrl,
                    cardType = card.cardType,
                    benefit = benefit.toResponseDto()
                )
                if (!query.isNullOrBlank()) {
                    benefit.targets.forEach { target ->
                        if (target.contains(query, ignoreCase = true)) {
                            kakaoMapClient.searchKeyword(query, x, y).map { place ->
                                targetMap.computeIfAbsent(place) { mutableListOf() }.add(response)
                            }
                        }
                    }
                } else if (category != null) {
                    benefit.categories.forEach { benefitCategory ->
                        if (benefitCategory.contains(category.name, ignoreCase = true)) {
                            benefit.targets.forEach { target ->
                                kakaoMapClient.searchCategory(target, category, x, y).map { place ->
                                    targetMap.computeIfAbsent(place) { mutableListOf() }.add(response)
                                }
                            }
                        }
                    }
                }
            }
        }

        return targetMap.map { (place, cards) ->
            PlaceResponse(place = place, cards = cards)
        }
    }

    fun getOnline(category: Category?, cardIdRequest: CardIdRequest): List<CardResponse> {
        val allCards = cardIdRequest.cardIds.map { id ->
            cardDomainService.getCardBenefitById(id)
        }

        val filtered = allCards.map { card ->
            val benefits = card.benefits
                // 1. 카테고리를 포함하는 베네핏만 필터링
                .filter { benefit ->
                    category == null || benefit.categories.any {
                        it.equals(category.toString(), ignoreCase = true)
                    }
                }
                // 2. 필터된 베네핏 안에서 ONLINE, BOTH만 남김
                .map { benefit ->
                    benefit.copy(
                        discounts = benefit.discounts.filter {
                            it.channel == ChannelType.ONLINE || it.channel == ChannelType.BOTH
                        },
                        points = benefit.points.filter {
                            it.channel == ChannelType.ONLINE || it.channel == ChannelType.BOTH
                        },
                        cashbacks = benefit.cashbacks.filter {
                            it.channel == ChannelType.ONLINE || it.channel == ChannelType.BOTH
                        }
                    )
                }
            card.copy(benefits = benefits)
        }

        return filtered.map { it.toResponseDto() }
    }
}