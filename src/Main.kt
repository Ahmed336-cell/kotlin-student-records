fun main() {
    val allStudents = mutableListOf("Ahmed", "Laila", "Nour", "Omar", "Salma")

    val studentGrades = mutableMapOf<String, Int>()
    allStudents.forEach { name ->
        studentGrades[name] = 0
    }
    studentGrades["Ahmed"] = 85
    studentGrades["Laila"] = 72
    studentGrades["Nour"] = 95
    studentGrades["Omar"] = 58
    studentGrades["Salma"] = 89
    val graduatedStudents = studentGrades.filter { it.value >= 60 }.keys.toMutableSet()

    showAllReports(allStudents, studentGrades, graduatedStudents)

    while (true) {
        println("Options:")
        println("1. Add new students")
        println("2. Edit student grade")
        println("3. Delete student")
        println("4. Exit program")
        print("Choose an option (1-4): ")

        when (readlnOrNull()) {
            "1" -> {
                println("ADD NEW STUDENTS")
                addNewStudents(allStudents, studentGrades, graduatedStudents)
                showAllReports(allStudents, studentGrades, graduatedStudents)
            }
            "2" -> {
                println("EDIT STUDENT GRADE")
                editStudentGrade(studentGrades, graduatedStudents)
                showAllReports(allStudents, studentGrades, graduatedStudents)
            }
            "3" -> {
                println("DELETE STUDENT")
                deleteStudent(allStudents, studentGrades, graduatedStudents)
                showAllReports(allStudents, studentGrades, graduatedStudents)
            }
            "4" -> {
                println("FINAL REPORT")
                showAllReports(allStudents, studentGrades, graduatedStudents)
                println("Exiting program...")
                return
            }
            else -> println("Invalid option, please try again")
        }
    }
}

fun editStudentGrade(gradeBook: MutableMap<String, Int>, graduated: MutableSet<String>) {
    println("Current students and grades:")
    gradeBook.forEach { (name, grade) ->
        println("$name: $grade")
    }

    print("Enter student name to edit: ")
    val name = readlnOrNull()?.trim() ?: ""
    if (name !in gradeBook.keys) {
        println("Student not found!")
        return
    }

    print("Enter new grade for $name (0-100): ")
    val grade = readlnOrNull()?.toIntOrNull()?.coerceIn(0, 100) ?: 0

    gradeBook[name] = grade
    if (grade >= 60) {
        graduated.add(name)
    } else {
        graduated.remove(name)
    }
    println("Updated $name's grade to $grade")
}

fun deleteStudent(
    studentList: MutableList<String>,
    gradeBook: MutableMap<String, Int>,
    graduated: MutableSet<String>
) {
    println("Current students:")
    studentList.forEach { println(it) }

    print("Enter student name to delete: ")
    val name = readlnOrNull()?.trim() ?: ""
    if (name.lowercase() !in studentList.map { it.lowercase() }) {
        println("Student not found!")
        return
    }

    studentList.remove(name)
    gradeBook.remove(name)
    graduated.remove(name)
    println("Removed $name from all records")
}

fun showAllReports(
    allStudents: List<String>,
    studentGrades: Map<String, Int>,
    graduatedStudents: Set<String>
) {
    println("\nAll Students: $allStudents")
    println("Student Grades: $studentGrades")
    println("Graduated Students: $graduatedStudents")

    println("\nSTUDENT LIST (SKIPPING GRADUATES)")
    allStudents.forEach { student ->
        if (student !in graduatedStudents) {
            println("$student (Score: ${studentGrades[student] ?: "Not available"})")
        }
    }

    val uppercaseNames = allStudents.map { it.uppercase() }
    println("Uppercase Names: $uppercaseNames")

    println("TOP PERFORMERS (Score > 80):")
    studentGrades.filter { it.value > 80 }
        .map { "${it.key.uppercase()} - ${it.value}" }
        .forEach { println(it) }

    println("\nTotal of grades")
    val totalScore = studentGrades.values.reduce { acc, score -> acc + score }
    println("Total Score: $totalScore")

    val formattedGrades = studentGrades.entries.fold("") { result, (name, score) ->
        if (result.isEmpty()) "$name: $score" else "$result | $name: $score"
    }
    println("\nGrades: $formattedGrades")

    println("\n---Grades Report---")
    makeReport(studentGrades)
}

fun makeReport(grades: Map<String, Int>) {
    if (grades.isEmpty()) {
        println("No student records available")
        return
    }

    println("PASSING STUDENTS (Score ≥ 60):")
    grades.filter { it.value >= 60 }
        .map { "${it.key}: ${it.value}" }
        .forEach { println(it) }
}

fun addNewStudents(
    studentList: MutableList<String>,
    gradeBook: MutableMap<String, Int>,
    graduated: MutableSet<String>
) {
    println("Enter student details (type 'done' when finished):")

    while (true) {
        print("Student name: ")
        val name = readlnOrNull()?.trim() ?: ""
        if (name.equals("done", ignoreCase = true)) break
        if (name.isEmpty()) continue

        print("$name's grade (0-100): ")
        val grade = readlnOrNull()?.toIntOrNull()?.coerceIn(0, 100) ?: 0

        studentList.add(name)
        gradeBook[name] = grade
        if (grade >= 60) graduated.add(name)

        println("Added $name with grade $grade")
    }
}