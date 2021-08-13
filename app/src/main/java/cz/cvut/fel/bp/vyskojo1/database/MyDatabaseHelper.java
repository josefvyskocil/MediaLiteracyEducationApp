package cz.cvut.fel.bp.vyskojo1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MediaLiteracy.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Lesson";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DIFFICULTY = "difficulty";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_COMPLETED = "completed";
    private static final String COLUMN_RIGHT_ANSWER = "right_answer";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ANSWER_1 = "answer_1";
    private static final String COLUMN_ANSWER_2 = "answer_2";
    private static final String COLUMN_ANSWER_3 = "answer_3";
    private static final String COLUMN_ANSWER_4 = "answer_4";
    private static final String COLUMN_ANSWER_DESCRIPTION = "answer_description";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DIFFICULTY + " INTEGER, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_COMPLETED + " INTEGER, " +
                COLUMN_RIGHT_ANSWER + " INTEGER, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_ANSWER_1 + " TEXT, " +
                COLUMN_ANSWER_2 + " TEXT, " +
                COLUMN_ANSWER_3 + " TEXT, " +
                COLUMN_ANSWER_4 + " TEXT, " +
                COLUMN_ANSWER_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addLesson(Integer difficulty, String type, String question, String image, Integer completed, Integer right_answer, String title, String answer_1, String answer_2, String answer_3, String answer_4, String answer_description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DIFFICULTY, difficulty);
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_QUESTION, question);
        cv.put(COLUMN_IMAGE, image);
        cv.put(COLUMN_COMPLETED, completed);
        cv.put(COLUMN_RIGHT_ANSWER, right_answer);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_ANSWER_1, answer_1);
        cv.put(COLUMN_ANSWER_2, answer_2);
        cv.put(COLUMN_ANSWER_3, answer_3);
        cv.put(COLUMN_ANSWER_4, answer_4);
        cv.put(COLUMN_ANSWER_DESCRIPTION, answer_description);
        db.insert(TABLE_NAME,null, cv);
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateLesson(String row_id, Integer completed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COMPLETED, completed);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
    }

    public Cursor getLessonById(String id) {
        String query = "SELECT * FROM " + TABLE_NAME+ " where " + COLUMN_ID + "=" + id + "";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

