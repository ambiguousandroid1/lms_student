package com.example.lmsstudentkotlin.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.lmsstudentkotlin.R
import com.example.lmsstudentkotlin.activity.*

class Home : Fragment() {


    lateinit var cardSubjects : CardView
    lateinit var card_live_class : CardView
    lateinit var cardProfile : CardView
    lateinit var notes_card : CardView
    lateinit var recClass : CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view : View = inflater.inflate(R.layout.fragment_home, container, false)

        cardSubjects  = view.findViewById(R.id.cardSubjects)
        cardSubjects.setOnClickListener({
            var intent = Intent(context, Subjects::class.java)
            startActivity(intent)
        })




        card_live_class = view.findViewById(R.id.card_live_class)
        card_live_class.setOnClickListener {
            var intent = Intent(context, LiveClassSelect::class.java)
            startActivity(intent)

        }

        cardProfile = view.findViewById(R.id.cardProfile)
        cardProfile.setOnClickListener({
            var intent = Intent(context, Profile::class.java)
            startActivity(intent)
        })

        notes_card = view.findViewById(R.id.notes_card)
        notes_card.setOnClickListener({
            var intent = Intent(context, NotesSelect::class.java)
            startActivity(intent)
        })

        recClass = view.findViewById(R.id.recClass)
        recClass.setOnClickListener({
            var intent = Intent(context, RecordedClass::class.java)
            startActivity(intent)
        })


        return view
    }


}