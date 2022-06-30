package rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioEntity

@Dao
abstract class PortfolioDao {

    @Query("SELECT * FROM portfolios WHERE userId LIKE :userId" )
    abstract fun getAllByUserId(userId: Long): Observable<List<PortfolioEntity>>

    @Query("SELECT * FROM portfolios WHERE userId LIKE :userId AND sym LIKE :symbol" )
    abstract fun getAllByUserIdAndSymbol(userId: Long, symbol: String): Observable<List<PortfolioEntity>>

    @Update( onConflict = OnConflictStrategy.REPLACE )
    abstract fun updatePortfolioEntity(portfolioEntity: PortfolioEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(portfolioEntity: PortfolioEntity): Completable

}