package imransk.ml.SayingsQuotes.activity

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.view.View.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.permission.StaticClass
import imransk.ml.myquotesjava.adapter.ColorListAdapter
import imransk.ml.myquotesjava.adapter.FontStyleListAdapter
import imransk.ml.myquotesjava.adapter.ImageAdapter
import imransk.ml.myquotesjava.adapter.RecyclerItemClickListenr
import kotlinx.android.synthetic.main.activity_write_own_quotes.*
import kotlinx.android.synthetic.main.font_style_alert.*
import kotlinx.android.synthetic.main.text_effect_alert.*
import kotlinx.android.synthetic.main.text_style_alert.*
import java.io.*
import java.text.DecimalFormat


class WriteOwnQuotes : AppCompatActivity() {
    lateinit var context: Context

    lateinit var imageAdapter: ImageAdapter
    lateinit var imagelist: ArrayList<Int>
     var xDelta:Int = 0
    var yDelta : Int = 0


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_own_quotes)
        context = this@WriteOwnQuotes
        imagelist = ArrayList()

        toolbarWriteOwnQuotes.setNavigationOnClickListener {
            finish()
        }

        banckgroundImageRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


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

        imageAdapter = ImageAdapter(context as WriteOwnQuotes, imagelist)
        banckgroundImageRecyclerView.adapter = imageAdapter

        banckgroundImageRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListenr(this, banckgroundImageRecyclerView,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        imageLayout.background = context.resources.getDrawable(imagelist[position])

                        return
                    }

                    override fun onItemLongClick(view: View?, position: Int) {


                        return
                    }

                })
        )

        //delete background
        deleteIconIV.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Are You Sure To Remove Background Image ?")
                .setConfirmClickListener { sDialog ->
                    sDialog.dismissWithAnimation()
                    imageLayout.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
                }
                .setCancelText("NO")
                .setCancelClickListener { sweetAlertDialog ->
                    sweetAlertDialog.dismissWithAnimation()
                }
                .show()
        }
        saveIcoTV.setOnClickListener {
            var st = textQuotesTV.text

            if (st.isEmpty() or (st == "Write Here Again") or (st == "Write Here")){

                Toast.makeText(context, "Write Some Things New", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

            imageLayout.isDrawingCacheEnabled = true

            val tvImage: Bitmap = Bitmap.createBitmap(imageLayout.drawingCache)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //check the permission
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ) {

                    SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Alert !")
                        .setContentText("Do you want to save ?")
                        .setConfirmText("YES")
                        .setConfirmClickListener { sDialog ->
                            sDialog.dismissWithAnimation()
                            saveToInternalStorage(tvImage)

                        }
                        .setCancelText("NO")
                        .setCancelClickListener { sweetAlertDialog ->
                            sweetAlertDialog.dismissWithAnimation()
                        }
                        .show()
                } else {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), StaticClass.STROAGE_CODE
                    )
                }
            } /*else {
                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert !")
                    .setContentText("Do you want to save ?")
                    .setConfirmText("YES")
                    .setConfirmClickListener { sDialog ->
                        sDialog.dismissWithAnimation()
                        saveToInternalStorage(tvImage)

                    }
                    .setCancelText("NO")
                    .setCancelClickListener { sweetAlertDialog ->
                        sweetAlertDialog.dismissWithAnimation()
                    }
                    .show()
            }*/

        }
        writeIconIV.setOnClickListener {

            showEnterTextDialog()
        }
        textQuotesTV.setOnClickListener {
            showEnterTextDialog();
        }
        /*//move text view
        textQuotesTV.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                // Offsets are for centering the TextView on the touch location
                v.x = event.rawX - v.width / 2.0f
                v.y = event.rawY - v.height / 2.0f
            }
            true
        })
        */
        textQuotesTV.setOnTouchListener(onTouchListener())



        shareIcoTV.setOnClickListener {

//set image background

            var imageLayout = findViewById<LinearLayout>(R.id.imageLayout)
            var st = textQuotesTV.text

            if (st.isEmpty() or (st == "Write Here Again") or (st == "Write Here")){
                Toast.makeText(context, "Write Some Quotes", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            textQuotesTV.text = st
            imageLayout.isDrawingCacheEnabled = true

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
        pictureLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, StaticClass.CHOOSEIMAGE_CODE)

        }
        textStyleLayout.setOnClickListener {

            showtextStyleBottombar();

        }
        fontLayout.setOnClickListener {

            showFontStyleBottomAlert();


        }
        effectsLayout.setOnClickListener {

            textEffectBackgroundAlert()

        }

    }


    private fun onTouchListener(): OnTouchListener? {
        return OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams =
                        view.layoutParams as LinearLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }
                MotionEvent.ACTION_UP ->{/*Toast.makeText(
                    this@WriteOwnQuotes,
                    "thanks for new location!", Toast.LENGTH_SHORT
                )
                    .show()*/}
                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = view
                        .layoutParams as LinearLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }
            imageLayout.invalidate()
            true
        }
    }

    private fun textEffectBackgroundAlert() {

        val bottomalertDialog = BottomSheetDialog(context);
        bottomalertDialog.setContentView(R.layout.text_effect_alert)


        bottomalertDialog.seekbarTextbackground.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val startColor = Color.parseColor("#000000")
                val endColor = Color.parseColor("#FFFFFF")

                val start_red = Color.red(startColor)
                val start_green = Color.green(startColor)
                val start_blue = Color.blue(startColor)
                val end_red = Color.red(endColor)
                val end_green = Color.green(endColor)
                val end_blue = Color.blue(endColor)

                val progress_red = start_red + (end_red - start_red) * progress / 100
                val progress_green = start_green + (end_green - start_green) * progress / 100
                val progress_blue = start_blue + (end_blue - start_blue) * progress / 100

                val progress_color = Color.rgb(progress_red, progress_green, progress_blue)

                if (progress < 5) {
                    textQuotesTV.setBackgroundColor(0x00000000)

                } else {

                    textQuotesTV.setBackgroundColor(progress_color)
                }


                return
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                return
            }


            override fun onStopTrackingTouch(seekBar: SeekBar?) {

                return
            }

        })

        bottomalertDialog.seekbarTextRotation.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                /* val shape = GradientDrawable()
                 shape.setCornerRadius(progress.toFloat())
                 textQuotesTV.background=shape*/

                textQuotesTV.rotation = (progress * 2).toFloat()

                return
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

                return
            }

        })

        bottomalertDialog.seekbarimageOpacity.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (progress > 0) {
                    Log.e("TAG", "${(progress.toFloat() / 100)}: ");
                    var pro = DecimalFormat("##.#").format(progress.toFloat() / 100)

                    imageLayout.alpha = pro.toFloat()
//                    imageLayout.alpha=0.4f
                }


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }

        })



        bottomalertDialog.show()


    }

    private fun showFontStyleBottomAlert() {
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
            FontStyleListAdapter(this@WriteOwnQuotes, fontNameList, fontFileList)
        bottomalertDialog.fontStyleRecyclerView.adapter = fontStyleListAdapter

        bottomalertDialog.fontStyleRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListenr(context, bottomalertDialog.fontStyleRecyclerView,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        val tf = Typeface.createFromAsset(
                            context.assets,
                            fontFileList[position]
                        )
                        textQuotesTV.typeface = tf


                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                        return
                    }

                })
        )

        bottomalertDialog.show()


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun showtextStyleBottombar() {

        var bold = false
        var italic = false
        var underLine = false
        var colorListBoolean = false

        val bottomalertDialog = BottomSheetDialog(context);
        bottomalertDialog.setContentView(R.layout.text_style_alert)


        bottomalertDialog.seekbarTextSize.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {

                textQuotesTV.textSize = progress.toFloat()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }
        })
        bottomalertDialog.seekbarTextOpacity.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                var myAlpha = progress / (seekBar.getMax());
