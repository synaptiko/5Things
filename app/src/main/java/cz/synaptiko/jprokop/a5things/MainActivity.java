package cz.synaptiko.jprokop.a5things;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /**
     * Contains information about a view which is currently shown (needed because of the "trick" for
     * showing 3 different views in one activity)
     */
    private int mCurrentView = R.id.intro;
    /**
     * Contains business logic related to filling the layout with data and generating of a quiz.
     */
    private LanguagesController languagesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        languagesController = new LanguagesController(this);
    }

    /**
     * "Take a quiz" button was clicked
     * @param view
     */
    public void onTakeAQuizClicked(View view) {
        switchToView(R.id.quiz);
        languagesController.generateQuizQuestion();
    }

    /**
     * "Just show" button was clicked
     * @param view
     */
    public void onJustShowClicked(View view) {
        switchToView(R.id.showcase);
        languagesController.prepareShowcase();
    }

    /**
     * "Try it" button was clicked
     * @param view
     */
    public void onTryItClicked(View view) {
        languagesController.invokeTryItAction(view.getId());
    }

    /**
     * "Wikipedia" button was clicked
     * @param view
     */
    public void onWikipediaClicked(View view) {
        languagesController.invokeWikipediaAction(view.getId());
    }

    /**
     * Button for quiz answer was clicked
     * @param view
     */
    public void onQuizAnswerClicked(View view) {
        languagesController.evaluateQuizAnswer(view.getId());
    }

    /**
     * Back button was pressed, I want special logic in it.
     */
    @Override
    public void onBackPressed() {
        if (mCurrentView == R.id.intro) {
            // default behavior in intro
            super.onBackPressed();
        }
        else {
            // return back to the intro from any other subview
            switchToView(R.id.intro);
        }
    }

    /**
     * Helper method for switching between main view and subviews.
     *
     * @param viewId Which view should be shown
     */
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
