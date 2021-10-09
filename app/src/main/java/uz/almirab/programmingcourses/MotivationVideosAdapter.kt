package uz.almirab.programmingcourses

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.almirab.programmingcourses.databinding.MotivationVideosListBinding

class MotivationVideosAdapter(val context: Context, val motivationVideos: List<MotivationVideo>) : RecyclerView.Adapter<MotivationVideosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MotivationVideosListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val video = motivationVideos[position]
        holder.setData(video, position)
    }

    override fun getItemCount(): Int {
        return  motivationVideos.size
    }

    inner class MyViewHolder(val binding: MotivationVideosListBinding) : RecyclerView.ViewHolder(binding.root){
        fun setData(video: MotivationVideo?, position: Int) {

            Glide.with(itemView.context).load("https://img.youtube.com/vi/"+video!!.video_id+"/0.jpg")
                .skipMemoryCache(false)
                .into(binding.motivationVideoImage)
            binding.motivationVideoTitle.text = video.title
            binding.motivationVideoDescription.text = video.description

            binding.courseVideoItem.setOnClickListener {
                val video_id :String = video.video_id
                val video_title :String = video.title
                val intent = Intent(itemView.context, CoursesVideoActivity::class.java)
                intent.putExtra("video_id", video_id)
                intent.putExtra("video_title", video_title)
                itemView.context.startActivity(intent)

            }
        }
    }
}
