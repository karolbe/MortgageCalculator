package com.metasys.chf

/**
 * Created by kbryd on 1/27/16.
 */
class ExchangeRate() {

    constructor(date: String, rate: Double) : this() {
        this.rate = rate;
        this.year = date.substring(0, 4).toInt();
        this.month = date.substring(4, 6).toInt();
        this.day = date.substring(6, 8).toInt();
    }

    var year: Int = 0;
    var month: Int = 0;
    var day: Int = 0;
    var rate: Double = 0.0;
}
