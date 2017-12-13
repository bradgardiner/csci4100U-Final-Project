package ca.uoit.csci4100u.mapsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by bradg on 2017-12-13.
 */

public class UserHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    static final String UserTable = "Users";

    static final String CREATE_STATEMENT = "CREATE TABLE usersTable(\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tusername TEXT NOT NULL,\n" +
            "\temail TEXT NOT NULL,\n" +
            "\tpassword TEXT NOT NULL \n" +
            "\trunner BIT \n" +
            ");";


    static final String DROP_STATEMENT = "DROP TABLE contacts";


    public UserHelper(Context context){super(context, "Items", null,DATABASE_VERSION);};


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);

    }
    public User createUser(String username,
                                 String email,
                                 String password,
                                 boolean runner) {
        User user = new User(username, email, password, runner);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();

        newValues.put("username", username);
        


    }
}
