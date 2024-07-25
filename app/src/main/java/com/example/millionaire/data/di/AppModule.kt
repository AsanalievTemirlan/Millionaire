package com.example.millionaire.data.di

import android.content.Context
import androidx.room.Room
import com.example.millionaire.data.db.MillionaireDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MillionaireDataBase =
        Room.databaseBuilder(context, MillionaireDataBase::class.java, "MillionaireDB").allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideQuestionDao(db: MillionaireDataBase) = db.millionaireDao()
}