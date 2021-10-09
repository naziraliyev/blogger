package uz.almirab.programmingcourses

data class CourseList (
    var courses: List<Course>,
    var motivation_videos: List<MotivationVideo>
)

data class Course (
    val id: Int,
    val icon: String,
    val title: String,
    val payment_status: Int,
    val lesson_count: Int
)

data class MotivationVideo (
    val video_id: String,
    val title: String,
    val description: String,
    val created_date: String
)
