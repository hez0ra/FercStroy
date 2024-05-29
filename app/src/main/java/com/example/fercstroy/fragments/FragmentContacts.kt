package com.example.fercstroy.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fercstroy.*
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class FragmentContacts : Fragment() {

    var mapView: MapView? = null
    var mapObjects: MapObjectCollection? = null
    var instagram: ImageView? = null
    var telegram: ImageView? = null
    var vk: ImageView? = null
    var telegramType: ImageView? = null
    var whatsappType: ImageView? = null
    var fio: EditText? = null
    var email: EditText? = null
    var phone: EditText? = null
    var checkBox: CheckBox? = null
    var btnSend: Button? = null
    lateinit var dbHelper: DbHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conctacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = DbHelper(requireContext(), null)

        mapView = view.findViewById(R.id.contacts_map)
        instagram = view.findViewById(R.id.contacts_instagram)
        telegram = view.findViewById(R.id.contacts_telegram)
        vk = view.findViewById(R.id.contacts_vk)
        telegramType = view.findViewById(R.id.contacts_telegram_type)
        whatsappType = view.findViewById(R.id.contacts_whatsapp_type)
        fio = view.findViewById(R.id.contacts_fio)
        email = view.findViewById(R.id.contacts_email)
        phone = view.findViewById(R.id.contacts_phone)
        btnSend = view.findViewById(R.id.contacts_button_send)
        checkBox = view.findViewById(R.id.contacts_checkbox)

        if(ActiveUser.getUser() != null){
            val user = ActiveUser.getUser()
            fio?.setText(user?.fio)
            phone?.setText(user?.phone)
            email?.setText(user?.email)
        }


        mapView?.map?.move(CameraPosition(Point(53.941246, 27.667212), 13.0f, 0.0f, 0.0f))
        mapObjects = mapView?.map?.mapObjects
        setMapMarker(53.941246, 27.667212)

        instagram?.setOnClickListener {
            openLink("https://www.instagram.com/fercstroy/")
        }
        telegram?.setOnClickListener {
            openLink("https://t.me/fercgroup")
        }
        vk?.setOnClickListener {
            openLink("https://vk.com/fercstroy")
        }
        telegramType?.setOnClickListener {
            openLink("http://t.me/ferc_communication")
        }
        whatsappType?.setOnClickListener {
            openLink("https://api.whatsapp.com/send?phone=375293682991")
        }

        btnSend?.setOnClickListener {
            val Fio: String = fio?.text.toString().trim()
            val Phone: String = phone?.text.toString().replace(" ", "")
            val Email: String = email?.text.toString().trim()
            if (Fio == "" || Email == "" || Phone == "") {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else if (!isValidFio(Fio)) {
                fio?.error = "Введите ФИО в формате `Иванов Иван Иванович`"
            } else if (!isValidEmail(Email)) {
                phone?.error = "Некорректный email"
            } else if (!isValidPhone(Phone)) {
                email?.error = "Введите номер телефона в формате `+375291234568`"
            } else if(!checkBox!!.isChecked){
                Toast.makeText(requireContext(), "Необходимо согласие на обработку данных", Toast.LENGTH_SHORT).show()
            }
            else {
                dbHelper.addRequest(Request(Fio, Phone, Email, "none"))
                fio!!.text.clear()
                phone!!.text.clear()
                email!!.text.clear()
                Toast.makeText(requireContext(), "Заявка успешно отправлена", Toast.LENGTH_LONG).show()
            }

        }

    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(email)
    }

    fun isValidFio(fio: String): Boolean {
        val fioRegex = Regex("[a-zA-Zа-яА-яёЁ]+ [a-zA-Zа-яА-яёЁ]+ [a-zA-Zа-яА-яёЁ]+")
        return fioRegex.matches(fio)
    }

    fun isValidPhone(phone: String): Boolean{
        val phoneRegex = Regex("\\+[0-9]{1,15}")
        return phoneRegex.matches(phone)
    }


    private fun setMapMarker(latitude: Double, longitude: Double) {
        if (mapObjects != null) {
            val point = Point(latitude, longitude)

            // Масштабируем изображение маркера
            val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_map_marker)
            val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 80, 80, false)
            val imageProvider = ImageProvider.fromBitmap(scaledBitmap)

            val placemark: PlacemarkMapObject = mapObjects!!.addPlacemark(point)
            placemark.setIcon(imageProvider)
        }
    }

    private fun openLink(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onStop() {
        mapView?.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        mapView?.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

}
