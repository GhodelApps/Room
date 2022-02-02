package harshbarash.github.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import harshbarash.github.room.model.Task

//Все методы общения с нашей БД

@Dao
interface TaskDao {

    //При появлении одинаковых задач создать задачу
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY point ASC")
    fun readAllData(): LiveData<List<Task>>

}
