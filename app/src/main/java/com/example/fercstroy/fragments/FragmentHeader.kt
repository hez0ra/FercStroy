package com.example.fercstroy.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.fercstroy.ActiveUser
import com.example.fercstroy.LoginActivity
import com.example.fercstroy.R
import com.example.fercstroy.RegisterActivity
import de.hdodenhof.circleimageview.CircleImageView

class FragmentHeader : Fragment() {

    private var accIcon: CircleImageView? = null
    private var greeting: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accIcon = view.findViewById(R.id.header_account)
        greeting = view.findViewById(R.id.header_greeting)

        accIcon?.setOnClickListener{ account() }

        if(ActiveUser.getUser() != null) {
            greeting?.text = "Добрый день, ${ActiveUser.getUser()!!.fio.split(" ").let {"${it[1]} ${it[2]}"}}"
            Glide.with(this)
                .load(ActiveUser.getAvatar())
                .apply(RequestOptions.skipMemoryCacheOf(true)) // Не кэшировать изображение в памяти
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) // Не кэшировать изображение на диске
                .into(accIcon!!)
        }
        else{
            greeting?.text = "Добрый день"
        }

    }

    override fun onResume() {
        super.onResume()
        if(ActiveUser.getUser() != null) {
            greeting?.text = "Добрый день, ${ActiveUser.getUser()!!.fio.split(" ").let {"${it[1]} ${it[2]}"}}"
            Glide.with(this)
                .load(ActiveUser.getAvatar())
                .apply(RequestOptions.skipMemoryCacheOf(true)) // Не кэшировать изображение в памяти
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) // Не кэшировать изображение на диске
                .into(accIcon!!)
        }
        else{
            greeting?.text = "Добрый день"
        }
    }

    private fun account(){
        val intent: Intent = if (ActiveUser.getUser() != null) Intent(activity, RegisterActivity::class.java)
        else Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

}