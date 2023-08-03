import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.regex.Pattern

data class DataTable(val rows: List<List<String>>) {

    fun transpose(): DataTable {
        val transposedRows = mutableListOf<MutableList<String>>()

        for ((rowIndex, row) in rows.withIndex()) {
            for ((cellIndex, cell) in row.withIndex()) {
                if (rowIndex == 0) {
                    transposedRows.add(mutableListOf(cell))
                } else {
                    transposedRows[cellIndex].add(cell)
                }
            }
        }

        return DataTable(transposedRows)
    }

    fun print(printStream: PrintStream) {
        rows.map { it.joinToString(" | ", "| ", " |") }
            .forEach(printStream::println)
    }

    companion object {

        private val ROW_PATTERN = Pattern.compile("^\\s*\\|(.*)\\|\\s*$")

        fun read(inputStream: InputStream): DataTable {
            return BufferedReader(InputStreamReader(inputStream)).use { reader ->
                val rows = reader.lines()
                    .map(ROW_PATTERN::matcher)
                    .filter { it.matches() }
                    .map { it.group(1) }
                    .toList()
                val rowsOfCells = rows.map {
                    it.split("|").map(String::trim)
                }
                DataTable(rowsOfCells)
            }
        }

    }
}