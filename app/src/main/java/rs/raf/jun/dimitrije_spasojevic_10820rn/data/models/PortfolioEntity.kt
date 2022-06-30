package rs.raf.jun.dimitrije_spasojevic_10820rn.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )], tableName = "portfolios"
)
data class PortfolioEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val sym: String,
    val userId: Long,
    val quantity: Long,
)
