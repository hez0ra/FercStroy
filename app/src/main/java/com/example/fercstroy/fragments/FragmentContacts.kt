package com.example.fercstroy.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.fercstroy.R
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_conctacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.contacts_map)
        instagram = view.findViewById(R.id.contacts_instagram)
        telegram = view.findViewById(R.id.contacts_telegram)
        vk = view.findViewById(R.id.contacts_vk)
        telegramType = view.findViewById(R.id.contacts_telegram_type)
        whatsappType = view.findViewById(R.id.contacts_whatsapp_type)


        mapView?.map?.move(CameraPosition(Point(53.941246, 27.667212), 13.0f, 0.0f, 0.0f))
        mapObjects = mapView?.map?.mapObjects
        setMapMarker(53.941246, 27.667212)

        instagram?.setOnClickListener{
            openLink("https://www.instagram.com/fercstroy/")
        }
        telegram?.setOnClickListener{
            openLink("https://t.me/fercgroup")
        }
        vk?.setOnClickListener{
            openLink("https://vk.com/fercstroy")
        }
        telegramType?.setOnClickListener{
            openLink("http://t.me/ferc_communication")
        }
        telegramType?.setOnClickListener{
            openLink("https://api.whatsapp.com/send?phone=375293682991")
        }

    }

    private fun setMapMarker(latitude: Double, longitude: Double) {
        if (mapObjects != null){
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