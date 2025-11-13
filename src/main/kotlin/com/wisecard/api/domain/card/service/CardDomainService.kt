package com.wisecard.api.domain.card.service

import com.wisecard.api.domain.card.model.CardBenefit
import com.wisecard.api.domain.card.repository.CardBenefitRepository
import org.springframework.stereotype.Service

@Service
class CardDomainService(
    private val cardBenefitRepository: CardBenefitRepository
) {
    fun saveCardBenefits(cardBenefits: List<CardBenefit>) {
        cardBenefitRepository.saveAll(cardBenefits)
    }

    fun getAllCardBenefits(): List<CardBenefit> {
        return cardBenefitRepository.getAll()
    }

    fun getCardBenefitById(id: Long): CardBenefit {
        return cardBenefitRepository.getById(id)
    }

    fun deleteAllCardBenefits() {
        cardBenefitRepository.deleteAll()
    }
}