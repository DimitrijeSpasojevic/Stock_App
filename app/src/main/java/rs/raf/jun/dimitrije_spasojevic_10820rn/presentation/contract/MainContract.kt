package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.*
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.*


interface MainContract {

    interface ViewModel {


        val usersState: LiveData<UsersState>
        val portfolioItemState: LiveData<PortfolioState>
        val portfolioHistoryState: LiveData<PortfolioHistoryState>
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
        fun getAllPortfolioHistoryByUserId(userId: Long)
        fun insertPortfolioHistoryEntity(portfolioHistoryItem: PortfolioHistoryItem)
        fun deleteAllByUserIdAndSym(userId: Long, sym: String)
    }

}