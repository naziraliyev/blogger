package uz.almirab.programmingcourses

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.almirab.programmingcourses.databinding.CourseVideosListBinding

class CourseVideosAdapter(val context: Context, val courseVideos: List<CourseVideos>) : RecyclerView.Adapter<CourseVideosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CourseVideosListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val video = courseVideos[position]
        holder.setData(video, position)
    }

    override fun getItemCount(): Int {
        return  courseVideos.size
    }

    inner class MyViewHolder(val binding: CourseVideosListBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(videos: CourseVideos?, position: Int) {
            Glide.with(itemView.context).load("https://img.youtube.com/vi/"+videos!!.video_id+"/0.jpg")
                .skipMemoryCache(false)
                .into(binding.imageVideos)
            binding.courseVideoTitle.text = videos.title
            binding.courseVideoDescription.text = videos.description
            binding.courseVideoItem.setOnClickListener {
                val video_id :String = videos.video_id
                val video_title :String = videos.title
                val intent = Intent(itemView.context, CoursesVideoActivity::class.java)
                intent.putExtra("video_id", video_id)
                intent.putExtra("video_title", video_title)
                itemView.context.startActivity(intent)

            }
        }

    }
}