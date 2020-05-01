package imransk.ml.SayingsQuotes.activity

import android.Manifest
import android.app.Dialog
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
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.chinalwb.are.AREditText
import com.chinalwb.are.styles.toolbar.ARE_ToolbarDefault
import com.chinalwb.are.styles.toolbar.IARE_Toolbar
import com.chinalwb.are.styles.toolitems.*
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.adapter.CategoryAdapter
import imransk.ml.SayingsQuotes.adapter.RecyclerItemClickListenr
import imransk.ml.SayingsQuotes.database.DatabaseAccess
import imransk.ml.SayingsQuotes.model.CategoryClass
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_details.view.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.*
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Quote as ARE_ToolItem_Quote1

class MainActivity : AppCompatActivity() {
    lateinit var databaseAccess: DatabaseAccess;

    var categoryClassList = ArrayList<CategoryClass>()
    lateinit var context: Context

    private lateinit var mToolbar: IARE_Toolbar

    private lateinit var mEditText: AREditText

    var rnds = 9;
    var imagelist = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        initToolbar()

        val custom_font: Typeface = Typeface.createFromAsset(context.assets, "fonts/aa.ttf")


        xView.typeface = custom_font

        Toast.makeText(context, "Double Tap To Change The Background", Toast.LENGTH_SHORT).show();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_DENIED
            ) {
                var st = ArrayList<String>();

                ActivityCompat.requestPermissions(
                    context as MainActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                );
            }
        }

        imagelist = ArrayList<Int>()

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

        databaseAccess = DatabaseAccess.getInstance(this@MainActivity)
        categoryClassList = databaseAccess.category

        categeryListRecyclerID.layoutManager = GridLayoutManager(context, 2)

        var categoryAdapter = CategoryAdapter(context, categoryClassList)
        categeryListRecyclerID.adapter = categoryAdapter

        categeryListRecyclerID.addOnItemTouchListener(
            RecyclerItemClickListenr(
                context,
                categeryListRecyclerID,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                       /* Toast.makeText(
                            context,
                            "${categoryClassList[position]._id}",
                            Toast.LENGTH_SHORT
                        ).show();*/
                        if (categoryClassList[position]._id == -1) {

                            startActivity(Intent(
                                this@MainActivity,BanglaQuotesActivity::class.java)
                                .putExtra("collectionName","liveQuotes"))

                        } else if (categoryClassList[position]._id == -2) {
                            startActivity(Intent(
                                this@MainActivity,BanglaQuotesActivity::class.java)
                                .putExtra("collectionName","banglaQuotes"))

                        } else {
                            startActivity(
                                Intent(
                                    context,
                                    CategeryDetailsActivity::class.java
                                ).putExtra("id", categoryClassList[position]._id)
                            )
                        }


                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                        return
                    }

                })
        )

        bottom_navigation_view_linear.setNavigationChangeListener { _, position ->
            if (position == 0) {
                categeryListRecyclerID.visibility = View.GONE
                firstLayout.visibility = View.VISIBLE

            }
            if (position == 1) {
                categeryListRecyclerID.visibility = View.VISIBLE
                firstLayout.visibility = View.GONE

            }
            //navigation changed, do something here
        }

        val arEditor: AREditText = findViewById<AREditText>(R.id.arEditText)

        copyBtn.setOnClickListener {

            var myClipboard: ClipboardManager? = null
            var myClip: ClipData? = null
            myClipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?;
            myClip = ClipData.newPlainText("text", xView.text);
            myClipboard?.setPrimaryClip(myClip);
            Toast.makeText(context, "Copied..", Toast.LENGTH_SHORT).show();

        }

        shareBtn.setOnClickListener {


            imageLayout.background = context.resources.getDrawable(imagelist[rnds])

            imageLayout.isDrawingCacheEnabled = true

            /*
            xView.background = context.resources.getDrawable(imagelist[rnds])

            xView.isDrawingCacheEnabled = true*/

            val tvImage: Bitmap = Bitmap.createBitmap(imageLayout.drawingCache)
            try {
                tvImage.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    FileOutputStream(
                        Environment.getExternalStorageDirectory().toString() + "/tvimage.png"
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
                MediaStore.Images.Media.insertImage(context.contentResolver, tvImage, "title", null)

            val bitmapUri: Uri = Uri.parse(bitmapPath)

            sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)

            context.startActivity(sendIntent);

        }


        colorPickerBtn.setOnClickListener {

            val colorPickerDialog: ColorPickerDialog =
                ColorPickerDialog.createColorPickerDialog(this)

            colorPickerDialog.setOnColorPickedListener { color, hexVal ->
                //Your code here
                xView.setTextColor(Color.parseColor(hexVal))
                colorPickerDialog.setColorComponentsTextColor(color)

            }
            colorPickerDialog.show();

        }

        xView.setOnClickListener {


            rnds = (0 until imagelist.size).random()
            imageLayout.background = context.resources.getDrawable(imagelist[rnds])
        }

        arEditor.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    xView.text = Html.fromHtml(arEditor.html.toString())

                } else {
                    xView.text = Html.fromHtml(arEditor.html.toString())

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }

        })


    }


    private fun initToolbar() {
        mToolbar=areToolbar
//        mToolbar = findViewById(R.id.areToolbar) as ARE_ToolbarDefault
        val bold: IARE_ToolItem = ARE_ToolItem_Bold()
        val italic: IARE_ToolItem = ARE_ToolItem_Italic()
        val underline: IARE_ToolItem = ARE_ToolItem_Underline()
        val strikethrough: IARE_ToolItem = ARE_ToolItem_Strikethrough()
        val quote: IARE_ToolItem = ARE_ToolItem_Quote1()
        val listNumber: IARE_ToolItem = ARE_ToolItem_ListNumber()
        val listBullet: IARE_ToolItem = ARE_ToolItem_ListBullet()
        val hr: IARE_ToolItem = ARE_ToolItem_Hr()
        val link: IARE_ToolItem = ARE_ToolItem_Link()
        val subscript: IARE_ToolItem = ARE_ToolItem_Subscript()
        val superscript: IARE_ToolItem = ARE_ToolItem_Superscript()
        val left: IARE_ToolItem = ARE_ToolItem_AlignmentLeft()
        val center: IARE_ToolItem = ARE_ToolItem_AlignmentCenter()
        val right: IARE_ToolItem = ARE_ToolItem_AlignmentRight()
        val image: IARE_ToolItem = ARE_ToolItem_Image()
        val video: IARE_ToolItem = ARE_ToolItem_Video()
        val at: IARE_ToolItem = ARE_ToolItem_At()
        mToolbar.addToolbarItem(bold)
        mToolbar.addToolbarItem(italic)
        mToolbar.addToolbarItem(underline)
        mToolbar.addToolbarItem(strikethrough)
        mToolbar.addToolbarItem(quote)
        mToolbar.addToolbarItem(listNumber)
        mToolbar.addToolbarItem(listBullet)
        mToolbar.addToolbarItem(hr)
        mToolbar.addToolbarItem(subscript)
        mToolbar.addToolbarItem(superscript)
        mToolbar.addToolbarItem(left)
        mToolbar.addToolbarItem(center)
        mToolbar.addToolbarItem(right)
        mToolbar.addToolbarItem(at)
        mEditText = findViewById(R.id.arEditText)
        mEditText.setToolbar(mToolbar)


        at.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        right.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        center.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        left.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        superscript.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        subscript.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        hr.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        listBullet.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        bold.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        italic.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        underline.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }

        strikethrough.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        quote.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }
        listNumber.setToolItemUpdater {
            xView.text=Html.fromHtml(mEditText.html.toString())
        }

