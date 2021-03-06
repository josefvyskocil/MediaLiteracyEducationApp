package cz.cvut.fel.bp.vyskojo1.activity;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cz.cvut.fel.bp.vyskojo1.R;
import cz.cvut.fel.bp.vyskojo1.database.MyDatabaseHelper;

public class LessonActivity extends AppCompatActivity {

    TextView lesson_title, question, description;
    Button check, previous, next;
    RadioButton answer_1, answer_2, answer_3, answer_4;
    ImageView image;

    String id, title, question_text, image_name, answer_1_text, answer_2_text, answer_3_text, answer_4_text, right_answer, description_text;
    MyDatabaseHelper myDB;
    Cursor allLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        lesson_title = findViewById(R.id.lesson_title);
        question = findViewById(R.id.question);
        check = findViewById(R.id.check);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        answer_1 = findViewById(R.id.answer_1);
        answer_2 = findViewById(R.id.answer_2);
        answer_3 = findViewById(R.id.answer_3);
        answer_4 = findViewById(R.id.answer_4);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);

        myDB = new MyDatabaseHelper(LessonActivity.this);
        allLessons = myDB.readAllData();

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

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
            description_text = lesson.getString(12);
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
            description.setText(description_text);
            if (answer_4_text.equals(""))
                answer_4.setVisibility(View.INVISIBLE);
            if (Integer.parseInt(id) == 1)
                previous.setVisibility(View.INVISIBLE);
            else
                previous.setVisibility(View.VISIBLE);
            if (allLessons.getCount() == Integer.parseInt(id))
                next.setVisibility(View.INVISIBLE);

        }else{
            Toast.makeText(this, "Chyb?? data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void validate(View view) {
        switch (Integer.parseInt(right_answer)){
            case 1:
                if (answer_1.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    check.setVisibility(View.INVISIBLE);
                    myDB.updateLesson(id, 1);
                    showCorrectAnswerToast();
                } else {
                    showBadAnswerToast();
                }
                break;
            case 2:
                if (answer_2.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    check.setVisibility(View.INVISIBLE);
                    myDB.updateLesson(id, 1);
                    showCorrectAnswerToast();
                } else {
                    showBadAnswerToast();
                }
                break;
            case 3:
                if (answer_3.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    check.setVisibility(View.INVISIBLE);
                    myDB.updateLesson(id, 1);
                    showCorrectAnswerToast();
                } else {
                    showBadAnswerToast();
                }
                break;
            default:
                if (answer_4.isChecked()) {
                    findViewById(R.id.group).setVisibility(View.INVISIBLE);
                    findViewById(R.id.description).setVisibility(View.VISIBLE);
                    check.setVisibility(View.INVISIBLE);
                    myDB.updateLesson(id, 1);
                    showCorrectAnswerToast();
                } else {
                    showBadAnswerToast();
                }

        }
    }

    void resetVisibility() {
        findViewById(R.id.description).setVisibility(View.INVISIBLE);
        findViewById(R.id.group).setVisibility(View.VISIBLE);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.group);
        radioGroup.clearCheck();
        check.setVisibility(View.VISIBLE);
        previous.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
    }

    public void getNext(View view) {

        Cursor lesson = myDB.getLessonById(String.valueOf(Integer.parseInt(id)+1));
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
        description_text = lesson.getString(12);
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
        description.setText(description_text);
        if (answer_4_text.equals(""))
            answer_4.setVisibility(View.INVISIBLE);
        else
            answer_4.setVisibility(View.VISIBLE);
        resetVisibility();
        if (Integer.parseInt(id) == 1)
            previous.setVisibility(View.INVISIBLE);
        else
            previous.setVisibility(View.VISIBLE);
        if (allLessons.getCount() == Integer.parseInt(id))
            next.setVisibility(View.INVISIBLE);
    }

    public void getPrevious(View view) {
        Cursor lesson = myDB.getLessonById(String.valueOf(Integer.parseInt(id)-1));
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
        description_text = lesson.getString(12);
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
        description.setText(description_text);
        if (answer_4_text.equals(""))
            answer_4.setVisibility(View.INVISIBLE);
        else
            answer_4.setVisibility(View.VISIBLE);
        resetVisibility();
        if (Integer.parseInt(id) == 1)
            previous.setVisibility(View.INVISIBLE);
        else
            previous.setVisibility(View.VISIBLE);
    }

    void showBadAnswerToast() {
        Toast toast = Toast.makeText(this, "??patn?? odpov????!", Toast.LENGTH_SHORT);
        View toastView = toast.getView();

        //Gets the actual oval background of the Toast then sets the colour filter
        toastView.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

        //Gets the TextView from the Toast so it can be editted
        TextView text = toastView.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);

        toast.show();
    }

    void showCorrectAnswerToast() {
        Toast toast = Toast.makeText(this, "Spr??vn?? odpov????!", Toast.LENGTH_SHORT);
        View toastView = toast.getView();

        //Gets the actual oval background of the Toast then sets the colour filter
        toastView.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        //Gets the TextView from the Toast so it can be editted
        TextView text = toastView.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);

        toast.show();
    }

}