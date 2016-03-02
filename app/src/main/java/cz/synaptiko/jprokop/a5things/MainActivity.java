package cz.synaptiko.jprokop.a5things;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMeClicked(View view) {
        //View intro = findViewById(R.id.intro);
        View showcase = findViewById(R.id.showcase);
        //intro.setVisibility(View.GONE);
        showcase.setVisibility(View.VISIBLE);
    }
}
