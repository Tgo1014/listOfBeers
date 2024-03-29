package tgo1014.listofarts.data.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import mockwebserver3.MockWebServer
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.io.File


val json = Json { ignoreUnknownKeys = true }

inline fun <reified T> MockWebServer.getService(): T = Retrofit.Builder()
    .baseUrl(this.url("/"))
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()
    .create(T::class.java)

fun String.filePathContentAsString(): String {
    val uri = ClassLoader.getSystemResource(this)
    val file = File(uri.path)
    return String(file.readBytes())
}