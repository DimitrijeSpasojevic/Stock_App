package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataQuotes (
    val quotes: List<Quote>
        )