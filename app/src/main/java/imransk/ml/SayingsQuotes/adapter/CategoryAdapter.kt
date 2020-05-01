package imransk.ml.SayingsQuotes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.model.CategoryClass
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter(var context: Context, var categeryList: ArrayList<CategoryClass>) :
    RecyclerView.Adapter<CategoryAdapter.viewholderClass>() {


    public class viewholderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setvalue(s: String) {

            if (adapterPosition%2==0){
                itemView.categeryImageTV.setBackgroundColor(Color.parseColor("#DAB467"))
            }else{
                itemView.categeryImageTV.setBackgroundColor(Color.parseColor("#BFB1D2"))
            }

            if (s.equals("Live Quotes",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.live_quotes)

            }
            if (s.equals("bangla",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.bangladesh)

            }
            if (s.equals("Angry",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.angry)

            }
            if (s.equals("attitude",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.attitude)

            }
            if (s.equals("awkward moment",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.awkward_moment)

            }
            if (s.equals("beautiful",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.beatiful)

            }
            if (s.equals("bike",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.bike)

            }
            if (s.equals("brother",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.brother)

            }
            if (s.equals("cheat",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.cheat)

            }
            if (s.equals("clever",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.clever)

            }
            if (s.equals("cool",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.cool)

            }
            if (s.equals("cute",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.cute)

            }
            if (s.equals("emotional",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.emotional)

            }
            if (s.equals("facebook",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.facebook)

            }
            if (s.equals("flirt",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.flirt)

            }
            if (s.equals("friends",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.friends)

            }
            if (s.equals("god",ignoreCase = true)){
//                itemView.categeryTV.setBackgroundResource(R.drawable.friends)

            }
            if (s.equals("good night",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.goodnight)

            }
            if (s.equals("happiness",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.happiness)

            }
            if (s.equals("hot",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.hot)

            }
            if (s.equals("inspirational",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.inspiretional)

            }
            if (s.equals("jealous",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.jealous)

            }
            if (s.equals("life",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.life)

            }
            if (s.equals("romantic",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.romantic)

            }
            if (s.equals("marriage",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.marriage)

            }
            if (s.equals("monday",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.monday)

            }
            if (s.equals("music",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.music)

            }
            if (s.equals("party",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.party)

            }

            if (s.equals("pets",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.pets)

            }
            if (s.equals("quotes",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.quotes)

            }
            if (s.equals("relationship",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.releationship)

            }
            if (s.equals("lonely",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.lonley)

            }
            if (s.equals("hate you",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.hate_you)

            }
            if (s.equals("hurt",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.hurt)

            }

            if (s.equals("pain",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.pain)

            }
            if (s.equals("school",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.school)

            }
            if (s.equals("selfish",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.selfish)

            }
            if (s.equals("sick",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.sick)

            }
            if (s.equals("sister",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.sister)

            }
            if (s.equals("smile",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.smile)

            }
            if (s.equals("summer",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.summer)

            }

            if (s.equals("sweet",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.sweet)
            }

            if (s.equals("technology",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.technology)
            }

            if (s.equals("tired",ignoreCase = true)){
//                itemView.categeryImageTV.setBackgroundResource(R.drawable.tr)
            }

            if (s.equals("trust",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.trust)
            }
            if (s.equals("whatsapp",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.whatsapp)
            }
            if (s.equals("wife",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.wife)
            }
            if (s.equals("wise",ignoreCase = true)){
//                itemView.categeryImageTV.setBackgroundResource(R.drawable.w)
            }
            if (s.equals("work",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.work)
            }

            if (s.equals("work",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.work)
            }
            if (s.equals("mother's day",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.mother)
            }
            if (s.equals("moving on",ignoreCase = true)){
//                itemView.categeryImageTV.setBackgroundResource(R.drawable._on)
            }

            if (s.equals("forgiveness",ignoreCase = true)){
                itemView.categeryImageTV.setBackgroundResource(R.drawable.forgiveness)
            }


            itemView.categeryTV.text = " “ $s ” "


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderClass {


        return viewholderClass(
            LayoutInflater.from(context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return categeryList.size
    }

    override fun onBindViewHolder(holder: viewholderClass, position: Int) {



        holder.setvalue(categeryList[position].name)
    }
}