package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

data class SearchQuote (
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: Double,
    val open: Double,
    val close: Double,
    val bid: Double,
    val ask: Double,
    val metrics: Metric,
    val chart: Bars
    )
