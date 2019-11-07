import java.io.File


data class Member(val id: Int, val name: String)

fun fileExist() {
    val filename = "chercher tech.txt"
    var fileObject = File(filename)
    var fileExists = fileObject.exists()
    if (fileExists) {
        print("$filename file does exist.")
    } else {
        print("$filename file does not exist.")
    }
}

fun createFile() {
    val fileName = "cherchertech.txt"
    var fileObject = File(fileName)
    // create a new file
    val isNewFileCreated: Boolean = fileObject.createNewFile()
    if (isNewFileCreated) {
        println("$fileName is created successfully.")
    } else {
        println("$fileName already exists.")
    }
}

fun readFile(){
    val file = File("chercher tech.txt")
    file.forEachLine { println(it) }
}

interface WebContent {
    fun save() // persist the content to file / database
}

class ChoirContent(/* filename , ... */) : WebContent {
    fun getMember(): List<Member> =
        TODO(" Implement ␣GET ␣/ member ")

    fun getMember(id: Int): Member? =
        TODO(" Implement ␣GET ␣/ member /7")

    fun putMember(member: Member): Member =
        TODO(" Implement ␣PUT ␣/ member ")

    // ...
    override fun save() {
        TODO(" implement ␣ function ␣ save ")
    }
}

class WebServer(val content: WebContent, val port: Int = 80) {
    fun start() {
        TODO(" Implement ␣ start ")
    }

    fun stop() {
        TODO(" Implement ␣ stop ")
    }
}


fun main() {
    /*val content = ChoirContent(/* filename , ... */)
    val server = WebServer(content, 4711)
    server.start()*/
    createFile();

}