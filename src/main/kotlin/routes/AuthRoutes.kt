package routes

import domain.Profile
import domain.ProfileController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

fun Route.userRoutes(userController: ProfileController) {
    route("/user") {
        post("/reg") {
            val entity = call.receive<Profile>()
            call.respond(HttpStatusCode.Created, "Id: ${userController.registerUser(entity)}")
        }
        get("/auth") {
            val authHeaderValue = call.request.headers["Authorization"]
            authHeaderValue?.let { credentials ->
                credentials.replace("Basic ", "").also {values ->
                    userController.cypherService.decode(values).also {
                        LoggerFactory.getLogger(ProfileController::class.java).info("Log:pass = $it")
                        userController.findUserByLogin((it as String).split(':').first())?.also {
                            call.respond("User: $it")
                        } ?: call.respond(HttpStatusCode.OK,"No such User")
                    }
                }
            } ?: call.respond(HttpStatusCode.BadRequest, "Missing Authorization header")
        }
    }
}