package com.wisecard.api.api.card

import com.wisecard.api.application.card.CardService
import com.wisecard.api.application.card.dto.CardIdRequest
import com.wisecard.api.application.card.dto.CardResponse
import com.wisecard.api.application.card.dto.PlaceResponse
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import com.wisecard.api.infra.mongo.card.constant.CardType
import com.wisecard.api.infra.mongo.card.constant.Category
import org.springframework.web.bind.annotation.*

@RestController
class CardController(
    private val cardService: CardService
) {
    @GetMapping(CardApi.V1.BASE_URI)
    fun getAllCards(
        @RequestParam(required = false) cardCompany: CardCompany?,
        @RequestParam(required = false) cardType: CardType?,
        @RequestParam(required = false) cardName: String?
    ): List<CardResponse> {
        return cardService.getAllCards(cardCompany, cardType, cardName)
    }

    @PostMapping(CardApi.V1.MY)
    fun getMyCards(
        @RequestParam(required = false) cardCompany: CardCompany?,
        @RequestParam(required = false) cardType: CardType?,
        @RequestParam(required = false) cardName: String?,
        @RequestBody cardIdRequest: CardIdRequest
    ): List<CardResponse> {
        return cardService.getMyCard(cardCompany, cardType, cardName, cardIdRequest)
    }

    @PostMapping(CardApi.V1.OFFLINE)
    fun getOffline(
        @RequestParam x: String,
        @RequestParam y: String,
        @RequestParam query: String?,
        @RequestParam category: Category?,
        @RequestBody cardIdRequest: CardIdRequest
    ): List<PlaceResponse> {
        return cardService.getOffline(x, y, query, category, cardIdRequest)
    }

    @PostMapping(CardApi.V1.ONLINE)
    fun getOnline(
        @RequestParam category: Category?,
        @RequestBody cardIdRequest: CardIdRequest
    ): List<CardResponse> {
        return cardService.getOnline(category,cardIdRequest)
    }
}