package uz.almirab.programmingcourses

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orhanobut.hawk.Hawk
import uz.almirab.programmingcourses.PrefUtils.PrefUtils
import uz.almirab.programmingcourses.RoomDatabase.CourseDatabase
import uz.almirab.programmingcourses.RoomDatabase.Note
import uz.almirab.programmingcourses.databinding.CoursesListBuyingBinding

class CoursesBuyingAdapter(
    val context: Context, val coursebuying: List<CourseBuyingList>,
    val mOnItemClickListener: OnItemClickListener
) :

    RecyclerView.Adapter<CoursesBuyingAdapter.MyViewHolder>() {
    private val courseDatabase = CourseDatabase.geInstance(context)

    //var mOnItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CoursesListBuyingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            mOnItemClickListener, courseDatabase
        )

    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val coursebuying = coursebuying[position]
        holder.setData(coursebuying, position)
        Hawk.init(context).build()


    }

    override fun getItemCount(): Int {
        return coursebuying.size
    }

    inner class MyViewHolder(
        val binding: CoursesListBuyingBinding,
        val mOnItemClickListener: OnItemClickListener, courseDatabase: CourseDatabase?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(courseb: CourseBuyingList?, position: Int) {
            val note: Note? = null
            binding.courseItem.setOnClickListener {

//                mOnItemClickListener.onItemClick(Integer.parseInt(courseb?.price))


            }
            var icon_base_url = "http://blogger.almirab.uz/public/images/api/"
            Glide.with(itemView.context).load(icon_base_url + courseb?.icon)
                .skipMemoryCache(true)
                .into(binding.courseIconB)
            var clicked = false
            var row_index = 0
            var m = 0
            binding.courseItem.setOnClickListener {
                clicked = true
                row_index = position
                var s = 0
                m++
                if (row_index == position) {

                    if (m % 2 == 1) {

                        binding.courseItemS.setBackgroundColor(Color.parseColor("#05294b"))
                        binding.courseItem.setCardBackgroundColor(R.drawable.course_background_selected)
                        s += Integer.parseInt(courseb?.price)
                        mOnItemClickListener?.onItemClick(s)
                        mOnItemClickListener?.getOnIdClick(courseb!!.id)
                        courseDatabase?.dao()?.insert(Note(courseb!!.id.toLong(), courseb.title, courseb.price))
                        PrefUtils.setCourses(courseb!!)
                        Toast.makeText(
                            context,
                            "${PrefUtils.setCourses(courseb)}",
                            Toast.LENGTH_LONG
                        ).show()
                       Toast.makeText(context,"data ${courseDatabase?.dao()?.getAllCourse()}",Toast.LENGTH_LONG).show()
                        clicked = false
                    } else {
                        s -= Integer.parseInt(courseb?.price)
                        binding.courseItemS.setBackgroundColor(Color.parseColor("#04567e"))
                        binding.courseItem.setCardBackgroundColor(R.drawable.course_background_selected)
                        mOnItemClickListener?.onItemClick(s)
                        courseDatabase?.dao()
                            ?.delete(Note(courseb!!.id.toLong(), courseb.title, courseb.price))
                        PrefUtils.removeCourses(courseb!!)
                        Toast.makeText(
                            context,
                            "${PrefUtils.removeCourses(courseb)}",
                            Toast.LENGTH_LONG
                        ).show()
                        clicked = false
                    }
                } else {
                    binding.courseItemS.setBackgroundColor(Color.parseColor("#04567e"))
                    binding.courseItem.setCardBackgroundColor(R.drawable.course_background_item)

                    clicked = false
                }

            }

            val course_id = courseb?.id
            binding.courseTitleB.text = courseb?.title
            binding.coursePrise.text = courseb?.price + " so'm"

        }
    }

    interface OnItemClickListener {
        fun onItemClick(index: Int)
        fun getOnIdClick(id: Int)
    }

}

