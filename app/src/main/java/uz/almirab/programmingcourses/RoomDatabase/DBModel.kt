package uz.almirab.programmingcourses.RoomDatabase

data class DBModel (
    val id: Int,
    val icon: String,
    val title: String,
    val payment_status: Int,
    val lesson_count: Int,
    val price: String
)