package com.example.artbook.dependency_injection

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artbook.R
import com.example.artbook.Util.Util
import com.example.artbook.api.RetrofitApi
import com.example.artbook.repo.ArtRepository
import com.example.artbook.repo.ArtRepositoryInterface
import com.example.artbook.service.ArtDao
import com.example.artbook.service.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by aarslan on 20/02/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDb(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, ArtDatabase::class.java, "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectRoomDao(database: ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitApi(): RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : ArtDao, api:RetrofitApi) = ArtRepository(dao,api) as ArtRepositoryInterface

    @Singleton
    @Provides
    fun injectGlides(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

}