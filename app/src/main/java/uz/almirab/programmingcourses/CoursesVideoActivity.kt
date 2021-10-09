package uz.almirab.programmingcourses

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class CoursesVideoActivity : YouTubeBaseActivity() {
    private lateinit var youTubeVideos: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses_video)

        youTubeVideos = findViewById(R.id.videowatching)
        val bundle: Bundle? = intent.extras
        val video_id: String? = bundle!!.getString("video_id")
        initializePlayer(video_id.toString())
    }

//

    private fun initializePlayer(video_id: String) {
        youTubeVideos.initialize(getString(R.string.GOOGLE_API_KEY),
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {

                    p1!!.setFullscreen(true)
                    p1.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
                    p1.loadVideo(video_id)
                    p1.play()
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    TODO("Not yet implemented")
                }
            })

    }
}