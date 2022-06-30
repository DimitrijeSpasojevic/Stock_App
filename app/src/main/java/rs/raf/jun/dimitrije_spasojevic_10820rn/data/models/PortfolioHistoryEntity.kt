package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )],tableName = "portfolioHistories"
)
data class PortfolioHistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val value: Double,
    val userId: Long,
    val date: Date,
)
