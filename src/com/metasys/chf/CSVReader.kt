package com.metasys.chf

import java.io.File

/**
 * Created by kbryd on 1/27/16.
 */

class CSVReader() {

    var rates = arrayOf<ExchangeRate>();

    constructor(fileName: String, currencyName: String) : this() {
        val file = File(fileName);

        println("Loading file " + file.absolutePath);

        val lines = file.readLines();

        var currencyIndex = 0;

        for (field in lines[0].split(";")) {
            if (field.contains(currencyName)) {
                break;
            }
            currencyIndex++;
        }

        for (line in lines.subList(1, lines.size)) {
            val fields = line.split(";");
            try {
                fields[0].toLong()
                val exchangeRate = ExchangeRate(fields[0], fields[currencyIndex].replace(",", ".").toDouble())
                rates += exchangeRate;
            } catch (e: Exception) {
                // ignore
            }
        }
    }
}
