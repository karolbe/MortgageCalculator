package com.metasys.chf

/**
 * Created by kbryd on 1/27/16.
 */

class MonthRecords() {
    var groupEntries = listOf<ExchangeRate>();

    constructor(allRates: Array<ExchangeRate>, groupId: Int) : this() {
        groupEntries = allRates.filter { it.month == groupId};
    }

    public fun getNthExchangeRate(n: Int, total: Int): ExchangeRate {
        val r: Double = groupEntries.size.toDouble() / total.toDouble();
        return groupEntries[Math.floor(n * r).toInt() - 1];
    }

    public fun getExchangeRate(n: Int): ExchangeRate {
        return groupEntries[n - 1];
    }
}