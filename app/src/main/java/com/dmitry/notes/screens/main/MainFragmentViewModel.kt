package com.dmitry.notes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dmitry.notes.utilits.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNote
}