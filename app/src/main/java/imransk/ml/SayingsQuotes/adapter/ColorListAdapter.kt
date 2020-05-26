package imransk.ml.myquotesjava.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.activity.WriteOwnQuotes

class ColorListAdapter(var context: WriteOwnQuotes, var colorList: ArrayList<String>) : RecyclerView.Adapter<ColorListAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var layoutInflater = LayoutInflater.from(context).inflate(R.layout.item_color_list_layout, parent, false);
        return ViewHolderClass(layoutInflater)
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        holder.imageview.setBackgroundColor(Color.parseColor(colorList[position]));

        return holder.setvalue(colorList[position])
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var imageview =itemView.findViewById<ImageView>(R.id.colorItemImageViewID)

        fun setvalue(i: String) {

        }
    }
}