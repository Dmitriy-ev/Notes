package com.dmitry.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dmitry.notes.datadase.room.AppRoomDatabase
import com.dmitry.notes.datadase.room.AppRoomRepositoryImpl
import com.dmitry.notes.utilits.REPOSITORY
import com.dmitry.notes.utilits.TYPE_ROOM

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val context = application

    fun initDataBase(type: String, onSuccess:()->Unit){
        when(type){
            TYPE_ROOM ->{
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepositoryImpl(dao)
                onSuccess()
            }
        }
    }
}