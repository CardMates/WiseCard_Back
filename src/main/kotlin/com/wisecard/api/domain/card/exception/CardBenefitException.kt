package com.wisecard.api.domain.card.exception

import com.newket.core.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

sealed class CardBenefitException(
    message: String,
    errorCode: Int,
    httpStatusCode: HttpStatusCode,
) : BusinessException(DEFAULT_CODE_PREFIX, errorCode, httpStatusCode, message) {

    class CardNotFoundException(message: String = "존재하지 않는 카드입니다.") :
        CardBenefitException(message, 1, HttpStatus.BAD_REQUEST)

    companion object {
        const val DEFAULT_CODE_PREFIX = "CARS"
    }
}