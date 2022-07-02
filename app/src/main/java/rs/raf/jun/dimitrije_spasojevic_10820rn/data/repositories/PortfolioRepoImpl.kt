package rs.raf.jun.dimitrije_spasojevic_10820rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local.PortfolioDao
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local.PortfolioHistoryDao
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local.UserDao
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioEntity
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.User
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserEntity
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto
import timber.log.Timber

class PortfolioRepoImpl (
    private val localDataSourceP: PortfolioDao,   //portfolio
    private val localDataSourceH: PortfolioHistoryDao,  //history
    private val userDataSource: UserDao
) : PortfolioRepo {

    override fun insertUser(user: User): Completable {
        val userEntity = UserEntity(username = user.username, email = user.email, password = user.password,)
        return userDataSource.insert(userEntity)
    }

    override fun insertPortfolioItem(portfolioEntity: PortfolioEntity): Completable {
        return localDataSourceP.insert(portfolioEntity)
    }

    override fun updateUser(updateUser: UserUpdateDto): Completable {
        return userDataSource.updateUser(updateUser.accountValue, updateUser.portfolioValue, updateUser.username)
    }

    override fun getUserByUserName(userName: String): Observable<UserEntity> {
        return userDataSource.getUserByUsername(userName)
    }

    override fun getAllByUserIdAndSymbol(userId: Long, symbol: String): Observable<List<PortfolioEntity>> {
        return localDataSourceP.getAllByUserIdAndSymbol(userId, symbol)
    }

    override fun deleteByUserIdAndSym(userId: Long, sym: String): Completable {
        return localDataSourceP.deleteByUserIdAndSym(userId, sym)
    }

    override fun getAllByUserId(userId: Long): Observable<List<PortfolioEntity>> {
        return localDataSourceP.getAllByUserId(userId)
    }


}