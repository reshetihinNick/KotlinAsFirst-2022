@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sqrSum = 0.0
    for (element in v) {
        sqrSum += sqr(element)
    }
    return sqrt(sqrSum)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>) =
    if (list.isNotEmpty()) list.sum() / list.size
    else 0.0

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val arithmeticMean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= arithmeticMean
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    for (i in p.indices) {
        px += p[i] * (x.toDouble().pow(i.toDouble())).toInt()
    }
    return px
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val primeMultipliers = mutableListOf<Int>()
    var number = n
    var primeMultiplier = 2
    while (!isPrime(number)) {
        if (number % primeMultiplier == 0) {
            primeMultipliers.add(primeMultiplier)
            number /= primeMultiplier
        } else primeMultiplier++
    }
    primeMultipliers.add(number)
    return primeMultipliers
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int) = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val digits = mutableListOf<Int>()
    var number = n
    while (number >= base) {
        digits.add(number % base)
        number /= base
    }
    digits.add(number)
    digits.reverse()
    return digits
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val numberInBase = convert(n, base)
    val asciiNumberTen = 'a'
    return buildString {
        for (element in numberInBase) {
            if (element >= 10) this.append(asciiNumberTen + (element - 10))
            else this.append("$element")
        }
    }
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0
    for (i in digits.indices) {
        number += digits[digits.size - (i + 1)] * base.toDouble().pow(i).toInt()
    }
    return number
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var number = 0
    for (i in str.indices) {
        val fromAsciiCodeToInt = if (str[str.length - (i + 1)] >= 'a') str[str.length - (i + 1)] - 'a' + 10
        else str[str.length - (i + 1)] - '0'
        number += fromAsciiCodeToInt * base.toDouble().pow(i).toInt()
    }
    return number
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun numberToListOfDigits(n: Int): List<Int> {
    val listOfDigits = mutableListOf<Int>()
    var number = n
    while (number != 0) {
        listOfDigits.add(number % 10)
        number /= 10
    }
    listOfDigits.reverse()
    return listOfDigits
}

fun roman(n: Int): String {
    val romanX5DigitList = listOf('V', 'L', 'D')
    val romanX10DigitList = listOf('I', 'X', 'C', 'M')
    val listOfDigit = numberToListOfDigits(n)
    return buildString {
        for (i in listOfDigit.indices) {
            val currentIndex = listOfDigit.size - (i + 1)
            when (listOfDigit[i]) {
                0 -> this.append("")
                1, 2, 3 -> {
                    for (ind in 0 until listOfDigit[i]) {
                        this.append(romanX10DigitList[currentIndex])
                    }
                }

                4 -> {
                    this.append(romanX10DigitList[currentIndex])
                    this.append(romanX5DigitList[currentIndex])
                }

                5 -> this.append(romanX5DigitList[currentIndex])
                9 -> {
                    this.append(romanX10DigitList[currentIndex])
                    this.append(romanX10DigitList[currentIndex + 1])
                }

                else -> {
                    this.append(romanX5DigitList[currentIndex])
                    for (ind in 0 until listOfDigit[i] - 5) {
                        this.append(romanX10DigitList[currentIndex])
                    }
                }
            }
        }
    }
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val listOfRuDigits = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val listOfRu10sDigits = listOf(
        "десять", "одиннадцать", "двенадцать",
        "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    )
    val listOfRuX10Digits = listOf(
        "дцать", "сорок", "десят", "девяно"
    )
    val listOfX100Digits = listOf(
        "сто", "двести", "ста", "сот"
    )
    val listOfX1000Digits = listOf("одна", "две", "тысяча", "тысячи", "тысяч")
    val listOfDigit = numberToListOfDigits(n).reversed()
    return buildString {
        for (i in listOfDigit.size - 1 downTo 0) {
            if (listOfDigit[i] != 0) {
                when (i) {
                    0, 3 -> {
                        if (i + 1 < listOfDigit.size && listOfDigit[i + 1] == 1) continue
                        if (i == 3) {
                            if (listOfDigit[i] in 1..2) {
                                this.append(listOfX1000Digits[listOfDigit[i] - 1])
                            } else {
                                this.append(listOfRuDigits[listOfDigit[i] - 1])
                            }
                            this.append(" ")
                            when (listOfDigit[i]) {
                                1 -> this.append(listOfX1000Digits[2])
                                2, 3, 4 -> this.append(listOfX1000Digits[3])
                                else -> this.append(listOfX1000Digits[4])
                            }
                        } else this.append(listOfRuDigits[listOfDigit[i] - 1])
                    }

                    1, 4 -> {
                        if (listOfDigit[i] == 1) {
                            this.append(listOfRu10sDigits[listOfDigit[i - 1]])
                            if (i == 4) {
                                this.append(" ")
                                this.append(listOfX1000Digits[4])
                            }
                        } else {
                            when (listOfDigit[i]) {
                                2, 3 -> {
                                    this.append(listOfRuDigits[listOfDigit[i] - 1])
                                    this.append(listOfRuX10Digits[0])
                                }

                                4 -> this.append(listOfRuX10Digits[1])
                                5, 6, 7, 8 -> {
                                    this.append(listOfRuDigits[listOfDigit[i] - 1])
                                    this.append(listOfRuX10Digits[2])
                                }

                                else -> {
                                    this.append(listOfRuX10Digits[3])
                                    this.append(listOfX100Digits[0])
                                }
                            }
                            if (listOfDigit[i - 1] == 0 && i == 4) {
                                this.append(" ")
                                this.append(listOfX1000Digits[4])
                            }
                        }
                    }

                    2, 5 -> {
                        when (listOfDigit[i]) {
                            1, 2 -> this.append(listOfX100Digits[listOfDigit[i] - 1])
                            3, 4 -> {
                                this.append(listOfRuDigits[listOfDigit[i] - 1])
                                this.append(listOfX100Digits[2])
                            }

                            else -> {
                                this.append(listOfRuDigits[listOfDigit[i] - 1])
                                this.append(listOfX100Digits[3])
                            }
                        }
                        if (i == 5 && listOfDigit[i - 1] == 0 && listOfDigit[i - 2] == 0) {
                            this.append(" ")
                            this.append(listOfX1000Digits[4])
                        }
                    }
                }
                if (i != 0) {
                    this.append(" ")
                }
            }
        }
        if (this.elementAt(this.lastIndex) == ' ') this.deleteCharAt(this.lastIndex)
    }
}