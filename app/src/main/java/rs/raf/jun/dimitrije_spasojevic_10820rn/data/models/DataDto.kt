package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

import com.squareup.moshi.JsonClass
import rs.raf.rafstudenthelper.data.models.NewsResponse

@JsonClass(generateAdapter = true)
data class DataDto (
    val newsItems: List<News>
        )