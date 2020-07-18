package imransk.ml.SayingsQuotes.activity

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.adapter.BanglaQuotesDetailsAdapter
import imransk.ml.SayingsQuotes.model.BanglaQuotes
import imransk.ml.myquotesjava.adapter.FontStyleListAdapter
import imransk.ml.myquotesjava.adapter.RecyclerItemClickListenr
import kotlinx.android.synthetic.main.activity_bangla_quotes.*
import kotlinx.android.synthetic.main.font_style_alert.*
import kotlinx.android.synthetic.main.item_details.view.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.*

class BanglaQuotesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    var firebaseFirestore: FirebaseFirestore? = null
    lateinit var banglaQuotesArrayList: ArrayList<BanglaQuotes>
    var banglaQuotesDetailsAdapter: BanglaQuotesDetailsAdapter? = null
    lateinit var context:Context;

    var rnds=9;
    var imagelist=ArrayList<Int>()
    lateinit var collection:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bangla_quotes)
        // Hide the status bar.
        val actionBar: android.app.ActionBar? = actionBar
        actionBar?.hide()

        recyclerView = findViewById(R.id.banglaQuotesRecylerView)
        firebaseFirestore = FirebaseFirestore.getInstance()
        context=this;

        banglaQuotesArrayList = ArrayList()
        imagelist=ArrayList<Int>()

        Toast.makeText(context, "Double Tap To Change The Background", Toast.LENGTH_SHORT).show();
        collection=intent.getStringExtra("collectionName");

        imagelist.add(R.drawable.one)
        imagelist.add(R.drawable.two)
        imagelist.add(R.drawable.three)
        imagelist.add(R.drawable.four)
        imagelist.add(R.drawable.five)
        imagelist.add(R.drawable.six)
        imagelist.add(R.drawable.seven)
        imagelist.add(R.drawable.eight)
        imagelist.add(R.drawable.nine)
        imagelist.add(R.drawable.ten)
        imagelist.add(R.drawable.eleven)
        imagelist.add(R.drawable.tweelve)
        imagelist.add(R.drawable.thirtheen)
        imagelist.add(R.drawable.facebook)
        imagelist.add(R.drawable.angry)
        imagelist.add(R.drawable.attitude)
        imagelist.add(R.drawable.awkward_moment)
        imagelist.add(R.drawable.beatiful)
        imagelist.add(R.drawable.bike)
        imagelist.add(R.drawable.brother)
        imagelist.add(R.drawable.cheat)
        imagelist.add(R.drawable.clever)
        imagelist.add(R.drawable.cool)
        imagelist.add(R.drawable.cute)
        imagelist.add(R.drawable.flirt)
        imagelist.add(R.drawable.forgiveness)
        imagelist.add(R.drawable.friends)
        imagelist.add(R.drawable.goodnight)
        imagelist.add(R.drawable.happiness)
        imagelist.add(R.drawable.hate_you)
        imagelist.add(R.drawable.hot)
        imagelist.add(R.drawable.hurt)
        imagelist.add(R.drawable.inspiretional)
        imagelist.add(R.drawable.jealous)
        imagelist.add(R.drawable.life)
        imagelist.add(R.drawable.live_quotes)
        imagelist.add(R.drawable.lonley)
        imagelist.add(R.drawable.love)
        imagelist.add(R.drawable.marriage)
        imagelist.add(R.drawable.monday)
        imagelist.add(R.drawable.mother)
        imagelist.add(R.drawable.moving_on)
        imagelist.add(R.drawable.music)
        imagelist.add(R.drawable.nine)
        imagelist.add(R.drawable.pain)
        imagelist.add(R.drawable.party)
        imagelist.add(R.drawable.pets)
        imagelist.add(R.drawable.quotes)
        imagelist.add(R.drawable.releationship)
        imagelist.add(R.drawable.romantic)
        imagelist.add(R.drawable.school)
        imagelist.add(R.drawable.selfish)
        imagelist.add(R.drawable.sick)
        imagelist.add(R.drawable.sister)
        imagelist.add(R.drawable.smile)
        imagelist.add(R.drawable.summer)
        imagelist.add(R.drawable.sweet)
        imagelist.add(R.drawable.technology)
        imagelist.add(R.drawable.tired)
        imagelist.add(R.drawable.trust)
        imagelist.add(R.drawable.wife)
        imagelist.add(R.drawable.work)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_DENIED
            ) {
                var st = ArrayList<String>();

                ActivityCompat.requestPermissions(
                    this@BanglaQuotesActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                );
            }
        }


        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        progressBar.visibility=View.VISIBLE
        firebaseFirestore!!.collection(collection.toString())
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result!!.size() > 0) {
                        for (snapshot in task.result!!) {
                            val banglaQuotes = snapshot.toObject(
                                BanglaQuotes::class.java
                            )
                            banglaQuotesArrayList!!.add(banglaQuotes)
                        }
                        progressBar.visibility=View.GONE
                        banglaQuotesDetailsAdapter =
                            BanglaQuotesDetailsAdapter(context, banglaQuotesArrayList)
                        recyclerView?.setAdapter(banglaQuotesDetailsAdapter)
                    }else{
                        progressBar.visibility=View.GONE
                        Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }.addOnFailureListener {
                progressBar.visibility=View.GONE
            }


        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListenr(
                context,
                recyclerView,
                object : RecyclerItemClickListenr.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {

                        view.xView.setOnClickListener {


                            rnds = (0 until imagelist.size).random()
                            view.imageLayout.background = context.resources.getDrawable(imagelist[rnds])
                        }

                        view.copyBtn.setOnClickListener {



                            var myClipboard: ClipboardManager? = null
                            var myClip: ClipData? = null
                            myClipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?;
                            myClip = ClipData.newPlainText("text", banglaQuotesArrayList[position].quote);
                            myClipboard?.setPrimaryClip(myClip);
                            Toast.makeText(context, "Copied..", Toast.LENGTH_SHORT).show();
                        }

                        view.fontBtn.setOnClickListener {
                            showFontStyleBottomAlert(view)
                        }

                        view.colorPickerBtn.setOnClickListener {


                            val colorPickerDialog: ColorPickerDialog =
                                ColorPickerDialog.createColorPickerDialog(context)

                            colorPickerDialog.setOnColorPickedListener { color, hexVal ->
                                //Your code here
                                view.xView.setTextColor(Color.parseColor(hexVal))
                                colorPickerDialog.setColorComponentsTextColor(color)

                            }
                            colorPickerDialog.show();

                        }
                        view.shareBtn.setOnClickListener {
                            view.imageLayout.background = context.resources.getDrawable(imagelist[rnds])

                            view.imageLayout.isDrawingCacheEnabled = true

                            try {

                                val tvImage: Bitmap = Bitmap.createBitmap( view.imageLayout.drawingCache)
                                val sendIntent = Intent()
                                sendIntent.action = Intent.ACTION_SEND
                                sendIntent.type = "image/PNG"
                                val bitmapPath =
                                    MediaStore.Images.Media.insertImage(context.contentResolver, tvImage, "title", null)
                                val bitmapUri: Uri = Uri.parse(bitmapPath)
                                sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
                                context.startActivity(sendIntent);

                            } catch (e: FileNotFoundException) {

                                e.printStackTrace()
                            }



                        }

                        return
                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                        return
                    }


                })
        )


    }



    private fun showFontStyleBottomAlert(viewTV: View) {
        var fontNameList = ArrayList<String>()
        var fontFileList = ArrayList<String>()

        fontNameList.add("alex-brush")
        fontNameList.add("Aller")
        fontNameList.add("amatic")
//        fontNameList.add("blackjack")
        fontNameList.add("Caviar-Dreams")
//        fontNameList.add("chunkfive")
//        fontNameList.add("grand-hotel")
//        fontNameList.add("great-vibes")
        fontNameList.add("kurri-island")
        fontNameList.add("lato")
//        fontNameList.add("League-Gothic")
//        fontNameList.add("montserrat")
        fontNameList.add("open-sans")
        fontNameList.add("ostrich-sans")
        fontNameList.add("oswald")
        fontNameList.add("pacifico")
//        fontNameList.add("playfair-display")
//        fontNameList.add("quicksand")
        fontNameList.add("raleway")
        fontNameList.add("roboto")
//        fontNameList.add("sofia")
//        fontNameList.add("source-sans-pro")

        fontFileList.add("fonts/AlexBrushRegular.ttf")
        fontFileList.add("fonts/Aller_Rg.ttf")
        fontFileList.add("fonts/AmaticSC_Regular.ttf")
        fontFileList.add("fonts/CaviarDreams_Italic.ttf")
        fontFileList.add("fonts/KurriIslandItaPERSONAL_Thin.ttf")
        fontFileList.add("fonts/Lato_Black.ttf")
        fontFileList.add("fonts/OpenSans_LightItalic.ttf")
        fontFileList.add("fonts/ostrich_regular.ttf")
        fontFileList.add("fonts/Oswald_ExtraLight.ttf")
        fontFileList.add("fonts/Pacifico.ttf")
        fontFileList.add("fonts/Raleway_Italic.ttf")
        fontFileList.add("fonts/Roboto_Light.ttf")


        val bottomalertDialog = BottomSheetDialog(context);
        bottomalertDialog.setContentView(R.layout.font_style_alert)
        bottomalertDialog.fontStyleRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        var fontStyleListAdapter =
            FontStyleListAdapter(this@BanglaQuotesActivity, fontNameList, fontFileList)
        bottomalertDialog.fontStyleRecyclerView.adapter = fontStyleListAdapter

        bottomalertDialog.fontStyleRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListenr(context, bottomalertDialog.fontStyleRecyclerView,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        val tf = Typeface.createFromAsset(
                            context.assets,
                            fontFileList[position]
                        )
                        viewTV.xView.typeface = tf


                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                        return
                    }

                })
        )

        bottomalertDialog.show()


    }


}