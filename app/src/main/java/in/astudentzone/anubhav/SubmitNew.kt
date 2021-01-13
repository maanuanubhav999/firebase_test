package `in`.astudentzone.anubhav

import `in`.astudentzone.anubhav.daos.PostDao
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_submit_new.*

class SubmitNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_new)

        val postDao: PostDao

        toSubmitNewApplication.setOnClickListener {
            val intent2= Intent(this, SubmitNewApplicaiton::class.java)
            startActivity(intent2)

        }


    }
}