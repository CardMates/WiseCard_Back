package com.wisecard.api.infra.kakao

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoPlaceResponse(
    val documents: List<KakaoPlace>,
    val meta: KakaoMeta
)

data class KakaoPlace(
    @JsonProperty("address_name")
    val addressName: String,
    @JsonProperty("category_group_code")
    val categoryGroupCode: String,
    @JsonProperty("category_group_name")
    val categoryGroupName: String,
    @JsonProperty("category_name")
    val categoryName: String,
    val distance: String,
    val id: String,
    val phone: String,
    @JsonProperty("place_name")
    val placeName: String,
    @JsonProperty("place_url")
    val placeUrl: String,
    @JsonProperty("road_address_name")
    val roadAddressName: String,
    val x: String,
    val y: String
)

data class KakaoMeta(
    @JsonProperty("is_end")
    val isEnd: Boolean,
    @JsonProperty("pageable_count")
    val pageableCount: Int,
    @JsonProperty("total_count")
    val totalCount: Int,
    @JsonProperty("same_name")
    val sameName: SameName?
)

data class SameName(
    val keyword: String?,
    val region: List<String>,
    @JsonProperty("selected_region")
    val selectedRegion: String?
)