package uz.almirab.programmingcourses

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.internal.i
import uz.almirab.programmingcourses.RoomDatabase.CourseDatabase
import uz.almirab.programmingcourses.RoomDatabase.Note
import uz.almirab.programmingcourses.databinding.ActivityCourseBuyingConfirmBinding


class CourseBuyingConfirmActivity : AppCompatActivity() {
    private val courseDatabase =CourseDatabase.geInstance(this)
    private lateinit var binding:ActivityCourseBuyingConfirmBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCourseBuyingConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list = ArrayList<String>()
        //list = i.getSerializableExtra("LIST") as List<MyObject?>
        val mlist = intent.getSerializableExtra("LIST") as? List<CourseBuyingList>
        val note:Note? = null
        binding.textView2.setText("Siz tanlagan courses "+ note?.title+" "+note?.price)


         }


}