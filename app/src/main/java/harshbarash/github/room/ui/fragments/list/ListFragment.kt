package harshbarash.github.room.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import harshbarash.github.room.R
import harshbarash.github.room.databinding.FragmentListBinding
import harshbarash.github.room.viewModel.TaskViewModel


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var _binding: FragmentListBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        _binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val adapter = ListAdapter()
        val recyclerView = _binding.rvTasks
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task) })
    }
}