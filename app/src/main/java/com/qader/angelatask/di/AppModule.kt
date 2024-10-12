package com.qader.angelatask.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.qader.angelatask.data.local.AppDatabase
import com.qader.angelatask.data.local.MedicineDao
import com.qader.angelatask.data.remote.ApiService
import com.qader.angelatask.data.mapper.MedicineMapper
import com.qader.angelatask.data.repositry.MedicineRepositoryImpl
import com.qader.angelatask.domain.repositry.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule() {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "medicines_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(database: AppDatabase): MedicineDao {
        return database.medicineDao()
    }

    @Provides
    @Singleton
    fun provideMedicineMapper(): MedicineMapper {
        return MedicineMapper()
    }
    @Provides
    @Singleton
    fun provideMedicineRepository(
        apiService: ApiService,
        medicineDao: MedicineDao,
        mapper: MedicineMapper
    ): MedicineRepository {
        return MedicineRepositoryImpl(apiService, medicineDao, mapper)
    }
}
