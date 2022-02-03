package harshbarash.github.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import harshbarash.github.room.model.Task
import kotlinx.coroutines.flow.Flow

//Все методы общения с нашей БД

@Dao
interface TaskDao {

    //При появлении одинаковых задач создать задачу
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY point DESC")
    fun readAllData(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Task>>
}
