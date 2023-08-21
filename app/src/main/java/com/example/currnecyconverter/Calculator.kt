package com.example.currnecyconverter
class Calculator {
    private val currencyToEuroValue = mapOf(
        "Algerian Dinar (DZD)" to 147.525,
        "Australian Dollar (AUD)" to 1.61,
        "Bahraini Dinar (BHD)" to 0.44,
        "British Pound Sterling (GBP)" to 0.85,
        "Canadian Dollar (CAD)" to 1.48,
        "Chinese Yuan (CNY)" to 7.73,
        "Comorian Franc (KMF)" to 491.37,
        "Djiboutian Franc (DJF)" to 208.36,
        "Egyptian Pound (EGP)" to 18.19,
        "Euro (EUR)" to 1.0,
        "Iraqi Dinar (IQD)" to 1944.53,
        "Japanese Yen (JPY)" to 132.37,
        "Jordanian Dinar (JOD)" to 0.77,
        "Kuwaiti Dinar (KWD)" to 0.34,
        "Lebanese Pound (LBP)" to 1722.89,
        "Libyan Dinar (LYD)" to 4.53,
        "Mauritanian Ouguiya (MRO)" to 373.22,
        "Moroccan Dirham (MAD)" to 10.97,
        "Omani Rial (OMR)" to 0.46,
        "Qatari Riyal (QAR)" to 4.48,
        "Saudi Riyal (SAR)" to 4.59,
        "Somali Shilling (SOS)" to 657.76,
        "South Korean Won (KRW)" to 1325.41,
        "Sudanese Pound (SDG)" to 59.16,
        "Swiss Franc (CHF)" to 1.08,
        "Syrian Pound (SYP)" to 1885.03,
        "Tunisian Dinar (TND)" to 3.36232,
        "UAE Dirham (AED)" to 4.37,
        "United States Dollar (USD)" to 1.18,
        "Yemeni Rial (YER)" to 294.94
    )
    private lateinit var firstCountry:String
    private lateinit var secondCountry:String
    private var amount:Double = 0.0
    constructor(firstCountry: String, secondCountry: String, amount: Double) {
        this.firstCountry = firstCountry
        this.secondCountry = secondCountry
        this.amount = amount
    }
    constructor(){}
    fun calculateAmount():String{
        if (firstCountry.isNotEmpty() && secondCountry.isNotEmpty()){
            val firstInEuro = amount/currencyToEuroValue[firstCountry]!!
            return String.format("%.3f", firstInEuro * currencyToEuroValue[secondCountry]!!)
        }
        return "Error"
    }
}