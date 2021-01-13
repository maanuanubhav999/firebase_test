package `in`.astudentzone.anubhav.daos

import `in`.astudentzone.anubhav.SubmitNewApplicaiton
import `in`.astudentzone.anubhav.models.Post
import `in`.astudentzone.anubhav.models.User
import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class PostDao {

    var TAG = 12

    private val db = FirebaseFirestore.getInstance()
    val postCollections = db.collection("postsDatabase")
    private val auth = Firebase.auth

    fun addPost(
        phone: String,
        property: String,
        city: String,
        area: String,
        owner: String,
        preferredLang: String,
        applicationStatus: String
    ) {
        val currentUserId = auth.currentUser!!.uid
        //current user data
        GlobalScope.launch {
            val userDao = UserDao()
            val user = userDao.getUserById(currentUserId).await().toObject(User::class.java)

            val currentTime = System.currentTimeMillis()
            val post =
                user?.let {
                    Post(
                        it,
                        currentTime,
                        phone,
                        property,
                        city,
                        area,
                        owner,
                        preferredLang,
                        applicationStatus = "pending"
                    )
                }

            //entering to post
            if (post != null) {
                postCollections.document(post.phoneNo).set(post)
            }
        }
    }
/*
    fun checkIfPhoneExists(phone: String): Task<DocumentSnapshot> {
        return postCollections.document(phone).get()

    }

      fun userExists(phone: String) :Boolean{
        var returnData : Boolean= false


        GlobalScope.launch() {
           val rData = async {
                val data = checkIfPhoneExists(phone).await().toObject(Post::class.java)
                Log.d("TAG", data.toString() + "userexists working")
                val bool = data?.phoneNo


                    if (bool?.isNotEmpty() == true) {
                        returnData = true
                        Log.d("TAG", returnData.toString())

                        return@async returnData


                } else{

                }
            }
        }
        return false

    }*/

    /* suspend fun userExists(phone: String): Boolean{
        var returnData : Boolean = false
        val data: Post
       val scope =  GlobalScope.async {
            val data = checkIfPhoneExists(phone).await().toObject(Post::class.java)
            Log.d("TAG", data.toString() + " user ")

               if(data != null){
                   returnData = true

                   Log.d("TAG", "data returning insdie main "+ returnData)
                    return@async returnData
               } else{}

        }
        *//*if(data.phoneNo!=null) {
            Log.d("TAG", scope.toString())
            returnData = true
        }


*//*
        scope.await()
        Log.d("TAG", "data returning here "+ returnData)
        return returnData


    }*/



}