package com.dmitry.notes.screens.newnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dmitry.notes.R
import com.dmitry.notes.databinding.FragmentAddNewNoteBinding
import com.dmitry.notes.model.AppNote
import com.dmitry.notes.utilits.APP_ACTIVITY
import com.dmitry.notes.utilits.showToast

class AddNewNoteFragment : Fragment() {

    private var _binding: FragmentAddNewNoteBinding? = null
    private val binding: FragmentAddNewNoteBinding
        get() = _binding ?: throw RuntimeException("FragmentAddNewNoteBinding == null")
    private lateinit var addNewNoteFragmentViewModel: AddNewNoteFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initializationViewMode()
    }

    private fun initializationViewMode() {
        addNewNoteFragmentViewModel = ViewModelProvider(this)[AddNewNoteFragmentViewModel::class.java]
        binding.btnAddNote.setOnClickListener {
            val name = binding.inputNameNot.text.toString()
            val text = binding.inputTextNot.text.toString()
            if(name.isEmpty()){
                showToast(getString(R.string.toast_enter_name))
            }else{
                addNewNoteFragmentViewModel.insert(AppNote(name = name, text = text)){
                    APP_ACTIVITY.mNavController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}