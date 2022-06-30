package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val username: String,
    val email: String,
    val password: String,
    val accountValue: Double = 10000.0,
    val portfolioValue: Double = 0.0,
)
