package `in`.astudentzone.anubhav.daos

import `in`.astudentzone.anubhav.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserDao {
    val db = FirebaseFirestore.getInstance()
    val  usersCollection = db.collection("users")

    fun addUser(user: User?){
        user?.let{
            GlobalScope.launch (Dispatchers.IO){
            usersCollection.document(user.uid).set(it)
        }
        }

    }

    //details about user
    fun getUserById(uId: String): Task<DocumentSnapshot>{
        return usersCollection.document(uId).get()
    }
}