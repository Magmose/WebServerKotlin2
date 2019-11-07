import java.net.ServerSocket
import kotlin.concurrent.thread

class Server(val port: Int = 4711) {
    var running = true

    fun handle(request: Request, response: Response) {
        response.body.append("<p>Hej ${request.resource}</p>")
        response.send()
        //println(request.resource)
        //println("<p>hej</p>")
    }

    fun start() {
        val serverSocket = ServerSocket(port)
        while (running) {
            val socket = serverSocket.accept()
            thread {
                handle(Request(socket.getInputStream()), Response(socket.getOutputStream()))
            }
        }
    }

}

fun main() {
    println("Starting server")
    Server().start()
}

