import kotlin.io.path.toPath

fun String.readLines() = object {}.javaClass.getResource(this)!!.toURI().toPath().toFile().readLines()
