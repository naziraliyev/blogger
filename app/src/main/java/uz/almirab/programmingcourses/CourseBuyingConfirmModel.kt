package uz.almirab.programmingcourses


data class CourseBuyingConfirmModel (
    val id: Int,
    val icon: String,
    val title: String,
    val payment_status: Int,
    val lesson_count: Int,
    val price: String
)