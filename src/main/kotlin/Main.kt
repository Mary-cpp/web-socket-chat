import io.ktor.network.sockets.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import repo.DbUtils
import routes.authRouting

fun main() {
    DbUtils().connect("databaseConfig.json").apply { DbUtils.migration() }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module(){
    routing {
        authRouting()
    }
}