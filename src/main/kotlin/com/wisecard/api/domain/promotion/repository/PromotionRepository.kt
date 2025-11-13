package com.wisecard.api.domain.promotion.repository

import com.wisecard.api.domain.promotion.model.Promotion

interface PromotionRepository {
    fun saveAll(promotions: List<Promotion>)

    fun findAll(): List<Promotion>

    fun deleteAll()
}