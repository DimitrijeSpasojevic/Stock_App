package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states

import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioItem
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserFull
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto


sealed class PortfolioStateUpdate {
    data class Success(val msgFromWhere: String): PortfolioStateUpdate()
    data class Error(val message: String): PortfolioStateUpdate()
}