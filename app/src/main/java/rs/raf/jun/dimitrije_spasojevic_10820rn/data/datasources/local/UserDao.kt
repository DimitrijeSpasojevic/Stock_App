package rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserEntity

@Dao
abstract class UserDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: UserEntity): Completable

    @Query("SELECT * FROM users")
    abstract fun getAllUsers(): Observable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE username LIKE :userName")
    abstract fun getUserByUsername(userName: String): Observable<UserEntity>

    @Query("SELECT * FROM users WHERE email LIKE :email")
    abstract fun getUserByEmail(email: String): Observable<UserEntity>

    @Query("UPDATE users SET accountValue = :accValue, portfolioValue =:portfolioValue WHERE username =:userName")
    abstract fun updateUser(accValue: Double, portfolioValue: Double, userName: String): Completable

}