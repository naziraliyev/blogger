package uz.almirab.programmingcourses

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.almirab.programmingcourses.RoomDatabase.CourseDatabase
import uz.almirab.programmingcourses.databinding.ActivityBuyingCourseBinding
import java.io.Serializable

class CourseBuyingActivity : AppCompatActivity(),CoursesBuyingAdapter.OnItemClickListener  {
    private lateinit var binding: ActivityBuyingCourseBinding
    private var clicked: Boolean = false
    val user_id = 2

    private var courseDatabase:CourseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyingCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Hawk.init(this).build()
        val bundle: Bundle? = intent.extras
        val courseID = bundle!!.getInt("course_id")
        val courseTitle = bundle!!.getString("course_title")
        var s = 0


        binding.specialOffers.setOnClickListener {
            clicked = true
            s++
            if (clicked){
                if (s%2==1) {
                    binding.specialOffersLay.setBackgroundColor(Color.parseColor("#05294b"))
                    binding.specialOffers.setCardBackgroundColor(R.drawable.course_background_selected)
                    clicked = false
                }else{
                    binding.specialOffersLay.setBackgroundColor(Color.parseColor("#ff8200"))
                    binding.specialOffers.setCardBackgroundColor(R.drawable.register_button)
                    clicked = false
                }
            }else {

                binding.specialOffersLay.setBackgroundColor(Color.parseColor("#ff8200"))
                binding.specialOffers.setCardBackgroundColor(R.drawable.register_button)
            }

        }



        var retrofit = Retrofit.Builder()
            .baseUrl("http://blogger.almirab.uz")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllCoursesBuying(user_id).enqueue(object : Callback<CourseBuying> {
            override fun onResponse(call: Call<CourseBuying>, response: Response<CourseBuying>) {
                var body = response?.body()
                var courses = body?.courses

//                d("Response", "onResponse: ${motivation_videos}")

                showCourses(courses!!)
            }

            override fun onFailure(call: Call<CourseBuying>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerviewBuyingCourse.layoutManager = layoutManager

        binding.applyToCourse.setOnClickListener {

            api.fetchAllCoursesBuying(user_id).enqueue(object : Callback<CourseBuying> {
                override fun onResponse(call: Call<CourseBuying>, response: Response<CourseBuying>) {
                    var body = response?.body()
                    var courses = body?.courses

                    intentItems(courses!!)
                }

                override fun onFailure(call: Call<CourseBuying>, t: Throwable) {

                }
            })


        }

    }

    private fun intentItems(coursesBuying: List<CourseBuyingList>) {
        val intent = Intent(this,CourseBuyingConfirmActivity::class.java)
        intent.putExtra("id",coursesBuying.get(0).id)
        startActivity(intent)

    }


    private fun showCourses(coursesBuying: List<CourseBuyingList>) {
        val courseAdapter = CoursesBuyingAdapter(this,coursesBuying,this)
        binding.recyclerviewBuyingCourse.adapter = courseAdapter
    }

    override fun onItemClick(index: Int) {
        val item  = index.toDouble()
        var total = binding.courseItemPrice.text.toString()
        val total1 = item + total.toDouble()

        binding.courseItemPrice.setText(total1.toString())

    }

    override fun getOnIdClick(id: Int) {
   }




}

