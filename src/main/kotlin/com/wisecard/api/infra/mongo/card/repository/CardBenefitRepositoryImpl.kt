package com.wisecard.api.infra.mongo.card.repository

import com.wisecard.api.domain.card.exception.CardBenefitException
import com.wisecard.api.domain.card.model.CardBenefit
import com.wisecard.api.domain.card.repository.CardBenefitRepository
import com.wisecard.api.infra.mongo.card.mapper.CardBenefitMapper
import org.springframework.stereotype.Repository

@Repository
class CardBenefitRepositoryImpl(
    private val cardBenefitMongoRepository: CardBenefitMongoRepository
) : CardBenefitRepository {

    override fun saveAll(cardBenefits: List<CardBenefit>) {
        val entities = cardBenefits.map(CardBenefitMapper::toEntity)
        cardBenefitMongoRepository.saveAll(entities)
    }

    override fun getAll(): List<CardBenefit> {
        val entities = cardBenefitMongoRepository.findAll()
        return entities.map(CardBenefitMapper::toDomain)
    }

    override fun getById(id: Long): CardBenefit {
        val entity = cardBenefitMongoRepository.findById(id)
                .orElseThrow { CardBenefitException.CardNotFoundException() }
        return CardBenefitMapper.toDomain(entity)
    }

    override fun deleteAll() {
        cardBenefitMongoRepository.deleteAll()
    }
}