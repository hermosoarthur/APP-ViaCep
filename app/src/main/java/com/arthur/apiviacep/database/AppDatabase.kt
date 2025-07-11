package com.arthur.apiviacep.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arthur.apiviacep.dao.EnderecoDao
import com.arthur.apiviacep.model.Endereco

@Database(entities = [Endereco::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun enderecoDao(): EnderecoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "endereco_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
