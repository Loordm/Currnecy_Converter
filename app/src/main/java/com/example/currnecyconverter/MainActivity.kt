package com.example.currnecyconverter
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    var firstCountry: String = "Euro (EUR)"
    var secondCountry: String = "Algerian Dinar (DZD)"
    var valeurArgent: Double = 0.0
    val currencyNames = arrayListOf(
        "Algerian Dinar (DZD)",
        "Australian Dollar (AUD)",
        "Bahraini Dinar (BHD)",
        "British Pound Sterling (GBP)",
        "Canadian Dollar (CAD)",
        "Chinese Yuan (CNY)",
        "Comorian Franc (KMF)",
        "Djiboutian Franc (DJF)",
        "Egyptian Pound (EGP)",
        "Euro (EUR)",
        "Iraqi Dinar (IQD)",
        "Japanese Yen (JPY)",
        "Jordanian Dinar (JOD)",
        "Kuwaiti Dinar (KWD)",
        "Lebanese Pound (LBP)",
        "Libyan Dinar (LYD)",
        "Mauritanian Ouguiya (MRO)",
        "Moroccan Dirham (MAD)",
        "Omani Rial (OMR)",
        "Qatari Riyal (QAR)",
        "Saudi Riyal (SAR)",
        "Somali Shilling (SOS)",
        "South Korean Won (KRW)",
        "Sudanese Pound (SDG)",
        "Swiss Franc (CHF)",
        "Syrian Pound (SYP)",
        "Tunisian Dinar (TND)",
        "UAE Dirham (AED)",
        "United States Dollar (USD)",
        "Yemeni Rial (YER)"
    )

//**********************************************************************************************

//**********************************************************************************************

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mytoolbar: Toolbar = findViewById(R.id.mytoolbar)
        mytoolbar.inflateMenu(R.menu.menu)
        mytoolbar.setOnMenuItemClickListener { menuitem ->
            if (menuitem.itemId == R.id.contactUs) {
                val i = Intent(Intent.ACTION_SENDTO)
                i.data = Uri.parse("mailto:")
                i.putExtra(Intent.EXTRA_SUBJECT, "Contact Us")
                i.putExtra(Intent.EXTRA_EMAIL, arrayOf("mohdev81@gmail.com"))
                startActivity(i)
            }
            true
        }

        val myresultshow: TextInputEditText = findViewById(R.id.result)
        val myamount: TextInputEditText = findViewById(R.id.amountvalue)
        val First: AutoCompleteTextView = findViewById(R.id.FirstCountry)
        val Second: AutoCompleteTextView = findViewById(R.id.SecondCountry)
        val myadapter = ArrayAdapter(this, R.layout.dropdownmenu, currencyNames)
        var convertingResult: Calculator
        First.setAdapter(myadapter)
        Second.setAdapter(myadapter)
        First.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            if (First.text.toString().isNotEmpty()) firstCountry = First.text.toString()
            if (myamount.text.toString().isNotEmpty() && secondCountry.isNotEmpty()) {
                valeurArgent = myamount.text.toString().toDouble()
                convertingResult = Calculator(firstCountry,secondCountry,valeurArgent)
                myresultshow.setText(convertingResult.calculateAmount())
            }
        }
        Second.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            if (First.text.toString().isNotEmpty()) secondCountry = Second.text.toString()
            if (myamount.text.toString().isNotEmpty() && firstCountry.isNotEmpty()) {
                valeurArgent = myamount.text.toString().toDouble()
                convertingResult = Calculator(firstCountry,secondCountry,valeurArgent)
                myresultshow.setText(convertingResult.calculateAmount())
            }
        }
        myamount.addTextChangedListener {
            if (firstCountry.isNotEmpty() && secondCountry.isNotEmpty()) {
                if (myamount.text.toString().isNotEmpty()) {
                    valeurArgent = myamount.text.toString().toDouble()
                    if (firstCountry.isNotEmpty() && secondCountry.isNotEmpty()) {
                        convertingResult = Calculator(firstCountry,secondCountry,valeurArgent)
                        myresultshow.setText(convertingResult.calculateAmount())
                    }
                } else (myresultshow.setText("0"))
            } else Toast.makeText(this, "Select countries", Toast.LENGTH_SHORT).show()
        } // end listener
        //********************* Intent *********************************
        val btn: Button = findViewById(R.id.gotogoogle)
        btn.setOnClickListener {
            if (myamount.text.toString()
                    .isNotEmpty() && firstCountry.isNotEmpty()
                && secondCountry.isNotEmpty()
            ) {
                val i = Intent(Intent.ACTION_SENDTO)
                i.data = Uri.parse("mailto:")
                i.putExtra(Intent.EXTRA_SUBJECT, "Convertion result")
                i.putExtra(
                    Intent.EXTRA_TEXT,
                    "Do you know that ${myamount.text} $firstCountry is equals to $valeurArgent $secondCountry ! You can check in Currency Convertor app"
                )
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(i)
                }
            } else Toast.makeText(this, "You have to enter value!", Toast.LENGTH_SHORT).show()
        }

    } // end onCreate
}