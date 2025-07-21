fun main() {

}

fun createStudentList(): List<String> {
    return listOf("Ahmed", "Laila", "Nour", "Omar", "Salma")
}

fun createStudentScoreMap(names: List<String>): MutableMap<String, Int> {
    val scores = mutableMapOf<String, Int>()
    names.forEach { scores[it] = 0 }
    scores["Ahmed"] = 85
    scores["Laila"] = 72
    scores["Nour"] = 95
    scores["Omar"] = 58
    scores["Salma"] = 89
    return scores
}

fun createGraduatedStudentSet(scores: Map<String, Int>): Set<String> {
    return scores.filter { it.value >= 60 }.keys.toSet()
}

fun printStudentList(names: List<String>) {
    println("Student Names: $names")
}

fun printStudentScores(scores: Map<String, Int>) {
    println("Student Scores: $scores")
}

fun printGraduatedStudents(graduates: Set<String>) {
    println("Graduated Students: $graduates")
}

fun printNamesSkippingGraduates(names: List<String>, graduates: Set<String>) {
    names.forEach { name ->
        if (name !in graduates) {
            println("- $name")
        }
    }
}

fun transformAndFilterStudents(scores: Map<String, Int>) {
    val upperNames = scores.keys.map { it.uppercase() }
    println("Uppercase Names: $upperNames")

    println("Students who scored above 80:")
    scores.filter { it.value > 80 }
        .map { "${it.key.uppercase()} (${it.value})" }
        .forEach { println("- $it") }
}

fun aggregateScores(scores: Map<String, Int>) {
    val total = scores.values.reduce { acc, value -> acc + value }
    println("Total Score: $total")

    val formatted = scores.entries.fold("") { acc, entry ->
        acc + "${entry.key}: ${entry.value} | "
    }.removeSuffix(" | ")

    println("Formatted: $formatted")
}

fun generateStudentReport(scores: Map<String, Int>) {
    if (scores.isEmpty()) {
        println("No student data available.")
        return
    }

    scores.filter { it.value >= 60 }
        .map { "${it.key}: ${it.value}" }
        .forEach { println(it) }
}
