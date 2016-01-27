package com.metasys.chf

/**
 * Created by kbryd on 1/27/16.
 */

class Mortgage() {
    var allRecords = hashMapOf<kotlin.String, Array<ExchangeRate>>();

    constructor(filesSet: Array<String>) : this() {

        filesSet.forEach {
            val csvReader = CSVReader(it, "CHF");
            allRecords.put(it, csvReader.rates);
        }
    }

    public fun calculateIncrementalPurchases(amount: Double, numberOfInstallments: Int = 10) {
        var totalInstallmentAmount: Double = 0.0;
        var totalInstallmentAmountCHF: Double = 0.0;
        val installmentAmount = amount / numberOfInstallments;

        for (yearRecords in allRecords) {
            for (monthNumber in 1..12) {
                var monthRecords = MonthRecords(yearRecords.value, monthNumber);
                for (installment in 1..numberOfInstallments) {
                    var exchangeRate = monthRecords.getNthExchangeRate(installment, numberOfInstallments);
                    totalInstallmentAmount += installmentAmount * exchangeRate.rate;
                    totalInstallmentAmountCHF += installmentAmount;
                }
            }
            println("Total yearly cost for year ${yearRecords.key} installment $amount CHF is $totalInstallmentAmount PLN ($totalInstallmentAmountCHF CHF)");
            totalInstallmentAmount = 0.0;
            totalInstallmentAmountCHF = 0.0;
        }
    }

    public fun calculateSinglePurchase(amount: Double, quotationIndex: Int) {
        var totalInstallmentAmount: Double = 0.0;
        var totalInstallmentAmountCHF: Double = 0.0;
        val installmentAmount = amount;

        for (yearRecords in allRecords) {
            for (monthNumber in 1..12) {
                var monthRecords = MonthRecords(yearRecords.value, monthNumber);
                var exchangeRate = monthRecords.getExchangeRate(quotationIndex);
                totalInstallmentAmount += installmentAmount * exchangeRate.rate;
                totalInstallmentAmountCHF += installmentAmount;
            }
            println("Total yearly cost for year ${yearRecords.key} installment $amount CHF is $totalInstallmentAmount PLN ($totalInstallmentAmountCHF CHF)");
            totalInstallmentAmount = 0.0;
            totalInstallmentAmountCHF = 0.0;
        }
    }
}

fun main(args: Array<String>) {

    val mortgage = Mortgage(args);
    val installment: Double = 400.0;
    val numberOfInstallments = 10;
    println("Calculations for incremental purchases, # of installments: $numberOfInstallments");
    mortgage.calculateIncrementalPurchases(installment, numberOfInstallments);

    println("-------")

    val quotationIndex = 2;
    println("Calculations for a single purchase on ${quotationIndex}th quotation index of a month");
    mortgage.calculateSinglePurchase(installment, quotationIndex);
}

