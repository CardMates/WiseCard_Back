package com.wisecard.api.domain.card.repository

import com.wisecard.api.domain.card.model.CardBenefit

interface CardBenefitRepository {
    fun saveAll(cardBenefits: List<CardBenefit>)

    fun getAll(): List<CardBenefit>

    fun getById(id: Long): CardBenefit

    fun deleteAll()
}