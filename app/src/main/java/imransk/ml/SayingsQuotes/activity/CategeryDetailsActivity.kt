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
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.adapter.CategeryDetailsAdapter
import imransk.ml.SayingsQuotes.adapter.RecyclerItemClickListenr
import imransk.ml.SayingsQuotes.database.DatabaseAccess
import imransk.ml.SayingsQuotes.model.CategoryDetailsClass
import kotlinx.android.synthetic.main.activity_categery_details.*
import kotlinx.android.synthetic.main.item_details.*
import kotlinx.android.synthetic.main.item_details.view.*
import java.io.FileNotFoundException
import java.io.FileOutputStream

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
        imagelist.add(R.drawable.angry)
        imagelist.add(R.drawable.attitude)
        imagelist.add(R.drawable.awkward_moment)
        imagelist.add(R.drawable.beatiful)
        imagelist.add(R.drawable.bike)
        imagelist.add(R.drawable.brother)
        imagelist.add(R.drawable.emotional)

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
        Toast.makeText(context, "${id}", Toast.LENGTH_SHORT).show();
        
        if (id == 3) { // angry
            var categoryDetailsClass = CategoryDetailsClass(-1, -1, "Get mad, then get over it.", 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(-1, -1, "Anger is an acid that can do more harm to the vessel in which it is stored than to anything on which it is poured.", 0)
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(-1, -1,
                "Holding on to anger is like grasping a hot coal with the intent of throwing it at someone else; you are the one who gets burned.\n" +
                    "\n", 0)
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 5) { // Attitude
            var categoryDetailsClass = CategoryDetailsClass(-1, -1, "A positive attitude causes a chain reaction of positive thoughts, events and outcomes. It is a catalyst and it sparks extraordinary results.- Wade Boggs"
                    , 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(-1, -1, "If you have a positive attitude and constantly strive to give your best effort, eventually you will overcome your immediate problems and find you are ready for greater challenges.- Pat Riley", 0)
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(-1, -1,
                "I don't have a very positive attitude towards rappers. Miriam Margolyes", 0)
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 7) { // awkward moment
            var categoryDetailsClass = CategoryDetailsClass(-1, -1,
                "Move out of your comfort zone. You can only grow if you are willing to feel awkward and uncomfortable when you try something new. -Brian Tracy"

                    , 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(-1, -1, "Getting stopped in the middle of the lingerie section, when you're trying to stock up on a few things, by an older man who wants a selfie is a little bit awkward... but I don't let that get in the way of me trying to do normal things, because that is when I get to interact with people as well. Preferably not amongst the underwear, though."
                    , 0)
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(-1, -1,
                "Music has generally involved a lot of awkward contraptions, a certain amount of heavy lifting.", 0)
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 9) { /* beautiful */
            var categoryDetailsClass = CategoryDetailsClass(-1, -1,
                "A beautiful body perishes, but a work of art dies not."
                    , 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(-1, -1, "The most beautiful things in the world cannot be seen or even touched, they must be felt with the heart.\n"
                    , 0)
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(-1, -1,
                "Nothing can be beautiful which is not true." , 0)
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }

        if (id == 11) { /* Bike */
            var categoryDetailsClass = CategoryDetailsClass(-1, -1,
                "Don’t buy upgrades, ride up grades – Eddy Merckx"
                    , 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(-1, -1,
                "Ride as much or as little, as long or as short as you feel. But ride – Eddy Merckx"
                    , 0)
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(-1, -1,
                "It never gets easier, you just get faster – Greg LeMond" , 0)
            categoryDetailsClassArrayList.add(categoryDetailsClasslast)

        }
        if (id == 13) { /* Brother */
            var categoryDetailsClass = CategoryDetailsClass(-1, -1,
                "My brother is my only best friend. No one can replace him."
                    , 0)
            categoryDetailsClassArrayList.add(0, categoryDetailsClass)

            var categoryDetailsClass5 = CategoryDetailsClass(-1, -1,
                "Sometimes being a brother is even better than being a superhero. – Marc Brown"
                    , 0)
            categoryDetailsClassArrayList.add(5, categoryDetailsClass5)

            var categoryDetailsClasslast = CategoryDetailsClass(-1, -1,
                "Brothers are what best friends can never be.", 0)
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
                            view.imageLayout.background = context.resources.getDrawable(imagelist[rnds])
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

                            val tvImage: Bitmap = Bitmap.createBitmap(view.imageLayout.drawingCache)
                            try {
                                tvImage.compress(
                                    Bitmap.CompressFormat.PNG,
                                    100,
                                    FileOutputStream(
                                        Environment.getExternalStorageDirectory()
                                            .toString() + "/tvimage.png"
                                    )
                                )
                            } catch (e: FileNotFoundException) {
                                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }

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

                        }

                        return
                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                        return
                    }


                })
        )


    }


}
