package harshbarash.github.room.ui.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import harshbarash.github.room.R
import harshbarash.github.room.databinding.TaskBinding
import harshbarash.github.room.model.Task

class ListAdapter(
    private val listener: (Task) -> Unit,
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var taskList = emptyList<Task>()

    class ViewHolder(view: View, private val listener: (Task) -> Unit) : RecyclerView.ViewHolder(view) {

        private val binding = TaskBinding.bind(view)

        fun bind(item: Task) {
            binding.tvTask.text = item.title
            binding.tvDesc.text = item.description
            binding.tvPoint.text = item.point.toString()
            binding.root.setOnClickListener { listener(item) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.task, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(order: List<Task>) {
        this.taskList = order
        notifyDataSetChanged()
    }
}
