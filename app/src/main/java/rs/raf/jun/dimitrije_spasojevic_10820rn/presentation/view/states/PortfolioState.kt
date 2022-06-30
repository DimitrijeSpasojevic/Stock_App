package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states

import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioItem
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserFull
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto


sealed class PortfolioState {
    object Loading: PortfolioState()
    object DataFetched: PortfolioState()
    data class Success(val portfolioItems: List<PortfolioItem>): PortfolioState()
    data class Error(val message: String): PortfolioState()
}