import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream


enum class Method { GET, PUT, POST, DELETE }

val requestText = """
    GET /resource HTTP/1.1
    
    
""".trimIndent()
val bytes = "Hello world!".toByteArray().inputStream()
val bin = bytes.bufferedReader()


class Request(input: InputStream) {
    val resource: String
    val method: Method

    init {
        val reader = input.bufferedReader()
        val line = reader.readLine()
        val parts = line.split(" ")
        resource = parts[1].substring(1)
        method = Method.valueOf(parts[0])
    }
}

class Response(private val output: OutputStream) {
    val body = StringBuilder()
    fun append(text: String) {
        body.append(text)
    }

    fun send() {
        val head ="""
        HTTP/1.1 200 OK
        Content-Type: text/html; charset=UTF-8
        Content-Length: ${body.length}
        Connection: close

        """.trimIndent()
        val writer = output.bufferedWriter()
        writer.append(head)
        writer.newLine()
        writer.append(body)
        writer.flush()
        writer.close()
    }

}

fun main() {
    println(bin.readLine())
    val output = ByteArrayOutputStream(1024)
    val writer = output.bufferedWriter()


}


