package com.example.introsliderusingviewpager2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var introSliderViewPager : ViewPager2

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Sunlight",
                "Sunlight is the light and energy that comes from the Sun.",
                R.drawable.dumbbell
            ),
            IntroSlide(
                "Play Online",
                "Electronic bill payment is a feature of online,mobile and telephone banking.",
                R.drawable.selfigirl
            ),
            IntroSlide(
                "Video Streaming",
                "Streaming media is multimedia that is constantly received by and presented to an and-user",
                R.drawable.working
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        introSliderViewPager = findViewById(R.id.introSliderViewPager)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        findViewById<MaterialButton>(R.id.buttonNext).setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem += 1
            } else {
                Intent(applicationContext, AnotherActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        findViewById<TextView>(R.id.textSkipIntro).setOnClickListener {
            Intent(applicationContext, AnotherActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            findViewById<LinearLayout>(R.id.indicatorsContainer).addView(indicators[i])
        }
    }

    @SuppressLint("CutPasteId")
    private fun setCurrentIndicator(index: Int) {
        val childCount = findViewById<LinearLayout>(R.id.indicatorsContainer).childCount
        for (i in 0 until childCount) {
            val imageView = findViewById<LinearLayout>(R.id.indicatorsContainer)[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}