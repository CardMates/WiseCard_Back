package com.wisecard.api.infra.mongo.promotion.repository

import com.wisecard.api.infra.mongo.promotion.entity.PromotionEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface PromotionMongoRepository : MongoRepository<PromotionEntity, String> {
}