open class Tag(val name: String) {
    protected val children = mutableListOf<Tag>()

    override fun toString() =
            "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit): TABLE {
    val table = TABLE()
    table.init()
    return table
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) {
        val row = TR()
        row.init()
        children.add(row)
    }
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) {
        val cell = TD()
        cell.init()
        children.add(cell) //t
    }
}

class TD : Tag("td")

fun createTable() =
        table {
            tr {
                repeat(2) {
                    td {

                    }
                }
            }
        }

fun main() {
    println(createTable())
    // Output: <table><tr><td></td><td></td></tr></table>
}
