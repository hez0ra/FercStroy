package com.example.fercstroy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fercstroy.R

class FragmentMain : Fragment() {

    private val logos = intArrayOf(
        R.drawable.logo_belorusneft,
        R.drawable.logo_savushkin,
        R.drawable.logo_tomografy,
        R.drawable.logo_darida,
        R.drawable.logo_labfarma,
        R.drawable.logo_legmash
    )
    private var ourProjects: GridLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ourProjects = view.findViewById(R.id.our_projects)

        for (logo in logos) {
            val itemLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                gravity = android.view.Gravity.CENTER
                setPadding(8, 8, 8, 8)
                background = ContextCompat.getDrawable(context, R.drawable.rounded_background)
            }

            val imageView = ImageView(requireContext()).apply {
                setImageResource(logo)
                adjustViewBounds = true
                maxWidth = 200 // Установите максимальные размеры изображения
                maxHeight = 200
            }

            itemLayout.addView(imageView)

            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f)
                setMargins(8, 8, 8, 8)
            }

            itemLayout.layoutParams = params
            ourProjects!!.addView(itemLayout)
        }


    }

}