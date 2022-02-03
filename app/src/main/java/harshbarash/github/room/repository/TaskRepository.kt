package harshbarash.github.room.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import harshbarash.github.room.data.TaskDao
import harshbarash.github.room.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTasks(){
        taskDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Task>> {
        return taskDao.searchDatabase(searchQuery)
    }

}