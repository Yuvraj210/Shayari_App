package chat.social.shayariapp

import android.app.DownloadManager.Request
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdRequest.Builder
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Objects

class MainActivity : AppCompatActivity() {

    lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        db=FirebaseFirestore.getInstance()



        db.collection("Shayari").addSnapshotListener { value, error ->

            val list = arrayListOf<categeryModal>()
            val data=value?.toObjects(categeryModal::class.java)
             list.addAll(data!!)
            RecyclerCat.layoutManager=LinearLayoutManager(this)
            RecyclerCat.adapter=RecyclerCategaryAdepter(this,list)

        }

        MenuButton.setOnClickListener {
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)){

                drawer_layout.closeDrawer(Gravity.LEFT)
                }
                else
                {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }

        navigationView.setNavigationItemSelectedListener {

            when(it.itemId)
            {

            R.id.NShare->{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert Subject here")
                val app_url = " https://play.google.com/store/apps/details?id=chat.social.shayariapp"
                shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
                true
            }

                R.id.NRate->{
                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
                    }

                    true}
                R.id.NMore->{
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }
                    true}

                else-> false
            }

        }


    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(Gravity.LEFT)){
            drawer_layout.closeDrawer(Gravity.LEFT)
        }
        else
        {
            super.onBackPressed()
        }
    }
}