import pt.isel.canvas.*


fun writeTitle(title:String){
    println("<<------------$title------------>>")
}

fun readInt(range: List<Int>):Int{
    while (true){
        val type = readLine()!!.trim()
        if (type.toInt() in range) return type.toInt()
        println("Try again!")
    }
}

fun drawArena(game :MutableList<String>){
    println("   |   |   ")
    println(" ${game[0]} | ${game[1]} | ${game[2]} ")
    println("   |   |   ")
    println("------------")
    println("   |   |   ")
    println(" ${game[3]} | ${game[4]} | ${game[5]} ")
    println("   |   |   ")
    println("------------")
    println("   |   |   ")
    println(" ${game[6]} | ${game[7]} | ${game[8]} ")
    println("   |   |   ")
    writeTitle("")
}

fun playerMove(game: Game) :Game{
    var place: Int
    do {
        println("Which spot? (1-9)")
        place = readInt((1..9).toList())
    } while (!game.grid[place - 1].has(" "))

    val newGame = game.changeGrid(game.grid, place-1, "X")


    return newGame
}

fun compMove(game: Game):Game {

    for (move in game.spotsAvailable){
        val copiedGame = game.changeGrid(game.grid, move, "O")
        if (checkWin(copiedGame.grid, "O")) return copiedGame
    }

    for (move in game.spotsAvailable){
        val copiedGame = game.changeGrid(game.grid, move, "X")
        if (checkWin(copiedGame.grid, "X")) return  game.changeGrid(game.grid, move, "O")
    }

    if (4 in game.spotsAvailable) return game.changeGrid(game.grid, 4, "O")

    for (move in game.spotsAvailable)
        if (move in listOf(1, 3, 7, 9)) return game.changeGrid(game.grid, move, "O")

    return game.changeGrid(game.grid, game.spotsAvailable.random(), "O")
}

fun Game.changeGrid(grid: List<String>, idx: Int, c: String): Game{
    val copiedGrid = grid.toMutableList()
    copiedGrid[idx] = c
    return Game(copiedGrid, copiedGrid.updateSpotsAvailable(), !turn)
}

fun String.has(c:String) = this == c

fun checkWin(spots: MutableList<String>, c:String):Boolean = spots[0] == c && spots[1] == c && spots[2] == c ||
        spots[3] == c && spots[4] == c && spots[5] == c ||
        spots[6] == c && spots[7] == c && spots[8] == c ||
        spots[0] == c && spots[3] == c && spots[6] == c ||
        spots[1] == c && spots[4] == c && spots[7] == c ||
        spots[2] == c && spots[5] == c && spots[8] == c ||
        spots[0] == c && spots[4] == c && spots[8] == c ||
        spots[2] == c && spots[4] == c && spots[6] == c

fun Game.doPlay(enemyC : String) = !checkWin(grid, enemyC) && spotsAvailable.isNotEmpty()

fun List<String>.updateSpotsAvailable(): List<Int> = mapIndexedNotNull { idx, spot -> if (spot.has(" ")) idx else null }

data class Game(val grid: MutableList<String>, val spotsAvailable: List<Int>, val turn :Boolean)
data class Position(val x:Int, val y:Int)
data class Block(val pos: Position, val char: String)

const val WIDTH = 360
const val HEIGHT = 360
const val BLOCK_WIDTH = WIDTH/3
const val BLOCK_HEIGHT = HEIGHT/3
const val BORDER = 6

fun main() {
    writeTitle("Tic Tac Toe")

    onStart {
        var game = Game(MutableList(9){" "}, (1..9).toList(), true)
        val arena = Canvas(WIDTH, HEIGHT, WHITE)
        drawArena(game.grid)
        arena.drawGrid(game.grid)

            arena.onTimeProgress(10){
                if (game.grid.updateSpotsAvailable().isNotEmpty()) {
                    game = game.update()
                    drawArena(game.grid)
                    arena.drawGrid(game.grid)
                }
            }


        /*while (game.grid.any { it == " " }) {

            if (game.doPlay("O")) game = playerMove(game.grid)
            else if (checkWin(game.grid, "O")) {
                println("Computer wins! :(")
                break
            }

            if (game.doPlay("X")) {
                game = compMove(game)
                drawArena(game.grid)
            }
            else if(checkWin(game.grid, "X")) {
                println("Player wins! :)")
                break
            }

            if (game.spotsAvailable.isEmpty()) {
                println("Tied game!")
                break
            }
        }*/
    }
    onFinish { println("Bye bye!") }
}

fun Game.update():Game{
    val newGame :Game = copy()

    if (doPlay("O") && turn) return playerMove(newGame)
    else if (checkWin(grid, "O")) {
        println("Computer wins! :(")
    }

    if (doPlay("X")&& !turn) {
        return compMove(newGame)
    }
    else if(checkWin(grid, "X")) {
        println("Player wins! :)")
    }

    if (spotsAvailable.isEmpty()) {
        println("Tied game!")
    }
    return newGame
}

fun Canvas.drawGrid(grid: MutableList<String>){
    erase()

    drawLine(WIDTH/3, 0,WIDTH/3, HEIGHT, BLACK, BORDER )
    drawLine(2*WIDTH/3, 0, 2*WIDTH/3, HEIGHT, BLACK, BORDER )
    drawLine(0, HEIGHT/3,WIDTH, HEIGHT/3, BLACK, BORDER )
    drawLine(0, 2*HEIGHT/3, WIDTH, 2*HEIGHT/3, BLACK, BORDER )

    grid.forEachIndexed { idx, str ->
        val pos = idx.toPos()
        if (str == "X") drawX(Block(pos, "X"))
        if (str == "O") drawO(Block(pos, "O"))
    }
}

const val DISTANCE_CONSTANT = 20

fun Canvas.drawX(b: Block){
    drawLine(b.pos.x+DISTANCE_CONSTANT, b.pos.y+DISTANCE_CONSTANT, b.pos.x + BLOCK_WIDTH-DISTANCE_CONSTANT, b.pos.y + BLOCK_HEIGHT-DISTANCE_CONSTANT, GREEN, BORDER)
    drawLine(b.pos.x+ BLOCK_WIDTH-DISTANCE_CONSTANT, b.pos.y+DISTANCE_CONSTANT, b.pos.x+DISTANCE_CONSTANT, b.pos.y + BLOCK_HEIGHT-DISTANCE_CONSTANT, GREEN, BORDER)
}

fun Canvas.drawO(b: Block){
    drawCircle(b.pos.x + BLOCK_WIDTH/2, b.pos.y + BLOCK_HEIGHT/2, BLOCK_WIDTH/2 - DISTANCE_CONSTANT, RED, BORDER)
}

fun Int.toPos():Position{
    val x:Int = when(this+1){
        in listOf(1,4,7) -> 0
        in listOf(2,5,8) -> WIDTH/3
        else -> 2*WIDTH/3
    }

    val y:Int = when(this+1){
        in 1..3 -> 0
        in 4..6 -> HEIGHT/3
        else -> 2*HEIGHT/3
    }
    return Position(x, y)
}