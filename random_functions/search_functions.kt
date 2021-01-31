
fun find1(vals:List<Float>, elem:Float):Int?{
    vals.forEachIndexed{ idx, num ->
        if (num == elem) return idx
    }
    return null
}

fun find2(vals:List<Float>, elem:Float):Int?{
    for (idx in 0 until vals.size){
        if (vals[idx] == elem) return idx
    }
    return null
}

fun find3(vals:List<Float>, elem:Float):Int?{
    var idx = 0
    while (idx < vals.size){
        if (vals[idx] == elem) return idx
        ++idx
    }
    return null
}

fun find4(vals:List<Float>, elem:Float):Int?{
    repeat(vals.size){ idx ->
        if (vals[idx] == elem) return idx
    }
    return null
}

fun findR1(vals:List<Float>, elem: Float):Int? = when{
    vals.isEmpty()  -> null
    vals[0] == elem -> 0
    else ->{
        val idx = findR1(vals.drop(1), elem)
        if (idx == null) null else idx+1
    }
}

fun findR2(vals:List<Float>, elem: Float, from :Int = 0):Int? = when{
    from > vals.size -> null
    vals[from] == elem -> from
    else ->findR2(vals, elem, from + 1)
}

fun search1(vals:List<Float>, elem:Float, from:Int = 0, to:Int=vals.size-1):Int?{
    if (from > to) return null
    val midIdx = (to+from)/2
    val mid = vals[midIdx]

    return when {
        mid == elem -> midIdx
        mid > elem  -> search1(vals, elem, from, midIdx-1)
        else        -> search1(vals, elem, midIdx+1, to)
    }
}

fun search2(vals:List<Float>, elem:Float, from:Int = 0, to:Int=vals.size-1):Int?{
    var from2 = from
    var to2 = to

    while (from2 <= to2){
        val midIdx = (to2+from2)/2
        val mid = vals[midIdx]

        when {
            mid == elem -> return midIdx
            elem > mid -> from2 = midIdx + 1
            else -> to2 = midIdx - 1
        }
    }
    return null
}
