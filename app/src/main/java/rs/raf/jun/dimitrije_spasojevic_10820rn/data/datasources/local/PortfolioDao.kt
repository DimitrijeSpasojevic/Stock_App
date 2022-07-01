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

    @Query("UPDATE portfolios SET quantity = :newQuantity WHERE userId =:userId AND sym = :symbol")
    abstract fun updatePortfolioEntity(userId: Long, symbol: String, newQuantity: Long): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(portfolioEntity: PortfolioEntity): Completable

    @Query("DELETE FROM portfolios WHERE userId = :userId AND sym = :sym")
    abstract fun deleteByUserIdAndSym(userId: Long, sym: String): Completable

}