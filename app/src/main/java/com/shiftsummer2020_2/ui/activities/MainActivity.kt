package com.shiftsummer2020_2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiftsummer2020_2.R
import com.shiftsummer2020_2.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mainFragment = MainFragment.getInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_main_activity, mainFragment)
            .commit()
    }
}