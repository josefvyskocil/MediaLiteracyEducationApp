package cz.cvut.fel.bp.vyskojo1.activity;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cz.cvut.fel.bp.vyskojo1.R;
import cz.cvut.fel.bp.vyskojo1.database.MyDatabaseHelper;

public class LessonActivity extends AppCompatActivity {

    TextView lesson_title, question;
    Button check;
    RadioButton answer_1, answer_2, answer_3, answer_4;
    ImageView image;

    String id, title, question_text, image_name, answer_1_text, answer_2_text, answer_3_text, answer_4_text, right_answer;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        lesson_title = findViewById(R.id.lesson_title);
        question = findViewById(R.id.question);
        check = findViewById(R.id.check);
        answer_1 = findViewById(R.id.answer_1);
        answer_2 = findViewById(R.id.answer_2);
        answer_3 = findViewById(R.id.answer_3);
        answer_4 = findViewById(R.id.answer_4);
        image = findViewById(R.id.image);

        myDB = new MyDatabaseHelper(LessonActivity.this);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        check.setOnClickListener(view -> {
            //And only then we call this
            MyDatabaseHelper myDB = new MyDatabaseHelper(LessonActivity.this);
            myDB.updateLesson(id, 1);
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){
            Cursor lesson = myDB.getLessonById(getIntent().getStringExtra("id"));
            //Getting Data from Intent
            lesson.moveToNext();

            id = lesson.getString(0);
            title = lesson.getString(7);
            question_text = lesson.getString(3);
            image_name = lesson.getString(4);
            answer_1_text = lesson.getString(8);
            answer_2_text = lesson.getString(9);
            answer_3_text = lesson.getString(10);
            answer_4_text = lesson.getString(11);
            right_answer = lesson.getString(6);

            Resources resources = getResources();
            final int resourceId = resources.getIdentifier(image_name,
                    "drawable", getPackageName());

            //Setting Intent Data
            image.setImageResource(resourceId);
            lesson_title.setText(title);
            question.setText(question_text);
            answer_1.setText(answer_1_text);
            answer_2.setText(answer_2_text);
            answer_3.setText(answer_3_text);
            answer_4.setText(answer_4_text);
            if (answer_4_text.equals(""))
                answer_4.setVisibility(View.INVISIBLE);

        }else{
            Toast.makeText(this, "Chybí data.", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO nějak se to nastavilo na completed a nevím jak :D
    public void validate(View view) {
        Toast.makeText(this, "Cože?", Toast.LENGTH_SHORT).show();
        switch (Integer.parseInt(right_answer)){
            case 1:
                if (answer_1.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    //TODO set as completed
                } else {
                    //TODO show bad answer and uncheck checkbox
                }
                break;
            case 2:
                if (answer_2.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    //TODO set as completed
                } else {
                    //TODO show bad answer and uncheck checkbox
                }
                break;
            case 3:
                if (answer_3.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    //TODO set as completed
                } else {
                    //TODO show bad answer and uncheck checkbox
                }
                break;
            default:
                if (answer_4.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    //TODO set as completed
                } else {
                    //TODO show bad answer and uncheck checkbox
                }

        }
    }

    public void getNext(View view) {
        //TODO
    }

    public void getPrevious(View view) {
        //TODO
    }

}