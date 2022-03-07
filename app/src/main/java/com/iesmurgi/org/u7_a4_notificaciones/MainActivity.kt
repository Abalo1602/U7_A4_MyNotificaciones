package com.iesmurgi.org.u7_a4_notificaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.PersistableBundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var miBoton=findViewById<Button>(R.id.bNotificacion)

        miBoton.setOnClickListener { crearCanalNotificacion()}
    }

    private fun crearCanalNotificacion(){
        if (Build.VERSION.SDK_INT >= VERSION_CODES.O){
            val nombre = getString(R.string.nombre_canal)
            val canalId = getString(R.string.id_canal)
            val descripcion = getString(R.string.descripcion_canal)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val canal = NotificationChannel(canalId, nombre, importance).apply {
                description=descripcion
            }

            val nm: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(canal)
        }
    }

    private fun generarNotificacion(){
        val notificacionId = 0
        val canalId = getString(R.string.id_canal)
        val largeIcon = BitmapFactory.decodeResource(
            resources,
            R.drawable.simbol
        )

        val notificacion = NotificationCompat.Builder(this, canalId)
            .setLargeIcon(largeIcon)
            .setSmallIcon(R.drawable.ic_circle_notifications)
            .setContentTitle("PMDM Notificaciones en Android con Kotlin")
            .setContentText("1. Crear notificaciones")
            .setSubText("iesmurgi.org")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_bookmark, "Leer más tarde", null)
            .build()

        with(NotificationManagerCompat.from(this)){
            notify(notificacionId, notificacion)
        }
    }
}