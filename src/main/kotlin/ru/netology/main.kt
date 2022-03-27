package ru.netology
import kotlin.math.ceil


fun main() {
    val purchases: Array<Client> = arrayOf(
        Client(100.0, false),
        Client(1000.0, false),
        Client(1001.0, true),
        Client(10000.0, false),
        Client(10001.0, true)
    )
    purchases.forEach { it.calculatePurchase(100.0) }
}


fun getRubKop(sum: Double): Array<Long> {
    val rub = sum.toLong()
    val kop = ceil(sum % 1 * 100).toLong()
    return arrayOf(rub, kop)
}


class Client(val purchasesTotal: Double, val isMusicLover: Boolean) {
    val discount1 = 1001.0
    val discount2 = 10_001.0

    fun calculatePurchase(purchase: Double) {
        val (initRub, initKop) = getRubKop(purchasesTotal)

        println("При сумме покупок: $initRub руб. $initKop коп.")

        var sum = purchase
        val discount = when (this.purchasesTotal) {
            in discount1..(discount2 - 1) -> 0.03
            in discount2..Double.MAX_VALUE -> 0.05
            else -> 0.0
        }
        sum -= sum * discount
        sum = if (this.isMusicLover) sum - (sum * 0.01) else sum

        val (rub, kop) = getRubKop(sum)

        println("Сумма покупки со скидкой (из 100 рублей): $rub руб. $kop коп.\n")
    }
}