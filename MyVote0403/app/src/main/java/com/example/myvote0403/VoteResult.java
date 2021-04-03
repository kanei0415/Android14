package com.example.myvote0403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class VoteResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vode_result);

        setTitle("명화 투표 결과");

        Intent intent = getIntent();

        int[] voteCount = intent.getIntArrayExtra("VOTE_COUNT");
        String[] candidateName = intent.getStringArrayExtra("CANDIDATE_NAMES");

        TextView[] textViews = new TextView[voteCount.length];
        RatingBar[] ratingBars = new RatingBar[voteCount.length];

        final int[] VOTED_IDS = {
            R.id.candidate1Voted,
            R.id.candidate2Voted,
            R.id.candidate3Voted,
            R.id.candidate4Voted,
            R.id.candidate5Voted,
            R.id.candidate6Voted,
            R.id.candidate7Voted,
            R.id.candidate8Voted,
            R.id.candidate9Voted,
        };
        final int[] RATING_IDS = {
            R.id.candidate1Rate,
            R.id.candidate2Rate,
            R.id.candidate3Rate,
            R.id.candidate4Rate,
            R.id.candidate5Rate,
            R.id.candidate6Rate,
            R.id.candidate7Rate,
            R.id.candidate8Rate,
            R.id.candidate9Rate,
        };

        for(int i=0 ; i<voteCount.length ; i++) {
            final int index = i;

            textViews[index] = (TextView)findViewById(VOTED_IDS[index]);
            textViews[index].setText(candidateName[index]);

            ratingBars[index] = (RatingBar)findViewById(RATING_IDS[index]);
            ratingBars[index].setRating(voteCount[index]);
        }

        Button exitBtn = (Button)findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}