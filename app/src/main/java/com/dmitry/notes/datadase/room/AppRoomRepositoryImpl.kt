package com.dmitry.notes.datadase.room

import androidx.lifecycle.LiveData
import com.dmitry.notes.datadase.DatabaseRepository
import com.dmitry.notes.model.AppNote

class AppRoomRepositoryImpl(private val appRoomDao: AppRoomDao) : DatabaseRepository {
    override val allNote: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }
}