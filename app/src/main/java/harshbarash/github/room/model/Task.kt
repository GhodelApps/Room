package harshbarash.github.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

//entity - сущность, класс модели

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val point: Int,
    val time: Time
)

