package cz.cvut.fel.bp.vyskojo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cz.cvut.fel.bp.vyskojo1.activity.LessonsActivity;
import cz.cvut.fel.bp.vyskojo1.database.Data;
import cz.cvut.fel.bp.vyskojo1.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = new MyDatabaseHelper(MainActivity.this);
        insertData();

        setContentView(R.layout.activity_main);

    }

    public void onLectionsClicked(View view) {
        Intent intent = new Intent(MainActivity.this, LessonsActivity.class);
        String tag = view.getTag().toString();
        intent.putExtra("tag", tag);
        startActivity(intent);
    }

    public void onShareClicked(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sdílení odkazu");
        i.putExtra(Intent.EXTRA_TEXT, "https://github.com/josefvyskocil/MediaLiteracyEducationApp");
        startActivity(Intent.createChooser(i, "Sdílet odkaz"));
    }

    public void insertData(){
        if (myDB.readAllData().getCount() == 0) {
            Data.insertDataToDB(myDB);
        }
    }

}
