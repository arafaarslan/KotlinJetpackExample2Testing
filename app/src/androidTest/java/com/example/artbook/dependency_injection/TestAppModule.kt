package com.example.artbook.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.artbook.service.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.components.SingletonComponent
import org.junit.Rule
import javax.inject.Named

/**
 * Created by aarslan on 27/02/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context: Context)=
        Room.inMemoryDatabaseBuilder(context,ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}