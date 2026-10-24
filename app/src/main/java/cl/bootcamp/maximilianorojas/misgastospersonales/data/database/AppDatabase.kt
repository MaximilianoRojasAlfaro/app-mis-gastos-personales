package cl.bootcamp.maximilianorojas.misgastospersonales.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [GastoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gastoDAO(): GastoDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {

            }
        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mis_gastos_personales_database"

                ).addMigrations(MIGRATION).build()
                INSTANCE = instance
                instance
            }
        }
    }
}