package rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioHistoryEntity

@Dao
abstract class PortfolioHistoryDao {

    @Query("SELECT * FROM portfolioHistories WHERE userId LIKE :userId" )
    abstract fun getAllByUserId(userId: Long): Observable<List<PortfolioHistoryEntity>>


    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(portfolioHistoryEntity: PortfolioHistoryEntity): Completable

}