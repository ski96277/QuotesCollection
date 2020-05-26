package imransk.ml.myquotesjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.activity.WriteOwnQuotes

class ImageAdapter(var context: WriteOwnQuotes, var imageList: ArrayList<Int>) : RecyclerView.Adapter<ImageAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var layoutInflater = LayoutInflater.from(context).inflate(R.layout.item_image_layout, parent, false);
        return ViewHolderClass(layoutInflater)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        holder.imageview.setImageDrawable(context.resources.getDrawable(imageList[position]))

        return holder.setvalue(imageList[position])
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var imageview =itemView.findViewById<ImageView>(R.id.imageBackgroundIV)

        fun setvalue(i: Int) {

        }
    }
}