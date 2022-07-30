package com.example.movie_demo.di


import com.example.movie_demo.data.datasource.remote.MovieApi
import com.example.movie_demo.data.repository.RemoteMovieRepository
import com.example.movie_demo.domain.repository.IRemoteMovieRepository
import com.example.movie_demo.domain.use_case.GetMovieList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun providesMovieRepository(breedService: MovieApi): IRemoteMovieRepository =
        RemoteMovieRepository(breedService)

    @Provides
    @Singleton
    fun provideGetMovieListUseCase(
        repository: IRemoteMovieRepository,
    ): GetMovieList {
        return GetMovieList(iRemoteMovieRepository = repository)
    }

}