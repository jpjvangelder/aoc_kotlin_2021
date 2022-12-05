import java.io.File

class AocUtil {
    companion object {
        fun getFile(fileName: String): List<String> {
            return getFileFromResources(fileName).readLines()
        }

        private fun getFileFromResources(fileName: String): File {
            return File("src/main/resources/$fileName.txt")
        }
    }
}
