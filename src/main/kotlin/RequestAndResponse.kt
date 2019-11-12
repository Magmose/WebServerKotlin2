import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream


enum class Method { GET, PUT, POST, DELETE }

val requestText = """
    GET /resource HTTP/1.1
    
    
""".trimIndent()
//val bytes = "Hello world!".toByteArray().inputStream()
//val bin = bytes.bufferedReader()


class Request(input: InputStream) {
    val resource: String
    val method: Method
    val headers = mutableMapOf<String,String>()
    val contentLength : Int
    init {
        val reader = input.bufferedReader()
        var line = reader.readLine()
        val parts = line.split(" ")
        resource = parts[1].substring(1)
        method = Method.valueOf(parts[0])
        line = reader.readLine()
        while(line.isNotBlank()){
            val headerPart = line.split(":")
            headers[headerPart[0].trim()] = headerPart[1].trim()
            line = reader.readLine()
        }

        contentLength = contentLengthRead(headers["Content-Length"])
    }
    private fun contentLengthRead (text:String?) : Int {
        return text?.toInt() ?: 0
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
    val bytes ="""
    GET /member HTTP/1.1
    Content-Type: text/html; charset=UTF-8
    Content-Length: 4
    Connection: close

    kage
    """.trimIndent().toByteArray()
    //println(bin.readLine())
    val output = ByteArrayOutputStream(1024)
    val writer = output.bufferedWriter()
    val request = Request(ByteArrayInputStream(bytes))
    println(request.contentLength)
    println(request.)

}


