import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

val allBags = arrayListOf<Bag>()

val contentRegex = "([0-9]+) ([\\w]+ [\\w]+) bag[s]?".toRegex()

val bags = Files.readAllLines(Paths.get("input.txt"))
        .map { it.removeSuffix(".") }
        .filterNot { it.contains("no other") }
        .map { it.replace("bag[s]?".toRegex(), "").replace("contain", ",").split(",") }
        .map { it.map(String::trim) }
        .map { it[0] to Bag(it[0], toChildBags(it.subList(1, it.size))) }.toMap()

println(bags.filter { it.value.hasColor("shiny gold", bags) }.count())
println(bags["shiny gold"]?.getChildBagNumber(bags))

data class Bag(val color: String, val childBags: Map<String, Int>)

fun Bag.hasColor(color: String, bags: Map<String, Bag>): Boolean =
        if (this.childBags.keys.contains(color)) true
        else this.childBags.keys.any { bags[it]?.hasColor(color, bags) == true }

fun toChildBags(bags: List<String>): Map<String, Int> {
    return bags.map { it.substring(2) to Integer.parseInt(it.substring(0, 1)) }.toMap()
}

fun Bag.getChildBagNumber(bags: Map<String, Bag>): Int {
    return this.childBags.map { (bags[it.key]?.getChildBagNumber(bags) ?: 0) * it.value + it.value }.sum()
}