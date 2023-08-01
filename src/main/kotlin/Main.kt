import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.regex.Pattern


fun main() {
    print(transpose(readDataTable(System.`in`)))
}

private fun readDataTable(inputStream: InputStream): List<List<String>> {
    val dataTablePattern = Pattern.compile("^\\s*\\|(.*)\\|\\s*$")

    return BufferedReader(InputStreamReader(inputStream)).use { reader ->
        val lines = reader.lines()
            .map(dataTablePattern::matcher)
            .filter { it.matches() }
            .map { it.group(1) }
            .toList()
        lines.map { it.split("|")
            .map(String::trim) }
    }
}

private fun transpose(dataTable: List<List<String>>): List<List<String>> {
    val result = mutableListOf<MutableList<String>>()

    for ((rowIndex, row) in dataTable.withIndex()) {
        for ((cellIndex, cell) in row.withIndex()) {
            if (rowIndex == 0) {
                result.add(mutableListOf(cell))
            } else {
                result[cellIndex].add(cell)
            }
        }
    }

    return result
}

private fun print(dataTable: List<List<String>>) {
    dataTable.map { it.joinToString(" | ", "| ", " |") }
        .forEach(::println)
}