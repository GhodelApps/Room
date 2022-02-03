package harshbarash.github.room.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import harshbarash.github.room.R
import harshbarash.github.room.databinding.FragmentListBinding
import harshbarash.github.room.model.Task
import harshbarash.github.room.viewModel.TaskViewModel

class ListFragment : Fragment(R.layout.fragment_list), SearchView.OnQueryTextListener {

    private lateinit var _binding: FragmentListBinding
    private lateinit var mTaskViewModel: TaskViewModel
    private val listAdapter: ListAdapter by lazy { ListAdapter { task ->
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(task)
        findNavController().navigate(action)} }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        _binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val adapter = ListAdapter { task ->
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(task)
            findNavController().navigate(action)
        }

        val recyclerView = _binding.rvTasks
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            Log.d("Fragment", "$task")
            adapter.setData(task)
        }
        )


        _binding.btnTrash.setOnClickListener {
            deleteAllTasks()
        }


        _binding.searchView.setOnCloseListener {
            _binding.searchView.setOnQueryTextListener(this)
            return@setOnCloseListener true
        }
    }

    private fun deleteAllTasks() {
        mTaskViewModel.deleteAllTasks()
        view?.let { Snackbar.make(it, "Задачи удалены", Snackbar.LENGTH_LONG).show() }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null) {
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"


        mTaskViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                listAdapter.setData(it)
            }
        })
    }
}
