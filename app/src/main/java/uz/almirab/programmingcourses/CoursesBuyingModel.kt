package uz.almirab.programmingcourses

import java.io.Serializable

data class CourseBuying(
    var courses: List<CourseBuyingList>,
    var motivation_videos: List<MotivationVideoBuying>
    ):Serializable


data class CourseBuyingList (
    val id: Int,
    val icon: String,
    val title: String,
    val payment_status: Int,
    val lesson_count: Int,
    val price: String
):Serializable

data class MotivationVideoBuying (
    val video_id: String,
    val title: String,
    val description: String,
    val created_date: String
)
