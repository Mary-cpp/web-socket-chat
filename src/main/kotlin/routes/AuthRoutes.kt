package routes

import domain.ProfileAuthDTO
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.authRouting(){
    route("/user"){
        post("/auth") {
            val reqBody = call.receive<ProfileAuthDTO>()

        }
        post("/reg"){
        }
    }
}