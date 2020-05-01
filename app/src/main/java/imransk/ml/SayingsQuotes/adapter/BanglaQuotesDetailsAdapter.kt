package imransk.ml.SayingsQuotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.model.BanglaQuotes
import kotlinx.android.synthetic.main.item_details.view.*
import java.util.ArrayList


@Suppress("DEPRECATION")
class BanglaQuotesDetailsAdapter(
    var context: Context,
    var categoryDetailsClassArrayList: ArrayList<BanglaQuotes>
) : RecyclerView.Adapter<BanglaQuotesDetailsAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        public fun setData(
            categoryDetailsClass: BanglaQuotes,
            context: Context
        ) {
            itemView.xView.text = " “  ${categoryDetailsClass.quote!!.trim()} ” "

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_details,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return categoryDetailsClassArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false);
        holder.setData(categoryDetailsClassArrayList[position], context);
    }
}