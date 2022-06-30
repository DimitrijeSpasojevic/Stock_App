package rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.converters.Converters
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioEntity
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioHistoryEntity
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserEntity

@Database(
    entities = [UserEntity::class, PortfolioEntity::class, PortfolioHistoryEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getPortfolioDao(): PortfolioDao
    abstract fun getPortfolioHistoryDao(): PortfolioHistoryDao
}