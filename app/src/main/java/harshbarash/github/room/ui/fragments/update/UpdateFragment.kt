package harshbarash.github.room.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import harshbarash.github.room.R
import harshbarash.github.room.databinding.FragmentAddBinding
import harshbarash.github.room.databinding.FragmentUpdateBinding
import harshbarash.github.room.model.Task
import harshbarash.github.room.viewModel.TaskViewModel


class UpdateFragment : Fragment(R.layout.fragment_update) {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var _binding: FragmentUpdateBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateBinding.bind(view)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        _binding.etUpdateTask.setText(args.task.title)
        _binding.etUpdateDescription.setText(args.task.description)
        _binding.etUpdatePoint.setText(args.task.point.toString())

        _binding.btnUpdate.setOnClickListener {
            updateItem()
        }

    }

    private fun updateItem() {
        val task = _binding.etUpdateTask.text.toString()
        val description = _binding.etUpdateDescription.text.toString()
        val point = Integer.parseInt(_binding.etUpdatePoint.text.toString())

        if (_binding.etUpdateTask.text?.let { inputCheck(task, description, it) } == true) {
            // Create User Object
            val updatedTask = Task(args.task.id, task, description, point)
            // Update Current User
            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да") { _, _ ->
            mTaskViewModel.deleteTask(args.task)
            view?.let { Snackbar.make(it, "Удалена задача: ${args.task.title}", Snackbar.LENGTH_LONG) }
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Удалить ${args.task.title}?")
        builder.create().show()
    }
}