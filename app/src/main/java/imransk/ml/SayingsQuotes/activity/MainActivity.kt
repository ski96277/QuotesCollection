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
import com.chinalwb.are.styles.toolbar.IARE_Toolbar
import com.chinalwb.are.styles.toolitems.*
import imransk.ml.SayingsQuotes.R
import imransk.ml.SayingsQuotes.adapter.CategoryAdapter
import imransk.ml.SayingsQuotes.database.DatabaseAccess
import imransk.ml.SayingsQuotes.model.CategoryClass
import imransk.ml.myquotesjava.adapter.RecyclerItemClickListenr
import kotlinx.android.synthetic.main.activity_main.*
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity


        val custom_font: Typeface = Typeface.createFromAsset(context.assets, "fonts/aa.ttf")




        fabIcon.setOnClickListener {
            startActivity(Intent(context,WriteOwnQuotes::class.java))
        }

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
