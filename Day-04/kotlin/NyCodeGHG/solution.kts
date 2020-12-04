import java.nio.file.Files
import java.nio.file.Paths

typealias Document = HashMap<String, String>

val documents = parseInput()

var validCounter = 0

documents.forEach { document ->
    if (document.isValid()) {
        validCounter++
    }
}

println("Found $validCounter valid documents")

fun parseInput(): List<Document> {
    val lines = Files.readAllLines(Paths.get("./input.txt"))

    var lineCounter = 0
    val documents = ArrayList<Document>()

    var currentDocument = Document()
    documents.add(currentDocument)

    while (lineCounter < lines.size) {

        var line = lines[lineCounter]

        if (line.isNotBlank()) {
            line.split(" ")
                .forEach {
                    val splitten = it.split(":")
                    val key = splitten[0]
                    val value = splitten[1]
                    currentDocument[key] = value
                }
        } else {
            currentDocument = Document()
            documents.add(currentDocument)
        }

        lineCounter++
    }

    return documents
}

fun Document.isValid(): Boolean {
    return this.containsKeys("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
}

fun Document.containsKeys(vararg keys: String): Boolean {
    return !keys.map { containsKey(it) }.any { !it }
}
