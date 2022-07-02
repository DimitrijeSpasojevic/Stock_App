package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.*
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioStateUpdate
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioUsersItemState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState


interface MainContract {

    interface ViewModel {


        val usersState: LiveData<UsersState>
        val portfolioItemState: LiveData<PortfolioState>
        val portfolioUpdateItemState: LiveData<PortfolioStateUpdate>
        val portfolioUsersItemState: LiveData<PortfolioUsersItemState>


        fun fetchAllNews()
        fun addUser(user: User)
        fun updateUser(updateUser: UserUpdateDto)
        fun getUserByUserName(userName: String)
        fun getAllByUserIdAndSymbol(userId: Long, symbol: String)
        fun insertPortfolioItem(portfolioItem: PortfolioItem)
        fun deleteByUserIdAndSym(userId: Long, symbol: String)
        fun getAllByUserId(userId: Long)
    }

}