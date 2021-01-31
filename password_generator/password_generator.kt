
fun readInt(range: IntRange):Int{
    while (true){
        val type = readLine()!!.trim()
        if (type.toInt() in range) return type.toInt()
        println("Try again!")
    }
}

fun writeTitle(title:String){
    println("<<------------$title------------>>")
}

val optionsToAdd = mutableListOf("Lowercase Letters", "Uppercase Letters", "Numbers", "Symbols")
val optionsChosen = mutableListOf<String>()
var password:String = ""
var passwordSize = 0

val lowercaseLetters = ('a'..'z').toList()
val uppercaseLetters = ('A'..'Z').toList()
val symbols = (33..47).toList() + (58..64).toList()


fun main() {
    writeTitle("Password Generator")

    //Password size
    println("How many characters do you want your password? (1-100)")
    passwordSize = readInt(1..100)

    //Password composition
    println("Select at least one option to your password:")
    while (true){
        optionsToAdd.forEachIndexed { idx, option-> println("[${idx + 1}] $option") }
        val numberSelected = readInt(1..optionsToAdd.size)
        val optionSelected = optionsToAdd[numberSelected-1]

        if (optionSelected != "Generate!") {
            optionsChosen.add(optionSelected)
            optionsToAdd.remove(optionSelected)
            println("$optionSelected selected!")
        }

        if(optionSelected == "Generate!" || optionsToAdd.size == 1) break

        if (optionsToAdd.last() != "Generate!") optionsToAdd.add("Generate!")
    }

    //Generating Password
    println("Generating...")
    while (passwordSize > 0) {
        optionsChosen.forEach { option ->
            when (option) {
                "Lowercase Letters" -> password += lowercaseLetters.random()
                "Uppercase Letters" -> password += uppercaseLetters.random()
                "Numbers" -> password += (0..10).random().toString()
                "Symbols" -> password += symbols.random().toChar()
            }
            --passwordSize
        }
    }
    println("Password generated: $password")
}