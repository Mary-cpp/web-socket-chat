package repo

import kotlinx.serialization.Serializable

@Serializable
data class DatabaseConfig(
    val url: String,
    val driver: String,
    val username: String,
    val password : String,
){
    companion object {
        fun empty() = DatabaseConfig("","", "","")
    }
}
