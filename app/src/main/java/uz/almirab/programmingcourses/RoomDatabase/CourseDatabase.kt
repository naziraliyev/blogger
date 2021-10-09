package uz.almirab.programmingcourses.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1,exportSchema = false)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun dao(): NoteDao

    companion object{
        private var instance: CourseDatabase? = null
        fun geInstance(context: Context):CourseDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context,CourseDatabase::class.java,"courseDb")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}