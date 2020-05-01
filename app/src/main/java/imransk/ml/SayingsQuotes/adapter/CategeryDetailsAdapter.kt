package imransk.ml.SayingsQuotes.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.model.CategoryDetailsClass
import kotlinx.android.synthetic.main.item_details.*
import kotlinx.android.synthetic.main.item_details.view.*
import kotlinx.android.synthetic.main.item_details.view.xView


@Suppress("DEPRECATION")
class CategeryDetailsAdapter(
    var context: Activity,
    var categoryDetailsClassArrayList: ArrayList<CategoryDetailsClass>
) : RecyclerView.Adapter<CategeryDetailsAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        public fun setData(
            categoryDetailsClass: CategoryDetailsClass,
            context: Context
        ) {
            val custom_font: Typeface = Typeface.createFromAsset(context.assets, "fonts/aa.ttf")

            itemView.xView.setTypeface(custom_font)
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