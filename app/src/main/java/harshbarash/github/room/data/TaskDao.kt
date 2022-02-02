package harshbarash.github.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import harshbarash.github.room.model.Task

//Все методы общения с нашей БД

@Dao
interface TaskDao {

    //При появлении одинаковых задач создать задачу
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY point ASC")
    fun readAllData(): LiveData<List<Task>>

}