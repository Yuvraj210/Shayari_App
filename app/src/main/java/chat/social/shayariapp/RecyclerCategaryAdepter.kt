package chat.social.shayariapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.categary_layout.view.*


class RecyclerCategaryAdepter(val contxer: Context, val list: ArrayList<categeryModal>):RecyclerView.Adapter<RecyclerCategaryAdepter.Viewholder>(){
    class Viewholder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val textna=itemview.txtcat
        val llbmain=itemview.llbMain

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
      return Viewholder(LayoutInflater.from(contxer).inflate(R.layout.categary_layout,parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

      holder.textna.text=list[position].name.toString()

        holder.llbmain.setOnClickListener {
            val intent=Intent(contxer,Shayari_Show::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name)
            contxer.startActivity(intent)
        }
    }
}