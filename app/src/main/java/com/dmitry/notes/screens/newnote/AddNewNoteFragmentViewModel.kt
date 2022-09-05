package com.dmitry.notes.screens.newnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.notes.model.AppNote
import com.dmitry.notes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteFragmentViewModel(application: Application): AndroidViewModel(application) {

    fun insert(note: AppNote, onSuccess:()->Unit){
        viewModelScope.launch{
            REPOSITORY.insert(note){
                onSuccess()
            }
        }
    }
}