package cz.synaptiko.jprokop.a5things;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int mCurrentView = R.id.intro;
    private LanguagesController languagesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        languagesController = new LanguagesController(this);
    }

    public void onTakeAQuizClicked(View view) {
        switchToView(R.id.quiz);
        languagesController.generateQuizQuestion();
    }

    public void onJustShowClicked(View view) {
        switchToView(R.id.showcase);
        languagesController.prepareShowcase();
    }

    public void onTryItClicked(View view) {
        languagesController.invokeTryItAction(view.getId());
    }

    public void onWikipediaClicked(View view) {
        languagesController.invokeWikipediaAction(view.getId());
    }

    public void onQuizAnswerClicked(View view) {
        languagesController.evaluateQuizAnswer(view.getId());
        languagesController.generateQuizQuestion();
    }

    @Override
    public void onBackPressed() {
        if (mCurrentView == R.id.intro) {
            super.onBackPressed();
        }
        else {
            switchToView(R.id.intro);
        }
    }

    private void switchToView(int viewId) {
        View intro = findViewById(R.id.intro);
        View showcase = findViewById(R.id.showcase);
        View quiz = findViewById(R.id.quiz);
        if (mCurrentView != viewId) {
            mCurrentView = viewId;
            intro.setVisibility(viewId == R.id.intro ? View.VISIBLE : View.GONE);
            showcase.setVisibility(viewId == R.id.showcase ? View.VISIBLE : View.GONE);
            quiz.setVisibility(viewId == R.id.quiz ? View.VISIBLE : View.GONE);
        }
    }

}
