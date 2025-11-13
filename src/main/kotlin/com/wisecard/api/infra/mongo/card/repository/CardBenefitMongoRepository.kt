package com.wisecard.api.infra.mongo.card.repository

import com.wisecard.api.infra.mongo.card.entity.CardBenefitEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CardBenefitMongoRepository : MongoRepository<CardBenefitEntity, Long> {
}