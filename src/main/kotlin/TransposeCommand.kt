import DataTable.Companion.read
import com.github.ajalt.clikt.core.CliktCommand

class TransposeCommand :
    CliktCommand(help = "Reads a Gherkin data table from stdin, transposes it and prints the result to stdout.") {
    override fun run() {
        read(System.`in`)
            .transpose()
            .print(System.out)
    }
}