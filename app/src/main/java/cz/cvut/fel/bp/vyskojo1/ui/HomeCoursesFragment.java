package cz.cvut.fel.bp.vyskojo1.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import cz.cvut.fel.bp.vyskojo1.R;
import cz.cvut.fel.bp.vyskojo1.database.MyDatabaseHelper;

public class HomeCoursesFragment extends Fragment {

    View view;
    ProgressBar progressBar;
    Cursor allLessons;

    MyDatabaseHelper myDB;

    public HomeCoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new MyDatabaseHelper(getActivity());
//        setProgress();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_courses, container, false);
        ViewGroup container_group = (ViewGroup) view;
        int count = container_group.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = container_group.getChildAt(i);
            progressBar = v.findViewById(R.id.progress);
            allLessons = myDB.readAllData();
            float lessons = allLessons.getCount();
            float completed = 0;
            while (allLessons.moveToNext()) {
                if ((allLessons.getString(5)).equals("1")) {
                    completed++;
                }
            }
            progressBar.setProgress(Math.round((completed/lessons)*100));
            TextView progress_text = v.findViewById(R.id.progress_text);
            progress_text.setText(String.valueOf(Math.round((completed/lessons)*100)) +"% Dokončeno");
        }
        return view;
    }
}