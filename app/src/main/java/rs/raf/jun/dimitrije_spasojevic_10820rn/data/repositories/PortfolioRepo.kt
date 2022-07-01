package rs.raf.jun.dimitrije_spasojevic_10820rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioEntity
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.User
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserEntity
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto


interface PortfolioRepo {

    fun insertUser(user: User): Completable
    fun insertPortfolioItem(portfolioEntity: PortfolioEntity): Completable
    fun updateUser(updateUser: UserUpdateDto): Completable
    fun getUserByUserName(userName: String): Observable<UserEntity>
    fun getAllByUserIdAndSymbol(userId: Long, symbol: String): Observable<List<PortfolioEntity>>
    fun deleteByUserIdAndSym(userId: Long, sym: String): Completable
}