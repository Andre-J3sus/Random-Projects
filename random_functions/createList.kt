
fun createListInt1(from:Int, to:Int, delta:Int) :List<Int> = (from..to step delta).toList()

fun createListInt2(from:Int, to:Int, delta:Int) :List<Int>{
    val res = mutableListOf<Int>()
    for (i in from .. to step delta){
        res.add(i)
    }
    return res
}

fun createListInt3(from:Int, to:Int, delta:Int) :List<Int>{
    val res = mutableListOf<Int>()
    var i = from
    while (i <= to){
        res.add(i)
        i += delta
    }
    return res
}

fun createListFloat1(from:Float, to:Float, delta:Float) :List<Float>{
    val res = mutableListOf<Float>()
    var i = from
    while (i <= to){
        res.add(i)
        i += delta
    }
    return res
}

fun createListFloat2(from:Float, to:Float, delta:Float) :List<Float>{
    val size = ((to - from) / delta).toInt()
    val res = MutableList<Float>(size){0}
    repeat(size) {
        res.map { from * it + delta }
    }
    return res
}

