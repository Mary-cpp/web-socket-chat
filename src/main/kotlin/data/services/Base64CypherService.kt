package data.services

import domain.CypherService
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Base64CypherService: CypherService {
    @OptIn(ExperimentalEncodingApi::class)
    override fun encode(value: String): String {
        return Base64.encode(value.toByteArray())
    }

    @OptIn(ExperimentalEncodingApi::class)
    override fun decode(value: String): String {
        return Base64.decode(value).decodeToString()
    }
}