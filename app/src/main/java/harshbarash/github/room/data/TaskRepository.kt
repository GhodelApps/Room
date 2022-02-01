package harshbarash.github.room.data

import androidx.lifecycle.LiveData
import harshbarash.github.room.model.Task

class TaskRepository (private val taskDao: TaskDao){

    val  readAllData: LiveData<List<Task>> = taskDao.readAllData()
    val  readAllDataTime: LiveData<List<Task>> = taskDao.readAllDataTime()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
}