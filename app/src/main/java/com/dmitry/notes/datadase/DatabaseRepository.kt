package com.dmitry.notes.datadase

import androidx.lifecycle.LiveData
import com.dmitry.notes.model.AppNote

interface DatabaseRepository {
    val allNote : LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess:()->Unit)
    suspend fun delete(note: AppNote, onSuccess:()->Unit)
}