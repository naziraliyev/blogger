package uz.almirab.programmingcourses

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.almirab.programmingcourses.databinding.CoursesListBinding

class CoursesAdapter(val context: Context, val courses: List<Course>) : RecyclerView.Adapter<CoursesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CoursesListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val course = courses[position]
        holder.setData(course, position)

    }

    override fun getItemCount(): Int {
        return  courses.size
    }

    inner class MyViewHolder (val binding: CoursesListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(course: Course?, position: Int) {
            var icon_base_url = "http://blogger.almirab.uz/public/images/api/"

            /**/Glide.with(itemView.context).load(icon_base_url + course!!.icon)
                .skipMemoryCache(true)
                .into(binding.courseIcon)

            val course_id = course!!.id

            binding.courseTitle.text = course!!.title
            if(course.payment_status == 1) {
                binding.courseOpened.visibility = View.VISIBLE
                binding.courseClosed.visibility = View.GONE
                binding.courseStatusO.setImageDrawable(itemView.context.getDrawable(R.drawable.status_icon_unlocked))
                binding.courseStatusO.setBackgroundResource(R.drawable.lock_btn_white)
                binding.courseCountO.text = course!!.lesson_count.toString()

                binding.courseItem.setOnClickListener {
                    val intent = Intent(itemView.context, CourseVideosActivity::class.java)
                    intent.putExtra("course_id", course_id)
                    intent.putExtra("course_title", course!!.title)
                    itemView.context.startActivity(intent)

                }

            } else {
                binding.courseClosed.visibility = View.VISIBLE
                binding.courseOpened.visibility = View.GONE
                binding.courseStatusC.setImageDrawable(itemView.context.getDrawable(R.drawable.status_icon_locked))
                binding.courseStatusC.setBackgroundResource(R.drawable.lock_btn_orange)
                binding.courseCountC.text = course!!.lesson_count.toString()
                binding.courseItem.setOnClickListener {
                    val intent = Intent(itemView.context, CourseBuyingActivity::class.java)
                    intent.putExtra("course_id", course_id)
                    intent.putExtra("course_title", course!!.title)
                    itemView.context.startActivity(intent)

                }
            }/**/
        }
    }


}

