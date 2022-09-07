package com.dmitry.notes.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.notes.R
import com.dmitry.notes.databinding.FragmentMainBinding
import com.dmitry.notes.model.AppNote
import com.dmitry.notes.utilits.APP_ACTIVITY

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var mainFragmentRecyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var observerList: Observer<List<AppNote>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        adapter = MainAdapter()
        mainFragmentRecyclerView = binding.recyclerView
        mainFragmentRecyclerView.adapter = adapter
        observerList = Observer {
            val list = it.asReversed()
            adapter.setList(list)
        }
        mainFragmentViewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        mainFragmentViewModel.allNotes.observe(this, observerList)
        binding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mainFragmentViewModel.allNotes.removeObserver(observerList)
        mainFragmentRecyclerView.adapter = null
    }

    companion object{
        fun click(note: AppNote){
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }
}