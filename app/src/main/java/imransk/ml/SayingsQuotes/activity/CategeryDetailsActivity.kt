package imransk.ml.SayingsQuotes.activity

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.adapter.CategeryDetailsAdapter
import imransk.ml.SayingsQuotes.database.DatabaseAccess
import imransk.ml.SayingsQuotes.model.CategoryDetailsClass
import imransk.ml.myquotesjava.adapter.FontStyleListAdapter
import imransk.ml.myquotesjava.adapter.RecyclerItemClickListenr
import kotlinx.android.synthetic.main.activity_categery_details.*
import kotlinx.android.synthetic.main.activity_write_own_quotes.*
import kotlinx.android.synthetic.main.font_style_alert.*
import kotlinx.android.synthetic.main.item_details.view.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.Exception

class CategeryDetailsActivity : AppCompatActivity() {

    lateinit var context: Activity
    lateinit var categeryDetailsAdapter: CategeryDetailsAdapter
    lateinit var databaseAccess: DatabaseAccess;
    lateinit var categoryDetailsClassArrayList: ArrayList<CategoryDetailsClass>
    var rnds = 9;
    var imagelist = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categery_details)
        context = this@CategeryDetailsActivity
        imagelist = ArrayList<Int>()
        Toast.makeText(context, "Double Tap To Change The Background", Toast.LENGTH_SHORT).show();
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
                    context,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                );
            }
        }
        categeryDetailsRecyclerID.layoutManager = LinearLayoutManager(context)
        var id: Int = intent.getIntExtra("id", 0);
        databaseAccess = DatabaseAccess(context)
        categoryDetailsClassArrayList = databaseAccess.getDetailsCategory(id)
//        Toast.makeText(context, "${id}", Toast.LENGTH_SHORT).show();

        if (id == 3) { // angry
            var categoryDetailsClass = CategoryDetailsClass(-1, -1, "Get mad, then get over it.", 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(
                -1,
                -1,
                "Anger is an acid that can do more harm to the vessel in which it is stored than to anything on which it is poured.",
                0
            )
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(
                -1, -1,
                "Holding on to anger is like grasping a hot coal with the intent of throwing it at someone else; you are the one who gets burned.\n" +
                        "\n", 0
            )
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 5) { // Attitude
            var categoryDetailsClass = CategoryDetailsClass(
                -1,
                -1,
                "A positive attitude causes a chain reaction of positive thoughts, events and outcomes. It is a catalyst and it sparks extraordinary results.- Wade Boggs"
                ,
                0
            )
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(
                -1,
                -1,
                "If you have a positive attitude and constantly strive to give your best effort, eventually you will overcome your immediate problems and find you are ready for greater challenges.- Pat Riley",
                0
            )
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(
                -1, -1,
                "I don't have a very positive attitude towards rappers. Miriam Margolyes", 0
            )
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 7) { // awkward moment
            var categoryDetailsClass = CategoryDetailsClass(
                -1, -1,
                "Move out of your comfort zone. You can only grow if you are willing to feel awkward and uncomfortable when you try something new. -Brian Tracy"

                , 0
            )
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(
                -1,
                -1,
                "Getting stopped in the middle of the lingerie section, when you're trying to stock up on a few things, by an older man who wants a selfie is a little bit awkward... but I don't let that get in the way of me trying to do normal things, because that is when I get to interact with people as well. Preferably not amongst the underwear, though."
                ,
                0
            )
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(
                -1,
                -1,
                "Music has generally involved a lot of awkward contraptions, a certain amount of heavy lifting.",
                0
            )
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 9) { /* beautiful */
            var categoryDetailsClass = CategoryDetailsClass(
                -1, -1,
                "A beautiful body perishes, but a work of art dies not."
                , 0
            )
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(
                -1,
                -1,
                "The most beautiful things in the world cannot be seen or even touched, they must be felt with the heart.\n"
                ,
                0
            )
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(
                -1, -1,
                "Nothing can be beautiful which is not true.", 0
            )
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }

        if (id == 11) { /* Bike */
            var categoryDetailsClass = CategoryDetailsClass(
                -1, -1,
                "Don’t buy upgrades, ride up grades – Eddy Merckx"
                , 0
            )
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(
                -1, -1,
                "Ride as much or as little, as long or as short as you feel. But ride – Eddy Merckx"
                , 0
            )
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(
                -1, -1,
                "It never gets easier, you just get faster – Greg LeMond", 0
            )
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 13) { /* Brother */
            var categoryDetailsClass = CategoryDetailsClass(
                -1, -1,
                "My brother is my only best friend. No one can replace him."
                , 0
            )
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(
                -1, -1,
                "Sometimes being a brother is even better than being a superhero. – Marc Brown"
                , 0
            )
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(
                -1, -1,
                "Brothers are what best friends can never be.", 0
            )
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }

        categeryDetailsAdapter = CategeryDetailsAdapter(context, categoryDetailsClassArrayList)
        categeryDetailsRecyclerID.adapter = categeryDetailsAdapter
        categeryDetailsRecyclerID.addOnItemTouchListener(
            RecyclerItemClickListenr(
                context,
                categeryDetailsRecyclerID,
                object : RecyclerItemClickListenr.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {

                        view.xView.setOnClickListener {


                            rnds = (0 until imagelist.size).random()
                            view.imageLayout.background =
                                context.resources.getDrawable(imagelist[rnds])
                        }

                        view.copyBtn.setOnClickListener {
                            var myClipboard: ClipboardManager? = null
                            var myClip: ClipData? = null
                            myClipboard =
                                context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?;
                            myClip = ClipData.newPlainText(
                                "text",
                                categoryDetailsClassArrayList[position].quote
                            );
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
                            view.imageLayout.background =
                                context.resources.getDrawable(imagelist[rnds])

                            view.imageLayout.isDrawingCacheEnabled = true

                            try {
                                val tvImage: Bitmap = Bitmap.createBitmap(view.imageLayout.drawingCache)
                                val sendIntent = Intent()
                                sendIntent.action = Intent.ACTION_SEND
                                sendIntent.type = "image/PNG"
                                val bitmapPath =
                                    MediaStore.Images.Media.insertImage(
                                        context.contentResolver,
                                        tvImage,
                                        "title",
                                        null
                                    )
                                val bitmapUri: Uri = Uri.parse(bitmapPath)
                                sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
                                context.startActivity(sendIntent);

                            }catch (e:Exception){

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
            FontStyleListAdapter(this@CategeryDetailsActivity, fontNameList, fontFileList)
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
