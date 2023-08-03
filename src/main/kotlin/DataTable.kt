import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.regex.Pattern

data class DataTable(val rows: List<List<String>>) {

    init {
        assertRows(rows)
    }

    private fun assertRows(rows: List<List<String>>) {
        if (rows.isEmpty()) {
            throw IllegalArgumentException("A data table must have at least one row.")
        }
        val numberOfCells = rows[0].size
        if (numberOfCells <= 0) {
            throw IllegalArgumentException("A data table must have at least one cell.")
        }
        for ((index, row) in rows.withIndex()) {
            if (row.size != numberOfCells) {
                throw IllegalArgumentException("All rows in a data table must have the same number of cells. The 1st row has $numberOfCells cells. But row ${index + 1} has ${row.size} cells.")
            }
        }
    }

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
        val maxlengthPerColumn = getMaxlengthPerColumn()
        rows.map { row ->
            row.mapIndexed { index, cell -> cell.padEnd(maxlengthPerColumn[index]) }
                .joinToString(" | ", "| ", " |")
        }.forEach(printStream::println)
    }

    private fun getMaxlengthPerColumn(): List<Int> {
        return transpose().rows.map { row ->
            row.map { cell ->
                cell.length
            }.max()
        }
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