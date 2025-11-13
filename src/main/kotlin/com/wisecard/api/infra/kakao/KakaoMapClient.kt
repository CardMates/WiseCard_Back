package com.wisecard.api.infra.kakao

import com.wisecard.api.infra.mongo.card.constant.Category
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Component
class KakaoMapClient(
    @Value("\${kakao.rest-api-key}") private val restApiKey: String
) {

    fun searchKeyword(query: String, x: String, y: String, radius: Int = 1000): List<KakaoPlace> {
        val webClient = WebClient.builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader("Authorization", "KakaoAK $restApiKey")
            .build()

        val uri = UriComponentsBuilder
            .fromPath("/v2/local/search/keyword.json")
            .queryParam("query", query)
            .queryParam("x", x)
            .queryParam("y", y)
            .queryParam("radius", radius)
            .build()
            .toUriString()

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(KakaoPlaceResponse::class.java)
            .block()!!
            .documents
    }

    fun searchCategory(query: String, category: Category, x: String, y: String, radius: Int = 1000): List<KakaoPlace> {
        val webClient = WebClient.builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader("Authorization", "KakaoAK $restApiKey")
            .build()

        val uri = UriComponentsBuilder
            .fromPath("/v2/local/search/keyword.json")
            .queryParam("query", query)
            .queryParam("category_group_code", category)
            .queryParam("x", x)
            .queryParam("y", y)
            .queryParam("radius", radius)
            .build()
            .toUriString()

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(KakaoPlaceResponse::class.java)
            .block()!!
            .documents
    }
}