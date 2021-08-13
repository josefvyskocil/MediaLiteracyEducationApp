package cz.cvut.fel.bp.vyskojo1.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cz.cvut.fel.bp.vyskojo1.database.CustomAdapter;
import cz.cvut.fel.bp.vyskojo1.database.MyDatabaseHelper;
import cz.cvut.fel.bp.vyskojo1.R;

public class LessonsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> lesson_id, lesson_difficulty, lesson_type, lesson_question, lesson_image,
            lesson_completed, lesson_right_answer, lesson_title, lesson_answer_1, lesson_answer_2,
            lesson_answer_3, lesson_answer_4, lesson_answer_description;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        String sessionId = getIntent().getStringExtra("view");

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        myDB = new MyDatabaseHelper(LessonsActivity.this);
        lesson_id = new ArrayList<>();
        lesson_difficulty = new ArrayList<>();
        lesson_type = new ArrayList<>();
        lesson_question = new ArrayList<>();
        lesson_image = new ArrayList<>();
        lesson_completed = new ArrayList<>();
        lesson_right_answer = new ArrayList<>();
        lesson_title = new ArrayList<>();
        lesson_answer_1 = new ArrayList<>();
        lesson_answer_2 = new ArrayList<>();
        lesson_answer_3 = new ArrayList<>();
        lesson_answer_4 = new ArrayList<>();
        lesson_answer_description = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(LessonsActivity.this,this, lesson_id,
                lesson_difficulty, lesson_type, lesson_question, lesson_image, lesson_completed,
                lesson_right_answer, lesson_title, lesson_answer_1, lesson_answer_2, lesson_answer_3,
                lesson_answer_4, lesson_answer_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LessonsActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                lesson_id.add(cursor.getString(0));
                lesson_title.add(cursor.getString(7));
                lesson_completed.add(cursor.getString(5));
                //TODO nastavit visibilitu podle cursor.getString(5)
//                if (Integer.parseInt(cursor.getString(5)) == 1)
//                    recyclerView.findViewHolderForAdapterPosition(1).itemView.findViewById(R.id.completed).setVisibility(View.VISIBLE);
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

}

