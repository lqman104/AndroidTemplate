package com.luqman.template.data.di

import com.luqman.template.data.repository.DataSource
import com.luqman.template.data.repository.LocalDataSource
import com.luqman.template.data.repository.DataRepository
import com.luqman.template.data.repository.RemoteDataSource
import com.luqman.template.data.services.SomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): SomeService = retrofit.create(SomeService::class.java)

    @Provides
    @LocalSource
    fun provideLocalDataSource(): DataSource = LocalDataSource(
        Dispatchers.IO
    )

    @Provides
    @RemoteSource
    fun provideRemoteDataSource(
        someService: SomeService
    ): DataSource = RemoteDataSource(
        someService,
        Dispatchers.IO
    )

    @Provides
    fun provideRepository(
        @RemoteSource remoteDataSource: DataSource,
        @LocalSource localDataSource: DataSource
    ): DataSource = DataRepository(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )
}