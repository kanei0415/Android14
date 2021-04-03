package com.example.myvote0403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button voteDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("명작 그림 투표 앱 1.0v");

        int[] voteCount = new int[9];

        ImageView[] candidates = new ImageView[9];

        final int[] CANDIDATE_IDS = {
            R.id.candidate1,
            R.id.candidate2,
            R.id.candidate3,
            R.id.candidate4,
            R.id.candidate5,
            R.id.candidate6,
            R.id.candidate7,
            R.id.candidate8,
            R.id.candidate9,
        };

        final String[] CANDIDATE_NAMES = {
            "독서하는 소녀",
            "꽃 장식 모자 소녀",
            "부채를 든 소녀",
            "이레느깡 단 베르양",
            "잠자는 소녀",
            "테라스의 두 자매",
            "피아노 레슨",
            "피아노 앞 소녀들",
            "해변에서",
        };

        for(int i=0 ; i<CANDIDATE_IDS.length ; i++) {
            final int index = i; // 같은 저장 공간을 중복 사용 불가

            candidates[index] = findViewById(CANDIDATE_IDS[index]);

            candidates[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,
                            CANDIDATE_NAMES[index] + " 득표수 : " + ++voteCount[index]
                            ,Toast.LENGTH_SHORT).show();
                }
            });
        }

        voteDone = (Button)findViewById(R.id.voteDone);

        voteDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoteResult.class);

                intent.putExtra("VOTE_COUNT", voteCount);
                intent.putExtra("CANDIDATE_NAMES", CANDIDATE_NAMES);

                startActivity(intent);
            }
        });
    }
}