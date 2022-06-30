package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

data class Quote (
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: Double,
    val changeFromPreviousClose: Double,
    val percentChangeFromPreviousClose: Double,
    val marketName: String,
    val recommendation: RecomendationType,
    val chart: Bars
    )
