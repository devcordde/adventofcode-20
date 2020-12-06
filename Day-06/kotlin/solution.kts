import java.nio.file.Files
import java.nio.file.Paths

val answers = ArrayList<Answer>()

var answer = Answer()
val lines = Files.readAllLines(Paths.get("input.txt"))

var index = 0
var lastLineCount = 0

while (index < lines.size) {
    val line = lines[index]

    if (line.isBlank()) {
        index++
        continue
    }

    answer.people = ++lastLineCount
    answer.questions.addAll(line.toCharArray().toList())

    if (++index < lines.size) {
        if (lines[index].isBlank()) {
            answers.add(answer)
            answer = Answer()
            lastLineCount = 0
        }
    } else {
        answers.add(answer)
        answer = Answer()
        lastLineCount = 0
    }
}

println(answers.map { it.questions.size }.sum())

data class Answer(val questions: MutableSet<Char> = HashSet<Char>(), var people: Int = 0)