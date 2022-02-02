package harshbarash.github.room.ui.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import harshbarash.github.room.R
import harshbarash.github.room.databinding.FragmentDataBinding
import harshbarash.github.room.databinding.FragmentListBinding
import harshbarash.github.room.model.Task
import harshbarash.github.room.viewModel.TaskViewModel

class DataFragment : Fragment() {

    private lateinit var _binding: FragmentDataBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDataBinding.bind(view)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        _binding.btnCreate.setOnClickListener {
            insrtDataToDatabase()
        }
    }

    private fun insrtDataToDatabase() {
       val task = _binding.taskInputText.toString()
       val description = _binding.descInputText.toString()
       val point = _binding.pointInputText

        if(inputCheck(task, description, point)) {
            val task = Task(id, task, description, Integer.parseInt(point.toString()))
            mTaskViewModel.addTask(task)
            view?.let { Snackbar.make(it, "Задача поставлена", Snackbar.LENGTH_LONG) }
            findNavController().navigate(R.id.action_dataFragment_to_listFragment)
        } else {
            view?.let { Snackbar.make(it, "Поставьте задачу", Snackbar.LENGTH_LONG) }
        }
    }

    private fun inputCheck(task: String, description: String, point: TextInputEditText): Boolean {
        return !(TextUtils.isEmpty(task))
    }
}