//        setHtml()
//        initToolbarArrow()
    }



    private fun setHtml() {
        val html =
            """
            <p style="text-align: center;"><strong>New Feature in 0.1.2</strong></p>
            <p style="text-align: center;">&nbsp;</p>
            <p style="text-align: left;"><span style="color: #3366ff;">In this release, you have a new usage with ARE.</span></p>
            <p style="text-align: left;">&nbsp;</p>
            <p style="text-align: left;"><span style="color: #3366ff;">AREditText + ARE_Toolbar, you are now able to control the position of the input area and where to put the toolbar at and, what ToolItems you'd like to have in the toolbar. </span></p>
            <p style="text-align: left;">&nbsp;</p>
            <p style="text-align: left;"><span style="color: #3366ff;">You can not only define the Toolbar (and it's style), you can also add your own ARE_ToolItem with your style into ARE.</span></p>
            <p style="text-align: left;">&nbsp;</p>
            <p style="text-align: left;"><span style="color: #ff00ff;"><em><strong>Why not give it a try now?</strong></em></span></p>
            """.trimIndent()
        mEditText.fromHtml(html)
    }







    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        mToolbar.onActivityResult(requestCode, resultCode, data)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {

                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.type = "text/plain"

                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=${this.packageName}"
                )

                context.startActivity(sendIntent);




                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            show_Alert_to_Exit_The_App()
        } else {
            supportFragmentManager.popBackStack()
        }

    }

    private fun show_Alert_to_Exit_The_App() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_alert_exit_or_rate)

        val no_btn = dialog.findViewById(R.id.no_button_ID) as Button
        val yes_btn = dialog.findViewById(R.id.yes_btn_ID) as Button
        val rate_btn = dialog.findViewById(R.id.rate_btn_ID) as Button
        dialog.show()
        no_btn.setOnClickListener {
            dialog.dismiss()
        }
        yes_btn.setOnClickListener {
            dialog.dismiss()
            finish()

        }
        rate_btn.setOnClickListener {
            dialog.dismiss()
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=${this.packageName}")
                )
            )
            Log.e("Tag - ", "package name = " + this.packageName)

        }
    }

}
