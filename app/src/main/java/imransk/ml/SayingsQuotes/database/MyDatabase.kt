package imransk.ml.SayingsQuotes.database

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class MyDatabase(context: Context?) : SQLiteAssetHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "bestquotesandstatus.db"
        private const val DATABASE_VERSION = 1
    }
}