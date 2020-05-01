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
import android.os.*
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.adapter.BanglaQuotesDetailsAdapter
import imransk.ml.SayingsQuotes.adapter.RecyclerItemClickListenr
import imransk.ml.SayingsQuotes.model.BanglaQuotes
import kotlinx.android.synthetic.main.activity_bangla_quotes.*
import kotlinx.android.synthetic.main.item_details.*
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

                            val tvImage: Bitmap = Bitmap.createBitmap( view.imageLayout.drawingCache)
                            try {
                                tvImage.compress(
                                    Bitmap.CompressFormat.PNG,
                                    100,
                                    FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/tvimage.png")
                                )
                            } catch (e: FileNotFoundException) {
                                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }


                            val sendIntent = Intent()
                            sendIntent.action = Intent.ACTION_SEND
                            sendIntent.type = "image/PNG"
                            val bitmapPath =
                                MediaStore.Images.Media.insertImage(context.contentResolver, tvImage, "title", null)

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