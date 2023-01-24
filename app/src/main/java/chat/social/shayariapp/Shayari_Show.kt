package chat.social.shayariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_shayari_show.*


class Shayari_Show : AppCompatActivity() {
    lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shayari_show)

        MobileAds.initialize(this) {

            val adRequest= AdRequest.Builder().build()
            adView.loadAd(adRequest)
            adView.adListener = object: AdListener() {
                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }

                override fun onAdFailedToLoad(adError : LoadAdError) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdImpression() {
                    // Code to be executed when an impression is recorded
                    // for an ad.
                }

                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }
            }

        }


        val name=intent.getStringExtra("name")
        val id=intent.getStringExtra("id")


        db=FirebaseFirestore.getInstance()
        db.collection("Shayari").document(id!!).collection("all",).
        addSnapshotListener { value, error ->

            val shayari_list= arrayListOf<ShayariModal>()
            val data=value?.toObjects(ShayariModal::class.java)
            shayari_list.addAll(data!!)
            RecyclerShayari.layoutManager=LinearLayoutManager(this)
            RecyclerShayari.adapter=ShayariAdepter(this,shayari_list)
        }

    }
}