package `in`.astudentzone.anubhav.models

data class Post(
    val createdBy: User = User(),
    val createdAt: Long = 0L,
    val phoneNo: String ="",
    val property: String= "",
    val city: String = "",
    val area: String="",
    val owner: String= "",
    val preferredLanguage: String="",
    val applicationStatus:String= ""
)