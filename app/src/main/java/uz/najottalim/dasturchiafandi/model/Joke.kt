package uz.najottalim.dasturchiafandi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class Joke(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "setup")
    val setup: String,

    @ColumnInfo(name = "punchline")
    val punchline: String
)
