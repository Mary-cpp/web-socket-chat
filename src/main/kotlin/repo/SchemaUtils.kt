package repo

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import repo.tables.ChatAbonentsTable
import repo.tables.ChatsTable
import repo.tables.MessagesTable
import repo.tables.UsersTable
import java.io.File

class DbUtils{

    fun connect(path: String){
        setConnection(getDataBaseConfigurationFromJson(path))
    }

    private val logger : Logger = LoggerFactory.getLogger(DbUtils::class.java)
    private fun getDataBaseConfigurationFromJson(path: String) : DatabaseConfig{
        return try {
            val confFile = File(path)
            Json.decodeFromString<DatabaseConfig>(confFile.readText())
        } catch (e: RuntimeException){
            logger.error("Error reading of finding file!\n$e")
            DatabaseConfig.empty()
        }
    }

    private fun setConnection(conf: DatabaseConfig){
        if(conf.url.isNotEmpty())
            Database.connect(
                HikariDataSource(
                    HikariConfig().apply {
                        jdbcUrl = conf.url
                        username = conf.username
                        driverClassName = conf.driver
                        password = conf.password
                        maximumPoolSize = 5
                    }))
    }

    companion object{
        fun migration(){
            transaction {
                SchemaUtils.create(tables = arrayOf(UsersTable, ChatsTable, MessagesTable, ChatAbonentsTable), inBatch = true)
            }
        }
    }
}

