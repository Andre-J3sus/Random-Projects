import java.io.File

fun writeTitle(title:String){
    println("<<------------$title------------>>")
}
fun readInt(range: IntRange):Int{
    while (true){
        val type = readLine()!!.trim()
        if (type.toInt() in range) return type.toInt()
        println("Try again!")
    }
}
fun List<String>.search(elem :String):String? {
    forEach { contact ->
        if (contact.split(' ')[0] == elem) return contact
    }
    return null
}

val options = mutableListOf<String>("View Contacts", "Search Contact", "Add Contact")

val file = File("src/main/kotlin/contact_book.txt")


fun main() {
    while (true) {
        val contacts = file.readLines().sorted()
        writeTitle("Contact Book")

        options.forEachIndexed { idx, option -> println("[${idx + 1}] $option") }
        val optionChosen = readInt(1..options.size)

        when (options[optionChosen - 1]) {
            "View Contacts" -> {
                writeTitle("Contacts")
                contacts.forEach { contact -> println(contact) }
            }

            "Search Contact" -> {
                do {
                    print("Contact Name: ")
                    val contactSearched = contacts.search(readLine()!!)
                    println(contactSearched ?: "Contact not found :(")
                } while (contactSearched == null)
            }

            "Add Contact" -> {
                print("Name: ")
                val name = readLine()!!
                print("Number: ")
                val number = readInt(0..999999999)

                file.appendText("\n$name -> $number")
                println("Contact added!")
            }
        }
    }
}
