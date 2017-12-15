package ca.uoit.csci4100u.mapsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradg on 2017-12-13.
 */

public class UserHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 3;

    static final String TABLE = "Users";

    static final String CREATE_STATEMENT = "CREATE TABLE Users(\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tusername text not null,\n" +
            "\temail text not null,\n" +
            "\tpassword text not null, \n" +
            "\trunner text not null\n" +
            ");";


    static final String DROP_STATEMENT = "DROP TABLE Users";


    public UserHelper(Context context){super(context, "Users", null,DATABASE_VERSION);};


    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.print("HENLO?!?!?");
        Log.i("Creating Table", "****************************");
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
                                 String runner) {
        User user = new User(username, email, password, runner);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();

        newValues.put("username", username);
        newValues.put("email", email);
        newValues.put("password", password);
        newValues.put("runner", runner);

        long id = db.insert(TABLE, null, newValues);

        user.setId(id);

        return user;
    }

    public User getUser(long id){
        User user = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = new String[] {"id", "username", "email", "password", "runner"};
        String where = "username = ?";
        String[] whereArgs = new String[] {"" + id};

        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "","");

        if(cursor.getCount() >= 1){
            cursor.moveToFirst();

            String username = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String runner = cursor.getString(4);

            user = new User(username, email, password, runner);

            user.setId(id);
        }

        return user;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[]columns  = new String[] {"_id", "username", "email", "password", "runner"};
        String where = "";
        String[] whereArgs = new String[] {};
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "username");

        cursor.moveToFirst();

        do{
            if(!cursor.isAfterLast()){
                long id = cursor.getLong(0);
                String username = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                String runner = cursor.getString(4);

                User user = new User(username, email, password, runner);

                user.setId(id);

                users.add(user);
            }

            cursor.moveToNext();
        }while (!cursor.isAfterLast());

        return users;
    }

    public boolean updateUser (User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("runner", user.isRunner());

        int numRows = db.update(TABLE, values, "_id = ?", new String[] {"" + user.getId() });

        return (numRows == 1);
    }

    public boolean deleteUser(long id){
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "_id = ?", new String[] {"" + id});
        return (numRows ==1);
    }


}
