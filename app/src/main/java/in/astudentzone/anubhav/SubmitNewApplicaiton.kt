package `in`.astudentzone.anubhav

import `in`.astudentzone.anubhav.daos.PostDao
import `in`.astudentzone.anubhav.models.Post
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_submit_new_applicaiton.*
import kotlinx.coroutines.GlobalScope

class SubmitNewApplicaiton : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    val postCollections = db.collection("postsDatabase")
    private lateinit var postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_new_applicaiton)

        val postDao = PostDao()



        //for test
        clientMobileNumberSubmission.setOnClickListener {
            val phoneNumber = clientMobileNumber.text.toString().trim()
            /*  val postCollection=postDao.postCollections
            val query = postCollection.whereEqualTo("phoneNo", phoneNumber)*/
            // query.equals(phoneNumber)

            //if  (phoneNumber.isNotEmpty() and (postDao.userExists(phoneNumber).toString()==(true).toString()) )
            if (phoneNumber.isNotEmpty()) {
                getResult(phoneNumber)


            } else if (phoneNumber.isEmpty()){
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }


        submitButton.setOnClickListener {
            val phoneNumber = clientMobileNumber.text.toString().trim()
            val property = propertPG.text.toString().trim()
            val cityRef = city.text.toString().trim()
            val areaRef = areaLocality.text.toString().trim()
            val ownerNameRef = ownerName.text.toString().trim()
            val preferredLangRef = preferredLang.text.toString().trim()
            if (phoneNumber.isNotEmpty() and property.isNotEmpty() and cityRef.isNotEmpty() and areaRef.isNotEmpty()) {
                postDao.addPost(
                    phoneNumber,
                    property,
                    cityRef,
                    areaRef,
                    ownerNameRef,
                    preferredLangRef,
                    applicationStatus="pending"
                )

            }
            Toast.makeText(this, "user registered ", Toast.LENGTH_SHORT).show()
            finish()

        }


    }

//    private fun openDialog(): AlertDialog? {
//
//    val exampleDialog : Dialog= Dialog(context)
//    exampleDialog.show(supportFragmentManager, "Dialogg")
//    }


    private fun getResult(phone: String) {
        val returnResult: Boolean= true
        // val docRef = postCollections.whereEqualTo("phoneNo", phone)

        // [START listen_document]
        val docRef = postCollections.document(phone).get()
        docRef.addOnSuccessListener { document ->


            if (document != null && document.exists()) {
                Log.d("TAG", "Current data1: ${document.data}")


                //if here open new activity
                Toast.makeText(this, "user already exits", Toast.LENGTH_SHORT).show()
                finish()

            } else {
                Log.d("TAG", "Current data2: null")
                viewValues()
            }
        }
        // [END listen_document]

    }

    fun viewValues(){
        propertPG.visibility = View.VISIBLE
        city.visibility = View.VISIBLE
        areaLocality.visibility = View.VISIBLE
        ownerName.visibility = View.VISIBLE
        preferredLang.visibility = View.VISIBLE
        submitButton.visibility = View.VISIBLE }

    fun hideValues(){
        propertPG.visibility = View.GONE
        city.visibility = View.GONE
        areaLocality.visibility = View.GONE
        ownerName.visibility = View.GONE
        preferredLang.visibility = View.GONE
        submitButton.visibility = View.GONE }

}