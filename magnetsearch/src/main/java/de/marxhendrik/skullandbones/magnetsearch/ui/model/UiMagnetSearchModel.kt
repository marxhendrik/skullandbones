package de.marxhendrik.skullandbones.magnetsearch.ui.model

data class UiMagnetSearchModel(val results: List<UiMagnetSearchResult>)

data class UiMagnetSearchResult(val name: String = "", val magnetLink: String = "")