//                water_text.setAlpha(myAlpha)
                textQuotesTV.alpha = myAlpha.toFloat()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }
        })
        bottomalertDialog.seekbarLineSpacing.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {

                textQuotesTV.setLineSpacing(progress.toFloat(), 1f)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }
        })

        bottomalertDialog.boldTV.setOnClickListener { view ->
            if (bold) {
                underLine = false
                bold = false
                italic = false
                bottomalertDialog.boldTV.setTextColor(context.resources.getColor(R.color.white))
                bottomalertDialog.italicTV.setTextColor(context.resources.getColor(R.color.white))
                bottomalertDialog.underLineTV.setTextColor(context.resources.getColor(R.color.white))
                textQuotesTV.typeface = Typeface.DEFAULT;


            } else {
                bold = true
                bottomalertDialog.boldTV.setTextColor(context.resources.getColor(R.color.colorPrimary))
                textQuotesTV.setTypeface(textQuotesTV.typeface, Typeface.BOLD);

            }

        }
        bottomalertDialog.italicTV.setOnClickListener { view ->
            if (italic) {
                underLine = false
                bold = false
                italic = false
                bottomalertDialog.boldTV.setTextColor(context.resources.getColor(R.color.white))
                bottomalertDialog.italicTV.setTextColor(context.resources.getColor(R.color.white))
                bottomalertDialog.underLineTV.setTextColor(context.resources.getColor(R.color.white))
                textQuotesTV.typeface = Typeface.DEFAULT;


            } else {
                italic = true
                bottomalertDialog.italicTV.setTextColor(context.resources.getColor(R.color.colorPrimary))
                textQuotesTV.setTypeface(textQuotesTV.typeface, Typeface.ITALIC);

            }

        }
        bottomalertDialog.underLineTV.setOnClickListener { view ->
            if (underLine) {
                underLine = false
                bold = false
                italic = false
                bottomalertDialog.boldTV.setTextColor(context.resources.getColor(R.color.white))
                bottomalertDialog.italicTV.setTextColor(context.resources.getColor(R.color.white))
                bottomalertDialog.underLineTV.setTextColor(context.resources.getColor(R.color.white))

                textQuotesTV.paintFlags = View.INVISIBLE;


            } else {
                underLine = true
                bottomalertDialog.underLineTV.setTextColor(context.resources.getColor(R.color.colorPrimary))

                textQuotesTV.paintFlags = textQuotesTV.paintFlags or Paint.UNDERLINE_TEXT_FLAG


            }


        }


        bottomalertDialog.alignMentLeft.setOnClickListener {
            textQuotesTV.gravity = Gravity.LEFT
//            bottomalertDialog.alignMentLeft.background=context.getDrawable(R.drawable.ic_format_align_left_black_dark_24dp)
        }
        bottomalertDialog.alignMentcenter.setOnClickListener {


            textQuotesTV.gravity = Gravity.CENTER
//            bottomalertDialog.alignMentcenter.background=context.getDrawable(R.drawable.ic_format_align_center_black_dark_24dp)


        }
        bottomalertDialog.alignMentRight.setOnClickListener {
            textQuotesTV.gravity = Gravity.RIGHT
//            bottomalertDialog.alignMentRight.background=context.getDrawable(R.drawable.ic_format_align_right_black_dark_24dp)


        }

        bottomalertDialog.textcolorIV.setOnClickListener {
            if (colorListBoolean) {
                colorListBoolean = false
                bottomalertDialog.colorRecyclerView.visibility = GONE

            } else {
                colorListBoolean = true
                bottomalertDialog.colorRecyclerView.visibility = VISIBLE

                callColorList(context, textQuotesTV, bottomalertDialog)
            }


        }



        bottomalertDialog.show()


    }

    private fun callColorList(
        context: Context,
        textQuotesTV: TextView?,
        bottomalertDialog: BottomSheetDialog
    ) {


        var colorList = ArrayList<String>()
        colorList.add("#FFFFFF")
        colorList.add("#000000")
        colorList.add("#FF0000")
        colorList.add("#00FFFF")
        colorList.add("#0000FF")
        colorList.add("#0000A0")
        colorList.add("#ADD8E6")
        colorList.add("#800080")
        colorList.add("#FFFF00")
        colorList.add("#00FF00")
        colorList.add("#FF00FF")
        colorList.add("#808000")
        colorList.add("#008000")
        colorList.add("#800000")
        colorList.add("#A52A2A")
        colorList.add("#FFA500")
        colorList.add("#808080")
        colorList.add("#C0C0C0")

        bottomalertDialog.colorRecyclerView.visibility = VISIBLE

        var colorListAdapter = ColorListAdapter(this@WriteOwnQuotes, colorList)
        bottomalertDialog.colorRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bottomalertDialog.colorRecyclerView.adapter = colorListAdapter


        bottomalertDialog.colorRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListenr(context, bottomalertDialog.colorRecyclerView,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        textQuotesTV?.setTextColor(Color.parseColor(colorList[position]))


                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                        return
                    }

                })
        )


    }

    private fun showEnterTextDialog() {

        val dialog = Dialog(context, R.style.my_dialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.enter_text_layout)

        val edittextid = dialog.findViewById(R.id.editTextID) as EditText
        val okButtonAlert = dialog.findViewById(R.id.okButtonAlert) as Button

        edittextid.setText(textQuotesTV.text.toString())


        okButtonAlert.setOnClickListener {
            dialog.dismiss()
            textQuotesTV.text = edittextid.text.toString().trim()
        }
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        dialog.show()
        edittextid.requestFocus()
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap) {

        try {

            val path = Environment.getExternalStorageDirectory().toString()
            var fOut: OutputStream? = null
            val counter = 0
            val file = File(
                path,
                "FitnessGirl$counter.jpg"
            ) // the File to save , append increasing numeric counter to prevent files from getting overwritten.

            fOut = FileOutputStream(file)



            bitmapImage.compress(
                Bitmap.CompressFormat.JPEG,
                85,
                fOut
            ) // saving the Bitmap to a file compressed as a JPEG with 85% compression rate

            fOut.flush() // Not really required

            fOut.close() // do not forget to close the stream


            MediaStore.Images.Media.insertImage(
                contentResolver,
                file.absolutePath,
                file.name,
                file.name
            )

            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            textQuotesTV.text = "Write Here Again"
        } catch (e: IOException) {
            e.message
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == StaticClass.CHOOSEIMAGE_CODE && resultCode == RESULT_OK) {
            try {
                var imgUri = data?.getData();
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imgUri)


                val background = BitmapDrawable(bitmap)
                imageLayout.setBackgroundDrawable(background);


            } catch (e: Exception) {

            }
        }
    }

}
