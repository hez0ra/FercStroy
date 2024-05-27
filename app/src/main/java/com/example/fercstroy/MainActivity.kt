package com.example.fercstroy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fercstroy.fragments.FragmentFooter
import com.example.fercstroy.fragments.FragmentHeader
import com.example.fercstroy.fragments.FragmentMain
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MapKitFactory.setApiKey("387d8611-7c50-469d-8c11-b8ce797a5d1f")
        MapKitFactory.initialize(this)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentHeader = FragmentHeader()
        fragmentTransaction.replace(R.id.header_container, fragmentHeader)

        val mainFragment = FragmentMain()
        fragmentTransaction.replace(R.id.main_container, mainFragment)

        val footerFragment = FragmentFooter()
        fragmentTransaction.replace(R.id.footer_container, footerFragment)

        fragmentTransaction.commit()




        supportActionBar?.hide()
    }
}