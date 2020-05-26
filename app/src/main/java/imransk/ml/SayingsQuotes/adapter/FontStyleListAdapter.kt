package imransk.ml.myquotesjava.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.activity.WriteOwnQuotes

class FontStyleListAdapter(var context: Context, var textStyleNameList: ArrayList<String>, var fontFileList: ArrayList<String>) : RecyclerView.Adapter<FontStyleListAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var layoutInflater = LayoutInflater.from(context).inflate(R.layout.item_text_style, parent, false);
        return ViewHolderClass(layoutInflater)
    }

    override fun getItemCount(): Int {
        return textStyleNameList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        holder.textView.text = textStyleNameList[position]

        val tf = Typeface.createFromAsset(context.assets,
                fontFileList[position])
        holder.textView.typeface=tf

        return holder.setvalue(textStyleNameList[position])
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var textView =itemView.findViewById<TextView>(R.id.textStyleTV)

        fun setvalue(i: String) {



        }
    }
}