package com.dmitry.notes.screens.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.notes.R
import com.dmitry.notes.databinding.FragmentMainBinding
import com.dmitry.notes.databinding.FragmentNoteBinding
import com.dmitry.notes.model.AppNote
import com.dmitry.notes.screens.main.MainAdapter
import com.dmitry.notes.screens.main.MainFragmentViewModel
import com.dmitry.notes.utilits.APP_ACTIVITY

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteBinding == null")

    private lateinit var noteViewModel: NoteFragmentViewModel
    private lateinit var currentNote: AppNote

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        currentNote = arguments?.getSerializable("note") as AppNote
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        binding.noteText.text = currentNote.text
        binding.noteName.text = currentNote.name
        noteViewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                noteViewModel.delete(currentNote) {
                    APP_ACTIVITY.mNavController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}