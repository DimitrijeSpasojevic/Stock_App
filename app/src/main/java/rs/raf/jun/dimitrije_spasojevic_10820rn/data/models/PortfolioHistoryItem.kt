package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models


import java.sql.Date


data class PortfolioHistoryItem (
    val value: Double,
    val userId: Long,
    val date: Date,
)
