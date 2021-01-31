fun min(a:Float, b:Float) = if (a<b) a else b

fun List<Float>.min():Float{
    var min = this[0]
    for (idx in 0 until size) min = min(min, this[idx])
    return min
}

fun sort1(vals:List<Float>) :List<Float> {
    val newVals = vals.toMutableList()
    val sortedList = mutableListOf<Float>()

    while (sortedList.size < vals.size){
        val min = newVals.min()
        newVals.remove(min)
        sortedList.add(min)
    }
    return sortedList
}
