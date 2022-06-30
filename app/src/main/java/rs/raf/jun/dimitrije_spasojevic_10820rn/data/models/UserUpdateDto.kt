package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models


data class UserUpdateDto (
    val username: String,
    val accountValue: Double = 10000.0,
    val portfolioValue: Double = 0.0,
)
