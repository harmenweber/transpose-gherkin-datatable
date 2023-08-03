import DataTable.Companion.read
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import kotlin.system.exitProcess

class TransposeCommand :
    CliktCommand(help = "Reads a Gherkin data table from stdin, transposes it and prints the result to stdout.") {

    val stacktrace: Boolean by option().flag().help("Prints the full stacktrace in case of an error.")

    override fun run() {
        try {
            read(System.`in`)
                .transpose()
                .print(System.out)
        } catch (throwable: Throwable) {
            print(throwable)
            exitProcess(1)
        }
    }

    private fun print(throwable: Throwable) {
        if (stacktrace) throwable.printStackTrace(System.err)
        else System.err.println(throwable.message)
    }
}