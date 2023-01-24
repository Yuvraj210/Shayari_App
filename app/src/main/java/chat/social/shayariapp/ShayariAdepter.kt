package chat.social.shayariapp

import android.animation.ObjectAnimator
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shayari_layout.view.*
import java.util.*


class ShayariAdepter(val shayariShow: Shayari_Show, val shayari_list: ArrayList<ShayariModal>) :RecyclerView.Adapter<ShayariAdepter.ViewHolder>(){
    val list_Color= arrayListOf("#feca57","#48dbf","#ff6b6b","#ff9ff3","#1dd1a1")
    class ViewHolder(item: View):RecyclerView.ViewHolder(item) {
        val txtShayari=item.txtShayari
        val shareAll=item.allShare
        val copyitem=item.copysayari
        val whatsApp=item.whatsUPshare
        val CardView=item.cardAnimation

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(shayariShow).inflate(R.layout.shayari_layout,parent,false))
    }

    override fun getItemCount(): Int {
     return  shayari_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position%5==0){
            holder.txtShayari.setBackgroundColor(Color.parseColor(list_Color[0]))

        }
        else if (position%5==1) {
            holder.txtShayari.setBackgroundColor(Color.parseColor("#FF018786"))
        }

        else if (position%5==2) {
            holder.txtShayari.setBackgroundColor(Color.parseColor("#ff9ff3"))
        }
//
        else if (position%5==3) {
            holder.txtShayari.setBackgroundColor(Color.parseColor("#1dd1a1"))
        }

        else if (position%5==4) {
            holder.txtShayari.setBackgroundColor(Color.parseColor("#ff9ff3"))
        }

        holder.txtShayari.text=shayari_list[position].data.toString()
        holder.shareAll.setOnClickListener {

            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayari_list[position].data.toString())

                shayariShow.startActivity(whatsappIntent)
        }

        holder.copyitem.setOnClickListener {

            val clipboard: ClipboardManager? =
                shayariShow.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayari_list[position].data.toString())
            Toast.makeText(shayariShow, "Copy", Toast.LENGTH_SHORT).show()
            clipboard?.setPrimaryClip(clip)

        }

        holder.whatsApp.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayari_list[position].data.toString())

            shayariShow.startActivity(whatsappIntent)
        }

        ObjectAnimator.ofFloat(holder.CardView,View.TRANSLATION_X,-300f,0f).apply {
            duration =800
            start()
        }
    }
}