package harshbarash.github.room.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import harshbarash.github.room.databinding.TaskBinding
import harshbarash.github.room.model.Task

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var taskList = emptyList<Task>()

    class ViewHolder(val _binding: TaskBinding): RecyclerView.ViewHolder(_binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder._binding.tvTask.text = currentItem.title
        holder._binding.tvDesc.text = currentItem.description
        holder._binding.tvPoint.text = currentItem.point.toString()

        holder._binding.taskLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder._binding.taskLayout.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(order: List<Task>){
        this.taskList = order
        notifyDataSetChanged()
    }
}