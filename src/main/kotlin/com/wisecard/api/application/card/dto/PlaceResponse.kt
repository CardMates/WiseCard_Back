package com.wisecard.api.application.card.dto

import com.wisecard.api.infra.kakao.KakaoPlace
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import com.wisecard.api.infra.mongo.card.constant.CardType

data class PlaceResponse(
    val place: KakaoPlace,
    val cards: List<CardBenefitResponse>
)

data class CardBenefitResponse(
    val cardId: Int,
    val cardCompany: CardCompany,
    val cardName: String,
    val imgUrl: String,
    val cardType: CardType,
    val benefit: BenefitResponseDto
)