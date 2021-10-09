package uz.almirab.programmingcourses

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/dashboard/{user_id}")
    fun fetchAllCourses(@Path("user_id")  user_id: Int) : Call<CourseList>

    @GET("/api/dashboard/{user_id}")
    fun fetchAllCoursesBuying(@Path("user_id") user_id: Int) : Call<CourseBuying>

    @GET("/api/course_videos/{course_id}")
    fun fetchCourseVideos(@Path("course_id")  course_id: Int) : Call<List<CourseVideos>>
}