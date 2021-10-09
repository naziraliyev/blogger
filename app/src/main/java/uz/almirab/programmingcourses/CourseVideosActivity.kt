package uz.almirab.programmingcourses

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.almirab.programmingcourses.databinding.ActivityCourseBinding

class CourseVideosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val courseID = bundle!!.getInt("course_id")
        val courseTitle = bundle!!.getString("course_title")

        binding.courseTitle.text = courseTitle + " Darslar"

        /**
         * Retreive Data with Retrofit 2
         * */
        var retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchCourseVideos(courseID).enqueue(object : Callback<List<CourseVideos>> {
            override fun onResponse(
                call: Call<List<CourseVideos>>,
                response: Response<List<CourseVideos>>
            ) {
                d("onResponse", "${response.body()!![0]}")
                showVidos(response.body()!!)
            }

            override fun onFailure(call: Call<List<CourseVideos>>, t: Throwable) {
                d("Error", "onFailure: ${t}")
            }
        })

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.coursesVideosRecyclerView.layoutManager = layoutManager
    }

    private fun showVidos(videos: List<CourseVideos>) {
        val courseVideoAdapter = CourseVideosAdapter(this, videos)
        binding.coursesVideosRecyclerView.adapter = courseVideoAdapter
    }

}