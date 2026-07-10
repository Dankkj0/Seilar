package com.saimuelbr.pobreflix

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import org.jsoup.nodes.Document

class PobreFlixProvider : MainAPI() {
    override var name = "PobreFlix" 
    
    // COLOQUE O LINK FUNCIONAL ATUALIZADO AQUI
    override var mainUrl = "https://www.pobreflixtv.moe" 
    
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)
    override var lang = "pt"
    override val hasMainPage = true

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse? {
        val document = app.get(mainUrl).document
        val homeItems = mutableListOf<SearchResponse>()
        
        document.select("div.poster, div.item").forEach { element ->
            val title = element.select("h2, span.title").text()
            val url = element.select("a").attr("href")
            val posterUrl = element.select("img").attr("src")
            
            if (title.isNotEmpty() && url.isNotEmpty()) {
                homeItems.add(newMovieSearchResponse(title, url, TvType.Movie) {
                    this.posterUrl = posterUrl
                })
            }
        }
        
        return newHomePageResponse(request.name, homeItems)
    }

    override suspend fun load(url: String): LoadResponse? {
        val document = app.get(url).document
        val title = document.select("h1.entry-title, h1").text()
        val poster = document.select("div.poster img, img.wp-post-image").attr("src")
        val plot = document.select("div.description, div.synopsis").text()

        return newMovieLoadResponse(title, url, TvType.Movie, url) {
            this.posterUrl = poster
            this.plot = plot
        }
    }

    override suspend fun loadLinks(
        data: String,
        isC網絡Video: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val document = app.get(data).document
        
        document.select("iframe").forEach { iframe ->
            val iframeUrl = iframe.attr("src")
            if (iframeUrl.isNotEmpty()) {
                loadExtractor(iframeUrl, data, subtitleCallback, callback)
            }
        }
        return true
    }
}
