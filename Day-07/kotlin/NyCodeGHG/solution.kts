import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

val allBags = arrayListOf<Bag>()

val contentRegex = "([0-9]+) ([\\w]+ [\\w]+) bag[s]?".toRegex()
val noBagsRegex = "([\\w]+ [\\w]+) bags contain no other bags.".toRegex()

val rules = arrayListOf<BagRule>()

Files.readAllLines(Paths.get("input.txt")).forEach { line ->

    val noBagsResult = noBagsRegex.find(line)
    if (noBagsResult != null) {
        val (type) = noBagsResult.destructured
        allBags.add(Bag(type))
        return@forEach
    }

    val parts = line.split(" bags contain ")
    val bagType = parts[0]
    allBags.add(Bag(bagType))

    parts[1].split(", ").map { it.replace(".", "") }.forEach {
        val result = contentRegex.find(it)
        val (count, type) = result?.destructured ?: error("parsing error")
        rules.add(BagRule(bagType, type, count.toInt()))
    }
}
println("${rules.size} Rules")

val workingBags = arrayListOf<Bag>()

rules.forEach { rule ->
    var bag = getBag(rule.root)
    bag.bags.add(getBag(rule.type))
}

var counter = 0
workingBags.forEach {
    if (it.bags.containsShinyGolden()) {
        counter++
    }
}
println(counter)

fun getBag(type: String): Bag {
    var bag = workingBags.find { it.type == type }
    if (bag == null) {
        bag = Bag(type)
        workingBags.add(bag)
    }
    return bag
}

data class Bag(val type: String, val bags: MutableList<Bag> = ArrayList())

data class BagRule(val root: String, val type: String, val count: Int)

fun MutableList<Bag>.containsShinyGolden(): Boolean {
    return map {
        if (it.type == "shiny gold")
            return@map true

        if (it.bags.isEmpty())
            return@map false

        return@map it.bags.containsShinyGolden()
    }.any { it }
}