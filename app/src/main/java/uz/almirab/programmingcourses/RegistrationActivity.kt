package uz.almirab.programmingcourses

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.almirab.programmingcourses.databinding.ActivityMainBinding
import uz.almirab.programmingcourses.databinding.ActivityRegistrBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrBinding.inflate(layoutInflater)
        setContentView(binding.root)
}

    fun RegistrBtn(view: android.view.View) {
        val  intent = Intent(this,CodeConfirmActivity::class.java)
        startActivity(intent)
    }
}