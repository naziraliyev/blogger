package uz.almirab.programmingcourses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    fun NextPage(view: android.view.View) {
        val  intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    fun NextPageregistr(view: android.view.View) {
        val  intent = Intent(this,RegistrationActivity::class.java)
        startActivity(intent)
    }
}