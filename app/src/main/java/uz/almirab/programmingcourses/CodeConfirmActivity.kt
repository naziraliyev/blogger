package uz.almirab.programmingcourses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.almirab.programmingcourses.databinding.ActivityCodeConfirmBinding

class CodeConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCodeConfirmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun CodeConfirm(view: android.view.View) {
        val  intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}