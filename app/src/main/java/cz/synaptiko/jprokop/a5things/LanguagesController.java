package cz.synaptiko.jprokop.a5things;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class with business logic related to:
 * - filling the layout with data
 * - generating of a quiz
 * - checking of right answer
 */
public class LanguagesController {

    /**
     * Data of all languages in this app
     */
    private LanguageDescription[] mLanguageDescriptions = new LanguageDescription[]{
            new LanguageDescription(
                    "Java", R.drawable.java,
                    "https://repl.it/Btoz",
                    "https://en.wikipedia.org/wiki/Java_(programming_language)",
                    "class Main {\n" +
                    "  public static void main(String[] args) {\n" +
                    "    System.out.println(\"Hello, World!\");\n" +
                    "  }\n" +
                    "}"
            ),
            new LanguageDescription(
                    "Python", R.drawable.python,
                    "https://repl.it/Btpw",
                    "https://en.wikipedia.org/wiki/Python_(programming_language)",
                    "#!/usr/bin/env python\n" +
                    "print(\"Hello, World!\")"
            ),
            new LanguageDescription(
                    "Javascript", R.drawable.javascript,
                    "https://repl.it/Btps",
                    "https://en.wikipedia.org/wiki/JavaScript",
                    "console.log(\"Hello, World!\");"
            ),
            new LanguageDescription(
                    "Go", R.drawable.go,
                    "https://repl.it/Btpj",
                    "https://en.wikipedia.org/wiki/Go_(programming_language)",
                    "package main\n\n" +
                    "import \"fmt\"\n\n" +
                    "func main() {\n" +
                    "  fmt.Println(\"Hello, World!\");\n" +
                    "}"
            ),
            new LanguageDescription(
                    "BrainF***", R.drawable.brainfck,
                    "https://repl.it/BtqG",
                    "https://en.wikipedia.org/wiki/Brainfuck",
                    "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
            )
    };

    /**
     * "Try it" button ids for easier "search by" index
     */
    private int[] mTryItButtonIds = new int[]{
            R.id.language_0_tryItButton,
            R.id.language_1_tryItButton,
            R.id.language_2_tryItButton,
            R.id.language_3_tryItButton,
            R.id.language_4_tryItButton
    };

    /**
     * "Wikipedia" button ids for easier "search by" index
     */
    private int[] mWikipediaButtonIds = new int[]{
            R.id.language_0_wikipediaButton,
            R.id.language_1_wikipediaButton,
            R.id.language_2_wikipediaButton,
            R.id.language_3_wikipediaButton,
            R.id.language_4_wikipediaButton
    };

    private AppCompatActivity mActivity;
    private Window mWindow;
    private String mPackageName;
    private Resources mResources;
    private Context mContext;

    private boolean mIsShowcasePrepared = false;
    private int mQuizRightAnswerId;

    /**
     * Construct the controller
     * @param activity Reference to an activity which will be used for filling with data
     */
    public LanguagesController(AppCompatActivity activity) {
        mActivity = activity;
        mWindow = activity.getWindow();
        mPackageName = activity.getPackageName();
        mResources = activity.getResources();
        mContext = activity.getApplicationContext();
    }

    /**
     * Evaluates quiz answer, in case answer is right it will generate new quiz question.
     * @param id Id of pressed button
     */
    public void evaluateQuizAnswer(int id) {
        Toast toast = Toast.makeText(mContext, (id == mQuizRightAnswerId
                ? R.string.successful_guess
                : R.string.unsuccessful_guess
        ), Toast.LENGTH_SHORT);
        toast.show();

        if (id == mQuizRightAnswerId) {
            generateQuizQuestion();
        }
    }

    /**
     * Generates new quiz question randomly
     */
    public void generateQuizQuestion() {
        TextView exampleTextView = (TextView) mWindow.findViewById(R.id.quiz_example);
        Button[] answerButtons = new Button[] {
            (Button) mWindow.findViewById(R.id.quiz_answer_a),
            (Button) mWindow.findViewById(R.id.quiz_answer_b),
            (Button) mWindow.findViewById(R.id.quiz_answer_c)
        };
        // lists all answers (as indexes)
        List<Integer> answers = Arrays.asList(0, 1, 2, 3, 4);
        int rightAnswer;
        int answerToButtonIndex = 0;
        Random random = new Random();

        // shuffle all answers
        Collections.shuffle(answers, random);
        // choose right answer from the first three answers
        rightAnswer = random.nextInt(3);

        // fill buttons with answers
        exampleTextView.setText(mLanguageDescriptions[answers.get(rightAnswer)].getExampleCode());
        for (Button answerButton : answerButtons) {
            answerButton.setText(mLanguageDescriptions[answers.get(answerToButtonIndex)].getName());
            answerToButtonIndex += 1;
        }
        mQuizRightAnswerId = answerButtons[rightAnswer].getId();
    }

    /**
     * Prepares list of all languages with examples
     */
    public void prepareShowcase() {
        if (!this.mIsShowcasePrepared) {
            this.mIsShowcasePrepared = true;

            int index = 0;
            String prefix = "language_";

            for (LanguageDescription description : mLanguageDescriptions) {
                int nameId = mResources.getIdentifier(prefix + index + "_name", "id", mPackageName);
                int logoId = mResources.getIdentifier(prefix + index + "_logo", "id", mPackageName);
                int exampleId = mResources.getIdentifier(prefix + index + "_example", "id", mPackageName);
                TextView nameTextView = (TextView) mWindow.findViewById(nameId);
                ImageView logoImageView = (ImageView) mWindow.findViewById(logoId);
                TextView exampleTextView = (TextView) mWindow.findViewById(exampleId);

                nameTextView.setText(description.getName());
                logoImageView.setImageResource(description.getLogoDrawableResource());
                exampleTextView.setText(description.getExampleCode());

                index += 1;
            }
        }
    }

    /**
     * Open "Try it" url of the language
     * @param id Id of "Try it" button
     */
    public void invokeTryItAction(int id) {
        int index = findIndexById(mTryItButtonIds, id);
        if (index >= 0) {
            openUrl(mLanguageDescriptions[index].getTryItUrl());
        }
    }

    /**
     * Open "Wikipedia" url of the language
     * @param id Id of "Wikipedia" button
     */
    public void invokeWikipediaAction(int id) {
        int index = findIndexById(mWikipediaButtonIds, id);
        if (index >= 0) {
            openUrl(mLanguageDescriptions[index].getWikipediaUrl());
        }
    }

    private int findIndexById(int[] ids, int viewId) {
        int index = -1;

        for (int i = 0; i < ids.length; i++) {
            if (viewId == ids[i]) {
                index = i;
                break;
            }
        }

        return index;
    }

    private void openUrl(String url) {
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
        openUrlIntent.setData(Uri.parse(url));
        mActivity.startActivity(openUrlIntent);
    }

}
