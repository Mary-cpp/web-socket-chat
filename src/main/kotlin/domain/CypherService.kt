package domain

interface CypherService {
    fun encode(value: String): String
    fun decode(value: String): Any
}