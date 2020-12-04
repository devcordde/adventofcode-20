import java.nio.file.Files
import java.nio.file.Paths

typealias Document = HashMap<String, String>

val documents = parseInput()

var validCounter = 0

documents.forEach { document ->
    if (document.areRequiredFieldsPresent()) {
        validCounter++
    }
}

println("Found $validCounter documents with required fields present")

val rules = ValidationRule.values()

validCounter = 0

documents.forEach { document ->

    if (document.areRequiredFieldsPresent()) {
        val invalid = document.map { entry ->
            val (key, value) = entry

            val rule = rules.firstOrNull { it.key == key } ?: error("Unknown key $key")

            if (rule.regex == null) {
                true
            } else {
                rule.regex.matches(value)
            }
        }.any { !it }

        if (!invalid) {
            validCounter++
        }
    }
}

println("Found $validCounter valid documents!")

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

fun Document.areRequiredFieldsPresent(): Boolean {
    return this.containsKeys("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
}

fun Document.containsKeys(vararg keys: String): Boolean {
    return !keys.map { containsKey(it) }.any { !it }
}

enum class ValidationRule(val key: String, val regex: Regex?) {
    BIRTH_YEAR("byr", "(19[2-8][0-9]|199[0-9]|200[0-2])".toRegex()),
    ISSUE_YEAR("iyr", "(201[0-9]|2020)".toRegex()),
    EXPIRATION_YEAR("eyr", "(202[0-9]|2030)".toRegex()),
    HEIGHT("hgt", "(59|6[0-9]|7[0-6])in|(1[5-8][0-9]|19[0-3])cm".toRegex()),
    HAIR_COLOR("hcl", "#[0-9a-f]{6}".toRegex()),
    EYE_COLOR("ecl", "amb|blu|brn|gry|grn|hzl|oth".toRegex()),
    PASSPORT_ID("pid", "[0-9]{9}".toRegex()),
    COUNTRY_ID("cid", null)
}
