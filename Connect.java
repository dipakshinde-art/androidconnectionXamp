package com.example.toure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.HashMap;

public class Connect extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyFirst.db";
    private static String student = "student";
    public static String CREATE_NOTE = "CREATE TABLE " + student + "(INT ID PRIMARY KEY, NAME TEXT,EMAIL TEXT,PHONE TEXT,PASSWORD TEXT);";
    public static String SELECT_NOTES = "SELECT * FROM " + student;
    public static String EMPTY_NOTES = "DELETE FROM  " + student;

    public Connect(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CREATE_NOTE ");
        onCreate(db);
    }

    public boolean insertIntoStudent(int s_id, String s_name, String s_email, int s_phone, String s_password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("s_name", s_name);
        contentValues.put("s_email", s_email);
        contentValues.put("s_phone", s_phone);
        contentValues.put("s_password", s_password);
        SQLiteDatabase db = this.getWritableDatabase();

        long STUDENT = db.insert(student, null, contentValues);
        if (STUDENT > 0) {
            return true;

        } else {
            return false;
        }

    } //end insert

    public boolean updateIntoStudent(int s_id, String s_name, String s_email, int s_phone, String s_password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("s_id", s_id);
        contentValues.put("s_name", s_name);
        contentValues.put("s_email", s_email);
        contentValues.put("s_phone", s_phone);
        contentValues.put("s_password", s_password);
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "s_id = ?";
        String Args[] = new String[]{String.valueOf(s_id)};

        long STUDENT = db.update(student, contentValues, whereClause, Args);
        if (STUDENT > 0) {
            return true;
        } else {
            return false;
        }
    }//end update

    public boolean DeleteIntoStudent(int s_id) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = "s_id = ?";
        String whereArgs[] = new String[]{String.valueOf(s_id)};
        long STUDENT = database.delete(student, whereClause, whereArgs);
        if (STUDENT > 0) {
            return true;
        } else {
            return false;
        }
    }//end delete

    public ArrayList<ConnectionModel> getAllconnection() {

        Log.e("Connect", "inside get all notes");
        ArrayList<ConnectionModel> connectionModels = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_NOTES, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ConnectionModel note = new ConnectionModel();
                note.id = cursor.getInt(cursor.getColumnIndex("s_id"));
                Log.e("Connect", " " + note.id);
                note.name = cursor.getString(cursor.getColumnIndex("s_name"));
                Log.e("onnect", " " + note.name);
                note.email = cursor.getString(cursor.getColumnIndex("s_email"));
                connectionModels.add(note);
                note.phone = cursor.getInt(cursor.getColumnIndex("s_phone"));
                connectionModels.add(note);
                note.password = cursor.getString(cursor.getColumnIndex("s_password"));
                connectionModels.add(note);
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();
        Log.e("connect"," " +connectionModels);
        return connectionModels;
    }
}


//   // public Connect(Context xtc) {
//        Context.xtc = xtc;
//    }


//    String myclass = "com.mysql.jdbc.Driver";
//    String urll = "jdbc:mysql:// 192.168.0.103:3306/interview";
//    String username = "dipak";
//    String password = "Root1997@";


//    @SuppressLint("NewApi")
//    public Connection CONN() {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                .permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        Connection conn = null;
//        String ConnURL = null;
//        try {
//            Class.forName(myclass);
//            conn = DriverManager.getConnection(url, username, password);
//            conn = DriverManager.getConnection(ConnURL);
//        } catch (SQLException e) {
//            Log.e("Error", e.getMessage());
//        } catch (ClassNotFoundException e) {
//            Log.e("Error", e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//new work

//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        String method = strings[0];
//        if (method.equals("register")) {
//            String Sname = strings[1];
//            String Smail = strings[2];
//            String Smob = strings[3];
//            String Spass = strings[4];
//
//            try {
//                URL url = new URL(urll);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("post");
//                httpURLConnection.setDoOutput(true);
//                OutputStream os = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//                String data = URLEncoder.encode("Name" + "UTF-8") + "=" + URLEncoder.encode(Sname + "UTF-8") + "&" +
//                        URLEncoder.encode("Email" + "UTF-8") + "=" + URLEncoder.encode(Smail + "UTF-8") + "&" +
//                        URLEncoder.encode("MObile" + "UTF-8") + "=" + URLEncoder.encode(Smob + "UTF-8") + "&" +
//                        URLEncoder.encode("Password" + "UTF-8") + "=" + URLEncoder.encode(Spass + "UTF-8");
//                bufferedWriter.write(data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                os.close();
//                InputStream is = httpURLConnection.getInputStream();
//                is.close();
//                return "registraion succesfull";
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void onProgressUpdate(String... values) {
//        super.onProgressUpdate(values);
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//
//
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//    }

