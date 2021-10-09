package uz.almirab.programmingcourses.RoomDatabase

import androidx.room.*

@Dao
interface  NoteDao {
    @Insert
    fun insert(course: Note)
    @Update
    fun update(course: Note)
    @Delete
    fun delete(course: Note)
    @Query(value = "SELECT * FROM courseDb")
    fun getAllCourse(): List<Note>

}