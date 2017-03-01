package com.chefcast.breakfast.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M1036631 on 2/24/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /** Database version */
    private static final int VERSION_NO=1;
    /** Database Name */
    private static final String DATABASE_NAME="contactsManager";
    /** Table Name*/
    private static final String TABLE_CONTACTS="contacts";

    /** table columns name */

    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO="phone_number";

    public DatabaseHandler(Context context)
    {
        super(context,DATABASE_NAME,null,VERSION_NO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE="CREATE TABLE "+TABLE_CONTACTS+"("+
                                      KEY_ID+ " INTEGER PRIMARY KEY, "+
                                      KEY_NAME+ " TEXT, "+
                                      KEY_PH_NO+ " TEXT)";

        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    void addContacts(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PH_NO,contact.getPhone_number());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

    Contact getContact(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Contact contact=null;

        Cursor cursor=db.query(TABLE_CONTACTS,new String[]{KEY_ID, KEY_NAME,KEY_PH_NO},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null
                        );
        if(cursor!=null)
        {
            cursor.moveToFirst();
            contact=new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2));

            cursor.close();
        }
        db.close();

         return contact;
    }

    void updateContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PH_NO,contact.getPhone_number());

        db.update(TABLE_CONTACTS,values,KEY_ID+"?",new String[]{String.valueOf(contact.getId())});
        db.close();

    }

    void deleteContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
        db.close();

    }
}
