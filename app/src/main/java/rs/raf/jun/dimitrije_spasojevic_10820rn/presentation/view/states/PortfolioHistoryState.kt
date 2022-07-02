package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states

import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioHistoryEntity



sealed class PortfolioHistoryState {
    object Loading: PortfolioHistoryState()
    object DataFetched: PortfolioHistoryState()
    data class Success(val portfolioHistoryItems: List<PortfolioHistoryEntity>): PortfolioHistoryState()
    data class Error(val message: String): PortfolioHistoryState()
}