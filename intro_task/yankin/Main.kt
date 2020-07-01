import java.util.Scanner
import java.io.File


fun main() {
    val scanner = Scanner(System.`in`)


    print("Введите имя входного файла: ")
    val file = File(scanner.nextLine())
    print("Введите имя выходного файла: ")
    val outFile = File(scanner.nextLine())
    outFile.createNewFile()

    if (!file.exists() || !file.canRead()) {
        println("Проблемы с файлом. Не прочитаю")
        return
    }
    if (!outFile.exists() || !outFile.canWrite()) {
        println("Проблемы с файлом для записи")
        return
    }


    val fileScanner = Scanner(file)
    val fileWriter = outFile.printWriter()


    if (!fileScanner.hasNextLine()) {
        println("Файл не очень: отсутствует содержимое в принципе")
        fileWriter.use {out -> out.println("Файл не очень: отсутствует содержимое в принципе")}
        return
    }
    var str = fileScanner.nextLine()
    if (!stringIsNumber(str)) {
        println("Содержимое файла не соответствует ожидаемому")
        fileWriter.use {out -> out.println("Содержимое файла не соответствует ожидаемому")}
        return
    }
    val operationCount = str.toInt()
    if (operationCount < 1 || operationCount > 10000000) {
        println("Некорректное число операций")
        fileWriter.use {out -> out.println("Некорректное число операций")}
        return
    }


    val deque = Deque<Int>()
    val stringBuilder = StringBuilder()
    repeat(operationCount) {
        if (!fileScanner.hasNextLine()) {
            stringBuilder.append("Не хватает значений в файле")
            stringBuilder.append("\n")
            println(stringBuilder)
            outFile.printWriter().use {out -> out.println(stringBuilder)}
            return
        }
        str = fileScanner.nextLine()
        if (!stringIsNumber(str)) {
            stringBuilder.append("Содержимое файла не соответствует ожидаемому")
            stringBuilder.append("\n")
            println(stringBuilder)
            outFile.printWriter().use {out -> out.println(stringBuilder)}
            return
        }

        val sign = str[0] != '-'
        val num = str.toInt()
        if (num != 0) {
            if (num > 0) {
                deque.pushFront(num)
            }
            else {
                deque.pushBack(kotlin.math.abs(num))
            }
        } else {
            if (!deque.isEmpty()) {
                if (sign) {
                    stringBuilder.append(deque.front())
                    stringBuilder.append("\n")
                    deque.popFront()
                } else {
                    stringBuilder.append(deque.back())
                    stringBuilder.append("\n")
                    deque.popBack()
                }
            }
        }
    }
    println(stringBuilder)
    outFile.printWriter().use {out -> out.println(stringBuilder)}
}


fun stringIsNumber(str : String) : Boolean {
    if (str.isEmpty()) return false

    for (i in str.indices) {
        if (!str[i].isDigit()) {
            if (i == 0 && (str[i] == '+' || str[i] == '-')) continue
            return false
        }
    }
    return true
}