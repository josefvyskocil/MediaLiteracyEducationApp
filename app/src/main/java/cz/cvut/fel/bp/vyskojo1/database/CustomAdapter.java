package cz.cvut.fel.bp.vyskojo1.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import cz.cvut.fel.bp.vyskojo1.R;
import cz.cvut.fel.bp.vyskojo1.activity.LessonActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList lesson_id, lesson_difficulty, lesson_type, lesson_question, lesson_image,
            lesson_completed, lesson_right_answer, lesson_title, lesson_answer_1, lesson_answer_2,
            lesson_answer_3, lesson_answer_4, lesson_answer_description;

    public CustomAdapter(Activity activity, Context context, ArrayList lesson_id,
                         ArrayList lesson_difficulty, ArrayList lesson_type,
                         ArrayList lesson_question, ArrayList lesson_image,
                         ArrayList lesson_completed, ArrayList lesson_right_answer,
                         ArrayList lesson_title, ArrayList lesson_answer_1,
                         ArrayList lesson_answer_2, ArrayList lesson_answer_3,
                         ArrayList lesson_answer_4, ArrayList lesson_answer_description){
        this.activity = activity;
        this.context = context;
        this.lesson_id = lesson_id;
        this.lesson_difficulty = lesson_difficulty;
        this.lesson_type = lesson_type;
        this.lesson_question = lesson_question;
        this.lesson_image = lesson_image;
        this.lesson_completed = lesson_completed;
        this.lesson_right_answer = lesson_right_answer;
        this.lesson_title = lesson_title;
        this.lesson_answer_1 = lesson_answer_1;
        this.lesson_answer_2 = lesson_answer_2;
        this.lesson_answer_3 = lesson_answer_3;
        this.lesson_answer_4 = lesson_answer_4;
        this.lesson_answer_description = lesson_answer_description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.book_id_txt.setText(String.valueOf(lesson_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(lesson_title.get(position)));
        if (Integer.parseInt(lesson_completed.get(position).toString()) == 1)
            holder.itemView.findViewById(R.id.completed).setVisibility(View.VISIBLE);

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, LessonActivity.class);
            intent.putExtra("id", String.valueOf(lesson_id.get(position)));
            activity.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return lesson_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
