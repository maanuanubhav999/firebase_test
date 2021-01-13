package `in`.astudentzone.anubhav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataEntry.setOnClickListener {
            val intent = Intent(this, SubmitNew::class.java)
            startActivity(intent)
            finish()
        }

        dataValidation.setOnClickListener {
            //intent
        }
    }
}