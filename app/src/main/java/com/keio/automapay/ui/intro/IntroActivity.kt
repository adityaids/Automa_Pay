package com.keio.automapay.ui.intro

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.keio.automapay.R
import com.keio.automapay.databinding.ActivityIntroBinding
import com.keio.automapay.ui.adapter.IntroSliderAdapter
import com.keio.automapay.ui.main.MainActivity
import com.keio.automapay.util.DummyData
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val mAdapter = IntroSliderAdapter()
        binding.slider.setSliderAdapter(mAdapter)
        val listCarousel = DummyData.getCarouselList(this)
        mAdapter.setData(listCarousel)
        binding.slider.startAutoCycle()

        binding.btnNext.setOnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}