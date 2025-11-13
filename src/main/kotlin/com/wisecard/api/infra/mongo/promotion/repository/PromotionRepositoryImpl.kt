package com.wisecard.api.infra.mongo.promotion.repository


import com.wisecard.api.domain.promotion.model.Promotion
import com.wisecard.api.domain.promotion.repository.PromotionRepository
import com.wisecard.api.infra.mongo.promotion.mapper.PromotionMapper
import org.springframework.stereotype.Repository

@Repository
class PromotionRepositoryImpl(
    private val promotionMongoRepository: PromotionMongoRepository
) : PromotionRepository {

    override fun saveAll(promotions: List<Promotion>) {
        val entities = promotions.map(PromotionMapper::toEntity)
        promotionMongoRepository.saveAll(entities)
    }

    override fun findAll(): List<Promotion> {
        return promotionMongoRepository.findAll().map(PromotionMapper::toDomain)
    }

    override fun deleteAll() {
        promotionMongoRepository.deleteAll()
    }
}