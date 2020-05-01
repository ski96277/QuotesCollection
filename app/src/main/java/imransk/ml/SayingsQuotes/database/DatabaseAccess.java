package imransk.ml.SayingsQuotes.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import imransk.ml.SayingsQuotes.model.CategoryClass;
import imransk.ml.SayingsQuotes.model.CategoryDetailsClass;

public class DatabaseAccess {

    public static DatabaseAccess instance;
    Cursor c = null;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;

    public DatabaseAccess(Context context) {
        this.openHelper = new MyDatabase(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public ArrayList<CategoryClass> getCategory() {

        ArrayList<CategoryClass> categoryClassList = new ArrayList<>();

        this.open();
        c = (Cursor) db.rawQuery("select * from category", new String[]{});


        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (c.moveToNext()) {

            Log.e("TAG", "getCategory: " + c.getString(1));

            int id = c.getInt(0);
            String name = c.getString(1);
            int status = c.getInt(2);

            CategoryClass categoryClass = new CategoryClass(id, status, name);
            if (i % 2 == 0) {
                if (categoryClass.getName().equalsIgnoreCase("hindi")) {

                } else {

                    categoryClassList.add(categoryClass);
                }

            }
            i++;
        }
        this.close();

        CategoryClass categoryClass2 = new CategoryClass(-1, -0, "Live Quotes");
        categoryClassList.add(0, categoryClass2);
        CategoryClass categoryClass3 = new CategoryClass(-2, -1, "Bangla");
        categoryClassList.add(1, categoryClass3);
        return categoryClassList;
    }

    public ArrayList<CategoryDetailsClass> getDetailsCategory(int _id) {

        ArrayList<CategoryDetailsClass> categoryDetailsClassArrayList = new ArrayList<>();

        this.open();
        c = (Cursor) db.rawQuery("select * from quotes where category_id =" + _id, new String[]{});


        StringBuffer stringBuffer = new StringBuffer();

        while (c.moveToNext()) {

            Log.e("TAG", "getCategory: " + c.getString(1));

            int id = c.getInt(0);
            int category_id = c.getInt(1);
            String quote = c.getString(2);
            int liked = c.getInt(3);
            CategoryDetailsClass categoryDetails = new CategoryDetailsClass(id, category_id, quote, liked);
            categoryDetailsClassArrayList.add(categoryDetails);
        }
        this.close();
        return categoryDetailsClassArrayList;
    }
}