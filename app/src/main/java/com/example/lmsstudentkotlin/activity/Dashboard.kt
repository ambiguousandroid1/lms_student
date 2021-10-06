package com.example.lmsstudentkotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lmsstudentkotlin.MainActivity
import com.example.lmsstudentkotlin.R
import com.example.lmsstudentkotlin.fragment.Attendance
import com.example.lmsstudentkotlin.fragment.Home
import com.example.lmsstudentkotlin.fragment.Notification
import com.example.lmsstudentkotlin.fragment.TimeTableFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity() {

    lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val firstFragment= Home()
//        val secondFragment=TimeTableFragment()
//        val thirdFragment= Attendance()
        val fourthFragment= Notification()
        setCurrentFragment(firstFragment)


        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(firstFragment)
//                R.id.timet->setCurrentFragment(secondFragment)
//                R.id.attend->setCurrentFragment(thirdFragment)
                R.id.noti->setCurrentFragment(fourthFragment)

                R.id.logout->{
                    val sharedPreferences: SharedPreferences = this.getSharedPreferences("kotlinsharedpreference", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor =  sharedPreferences.edit()

                    editor.putString("uID","")
                    editor.apply()
                    editor.commit()
                    startActivity(Intent(this, MainActivity::class.java))
                }


            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framecontainer,fragment)
                commit()
            }
}