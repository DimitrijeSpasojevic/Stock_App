package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states

import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioItem


sealed class PortfolioUsersItemState {
    object Loading: PortfolioUsersItemState()
    object DataFetched: PortfolioUsersItemState()
    data class Success(val portfolioItems: List<PortfolioItem>): PortfolioUsersItemState()
    data class Error(val message: String): PortfolioUsersItemState()
}