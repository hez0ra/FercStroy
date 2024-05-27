package com.example.fercstroy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.fercstroy.R

class FragmentFooter : Fragment() {

    var main: ImageView? = null
    var about: ImageView? = null
    var contacts: ImageView? = null
    var activeFragment: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_footer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = requireActivity().supportFragmentManager

        main = view.findViewById(R.id.footer_home)
        about = view.findViewById(R.id.footer_about)
        contacts = view.findViewById(R.id.footer_contacts)

        main?.setOnClickListener{

            if(activeFragment != 0){
                var line: View = view.findViewById(R.id.footer_line_first)
                line.visibility = View.INVISIBLE
                line = view.findViewById(R.id.footer_line_second)
                line.visibility = View.VISIBLE
                line = view.findViewById(R.id.footer_line_third)
                line.visibility = View.VISIBLE
                val fragmentTransaction = fragmentManager.beginTransaction()

                val mainFragment = FragmentMain()
                fragmentTransaction.replace(R.id.main_container, mainFragment)

                fragmentTransaction.commit()
                activeFragment = 0
            }

        }


        about?.setOnClickListener{

            if(activeFragment != 1){
                var line: View = view.findViewById(R.id.footer_line_first)
                line.visibility = View.VISIBLE
                line = view.findViewById(R.id.footer_line_second)
                line.visibility = View.INVISIBLE
                line = view.findViewById(R.id.footer_line_third)
                line.visibility = View.VISIBLE
                val fragmentTransaction = fragmentManager.beginTransaction()

                val mainFragment = FragmentAbout()
                fragmentTransaction.replace(R.id.main_container, mainFragment)

                fragmentTransaction.commit()
                activeFragment = 1
            }

        }

        contacts?.setOnClickListener{

            if(activeFragment != 2){
                var line: View = view.findViewById(R.id.footer_line_first)
                line.visibility = View.VISIBLE
                line = view.findViewById(R.id.footer_line_second)
                line.visibility = View.VISIBLE
                line = view.findViewById(R.id.footer_line_third)
                line.visibility = View.INVISIBLE
                val fragmentTransaction = fragmentManager.beginTransaction()

                val mainFragment = FragmentContacts()
                fragmentTransaction.replace(R.id.main_container, mainFragment)

                fragmentTransaction.commit()
                activeFragment = 2
            }

        }

    }

}