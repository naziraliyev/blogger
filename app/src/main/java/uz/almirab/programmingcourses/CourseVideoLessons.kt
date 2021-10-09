package uz.almirab.programmingcourses

data class CourseVideos (
    val video_id: String,
    val title: String,
    val description: String,
    val imagUri: Int,
    val status: Int,//video status
    val course_id: Int,
    val created_date: String
)
