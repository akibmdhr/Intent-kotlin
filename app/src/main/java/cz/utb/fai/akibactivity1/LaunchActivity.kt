package cz.utb.fai.akibactivity1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi


class LaunchActivity : AppCompatActivity() {
    lateinit var textView: TextView
    // some transient state for the activity instance
    var gameState: String? = null
    val GAME_STATE_KEY = "GAME_STATE_KEY"

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        gameState = savedInstanceState?.getString(GAME_STATE_KEY)
            if (gameState == null)
                gameState = "LEVEL 1"
        setContentView(R.layout.activity_launch)

        Log.v("MYAPP", "The application has been created")
        textView = findViewById(R.id.textView)

        findViewById<Button>(R.id.callButton).setOnClickListener() {

            // val callUri : Uri =Uri.parse ("tel:5551234")
            // val callIntent = Intent (Intent.ACTION_DIAL,callUri)
            //startActivity(callIntent)
            val callIntent: Intent = Uri.parse("tel:5551234").let { number ->
                Intent(Intent.ACTION_DIAL, number)

            }
            startActivity(callIntent)
        }
        findViewById<Button>(R.id.buttonMap).setOnClickListener() {
            val mapIntent: Intent = Uri.parse(
                "geo:49.195061,16.606836"
            ).let { location ->
                // Or map point based on latitude/longitude
                // val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14") // z param is zoom level
                //val mapIntent: Uri = Uri.parse("geo:49.195061,16.606836")
                Intent(Intent.ACTION_VIEW, location)
            }
            startActivity(mapIntent)
        }
        findViewById<Button>(R.id.linkBtn).setOnClickListener() {
            val webIntent: Intent = Uri.parse("https://www.utb.cz").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }



        findViewById<Button>(R.id.calBtn).setOnClickListener() {
            // Event is on January 23, 2021 -- from 7:30 AM to 10:30 AM.

            val calendar = Intent (Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
                val beginTime: Calendar = Calendar.getInstance().apply {
                    set(2021, 12, 31, 12, 59)
                }
                val endTime = Calendar.getInstance().apply {
                    set(2021, 12, 31, 24, 59)
                }
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                putExtra(CalendarContract.Events.TITLE, "New Year")
                putExtra(CalendarContract.Events.EVENT_LOCATION, "Happy New Year")

            }
            startActivity(calendar)

        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        textView.text = savedInstanceState?.getString(GAME_STATE_KEY)
        Log.v("MYAPP","restored")
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    override fun onSaveInstanceState(outState: Bundle) {


        outState.putString(GAME_STATE_KEY, gameState)
        //putString(TEXT_VIEW_KEY, textView.text.toString())

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
        Log.v("MYAPP","Saved" + gameState)
    }
    override fun onStart() {
        super.onStart()
        Log.v("MYAPP","The application has started")
    }
    override fun onResume() {
        super.onResume()
        Log.v("MYAPP","The application has resumed")
    }
    override fun onPause() {
        super.onPause()
        Log.v("MYAPP","The application has paused")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.v("MYAPP","The application has destroyed")
    }
}