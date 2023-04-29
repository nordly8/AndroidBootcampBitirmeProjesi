package com.acikgoz.menuapp.dependency

import com.acikgoz.menuapp.data.repo.YemeklerRepository
import com.acikgoz.menuapp.retrofit.ApiUtils
import com.acikgoz.menuapp.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(ydao:YemeklerDao) : YemeklerRepository {
        return YemeklerRepository(ydao)
    }

    @Provides
    @Singleton
    fun provideYemekDao() : YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }
}