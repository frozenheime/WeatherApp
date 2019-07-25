package by.fro.data.local.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "City", indices = [Index("name", unique = true)])
data class CityLocalModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name: String?,
    var countyCode: String?,
    var current: Boolean?,
    var favorite: Boolean?
)