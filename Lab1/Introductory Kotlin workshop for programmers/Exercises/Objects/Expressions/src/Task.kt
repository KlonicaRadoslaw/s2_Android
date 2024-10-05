interface Printer {
    fun print(document: String)
}

object PrintShop {

    fun printDocument(document: String, printer: Printer) {
        printer.print(document)
    }
}

class PrintShopOwner {

    var printedDocument = ""

    fun printIt(document: String) {

        PrintShop.printDocument(document, //implement object expression here)
    }
}

fun main(args: Array<String>) {

    val owner = PrintShopOwner()
    val document = "Photo book"

    owner.printIt(document)

    val printedDocument = owner.printedDocument
    println(printedDocument)


}