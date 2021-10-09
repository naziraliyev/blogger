package uz.almirab.programmingcourses

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.almirab.programmingcourses.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user_id = 2

        /**
         * Top VideoView Block
         * */
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        val onlineUri: Uri = Uri.parse("http://almirab.uz/video/testvideo.mp4")
//        val offlineUri : Uri = Uri.parse("android.resource://$packageName/${R.raw.testvideo}")

        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(onlineUri)
        binding.videoView.requestFocus()
        binding.buttonRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
//        binding.videoView.start()


        /**
         * Start Courses
         * */
        var retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllCourses(user_id).enqueue(object : Callback<CourseList> {
            override fun onResponse(call: Call<CourseList>, response: Response<CourseList>) {
                var body = response?.body()
                var courses = body?.courses
                var motivation_videos = body?.motivation_videos
//                d("Response", "onResponse: ${motivation_videos}")

                showCourses(courses!!)
                showMotivationVideos(motivation_videos!!)
            }

            override fun onFailure(call: Call<CourseList>, t: Throwable) {
                d("Error", "onFailure: ${t}")
            }

        })

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.coursesRecyclerView.layoutManager = layoutManager

        val motivationLayoutManager = LinearLayoutManager(this)
        motivationLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.motivationVideosRecyclerView.layoutManager = motivationLayoutManager
    }

    private fun showMotivationVideos(motivationVideos: List<MotivationVideo>) {

        val motivationVideoAdapter = MotivationVideosAdapter(this, motivationVideos)
        binding.motivationVideosRecyclerView.adapter = motivationVideoAdapter
    }

    private fun showCourses(courses: List<Course>) {

        val courseAdapter = CoursesAdapter(this, courses)
        binding.coursesRecyclerView.adapter = courseAdapter
    }


}

