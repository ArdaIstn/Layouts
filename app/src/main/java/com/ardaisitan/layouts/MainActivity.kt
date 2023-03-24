package com.ardaisitan.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardaisitan.layouts.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.hesaplaButton.setOnClickListener {
            bahşişHesapla()
        }

    }
    fun bahşişHesapla(){
        val hizmetBedeliString=binding.hizmetBedeliEditText.text.toString()

        val tutar=hizmetBedeliString.toDoubleOrNull()

        if(tutar==null){
            binding.bahsisTutariText.text="Lütfen bir sayi giriniz."
            return
        }

        val secilenButon=binding.bahsisSecenekleriRadioGrup.checkedRadioButtonId//Üçünü de tarıyor o an seçili olanı degiskene atar.

        val bahsisYuzdesi=when(secilenButon){
            R.id.yuzdeOnBesadioButton->0.15
            R.id.yuzdeOnSekizradioButton->0.18
            else->0.20
        }

        var bahşiş=tutar*bahsisYuzdesi

        val yukariYuvarla=binding.bahsisYuvarlaSwitch.isChecked

        if(yukariYuvarla){
            bahşiş=kotlin.math.ceil(bahşiş)//Ceil yukari yuvarlama islemdir.
        }
        val formatlananBahşiş=NumberFormat.getCurrencyInstance(Locale("tr","TR")).format(bahşiş)//Bu methodla bulundugum ulkenin para birimine gore yazdim.
        binding.bahsisTutariText.text=getString(R.string.bahşişTutarı,formatlananBahşiş) //String.xml'den cagirdim.



    }

}