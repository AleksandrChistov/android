package ru.androidstudy

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder


class MainActivity : AppCompatActivity() {
    private val btnMorning by lazy { findViewById<Button>(R.id.btnMorning) }
    private val btnDay by lazy { findViewById<Button>(R.id.btnDay) }
    private val btnEvening by lazy { findViewById<Button>(R.id.btnEvening) }
    private val btnNight by lazy { findViewById<Button>(R.id.btnNight) }
    private val btnPrince by lazy { findViewById<Button>(R.id.btnPrince) }
    private val btnPlanet by lazy { findViewById<Button>(R.id.btnPlanet) }
    private val btnVolcano by lazy { findViewById<Button>(R.id.btnVolcano) }
    private val btnRose by lazy { findViewById<Button>(R.id.btnRose) }
    private val btnBreakfast by lazy { findViewById<Button>(R.id.btnBreakfast) }

    private val imgMorning by lazy { findViewById<ImageView>(R.id.imgMorning) }
    private val imgDay by lazy { findViewById<ImageView>(R.id.imgDay) }
    private val imgEvening by lazy { findViewById<ImageView>(R.id.imgEvening) }
    private val imgNight by lazy { findViewById<ImageView>(R.id.imgNight) }

    private val layoutMorning by lazy { findViewById<ConstraintLayout>(R.id.layoutMorning) }

    private var notificationMorning = 1
    private var notificationDay = 2
    private var notificationEvening = 3
    private var notificationNight = 4
    private val CHANNEL_ID = "channelID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMorning.setOnClickListener { view -> btnMorningClick(view) }
        btnDay.setOnClickListener { view -> btnDayClick(view) }
        btnEvening.setOnClickListener { view -> btnEveningClick(view) }
        btnNight.setOnClickListener { view -> btnNightClick(view) }
        btnPrince.setOnClickListener { view -> btnPrinceClick(view) }
        btnPlanet.setOnClickListener { view -> btnPlanetClick(view) }
        btnVolcano.setOnClickListener { view -> btnVolcanoClick(view) }
        btnRose.setOnClickListener { view -> btnRoseClick(view) }
        btnBreakfast.setOnClickListener { view -> btnBreakfastClick(view) }
    }

    private fun btnMorningClick(view: View) {
        layoutMorning.visibility = View.VISIBLE
        imgDay.visibility = View.INVISIBLE
        imgEvening.visibility = View.INVISIBLE
        imgNight.visibility = View.INVISIBLE
        notifyUser("Утро", "Привести в порядок свою планету", R.drawable.cleaning, notificationMorning)
    }

    private fun btnDayClick(view: View) {
        layoutMorning.visibility = View.INVISIBLE
        imgDay.visibility = View.VISIBLE
        imgEvening.visibility = View.INVISIBLE
        imgNight.visibility = View.INVISIBLE
        notifyUser("День", "Полить розу", R.drawable.rose, notificationDay)
    }

    private fun btnEveningClick(view: View) {
        layoutMorning.visibility = View.INVISIBLE
        imgDay.visibility = View.INVISIBLE
        imgEvening.visibility = View.VISIBLE
        imgNight.visibility = View.INVISIBLE
        notifyUser("Вечер", "Закрыть розу ширмой", R.drawable.home, notificationEvening)
    }

    private fun btnNightClick(view: View) {
        layoutMorning.visibility = View.INVISIBLE
        imgDay.visibility = View.INVISIBLE
        imgEvening.visibility = View.INVISIBLE
        imgNight.visibility = View.VISIBLE
        notifyUser("Ночь", "Полюбоваться закатом", R.drawable.sunset, notificationNight)
    }

    private fun btnPrinceClick(view: View) {
        showToast("Маленький принц")
    }

    private fun btnPlanetClick(view: View) {
        showToast("Астероид Б-612. Планета, на которой живет Маленький принц")
    }

    private fun btnVolcanoClick(view: View) {
        showToast("Потухший вулкан. О нем тоже нужно заботиться")
    }

    private fun btnRoseClick(view: View) {
        showToast("Роза. Ее нужно поливать, а на ночь укрывать ширмой и колпаком")
    }

    private fun btnBreakfastClick(view: View) {
        showToast("Действующий вулкан. На нем удобно разогревать завтрак")
    }

    private fun notifyUser(title: String, text: String, icon: Int, id: Int) {
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        val resultIntent = Intent(this, MainActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)

        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        mNotificationManager.notify(id, mBuilder.build())
    }

    private fun showToast(text: String) {
        val myToast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
        myToast.setGravity(Gravity.BOTTOM, 0, 0)
        myToast.show()
    }
}