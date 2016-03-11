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

/**
 * Created by jprokop on 3/11/16.
 */
public class LanguagesController {

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

    private int[] mTryItButtonIds = new int[]{
            R.id.language_0_tryItButton,
            R.id.language_1_tryItButton,
            R.id.language_2_tryItButton,
            R.id.language_3_tryItButton,
            R.id.language_4_tryItButton
    };

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

    public LanguagesController(AppCompatActivity activity) {
        mActivity = activity;
        mWindow = activity.getWindow();
        mPackageName = activity.getPackageName();
        mResources = activity.getResources();
        mContext = activity.getApplicationContext();
    }

    public void evaluateQuizAnswer(int id) {
        //Toast toast = Toast.makeText(mContext, "Sorry, that's wrong answer.\nTry again.", Toast.LENGTH_LONG);
        Toast toast = Toast.makeText(mContext, "Yep! That's right! Next one…", Toast.LENGTH_LONG);
        toast.show();
    }

    public void generateQuizQuestion() {
        TextView exampleTextView = (TextView) mWindow.findViewById(R.id.quiz_example);
        Button[] answers = new Button[] {
            (Button) mWindow.findViewById(R.id.quiz_answer_a),
            (Button) mWindow.findViewById(R.id.quiz_answer_b),
            (Button) mWindow.findViewById(R.id.quiz_answer_c)
        };
        exampleTextView.setText(mLanguageDescriptions[0].getExampleCode());
        answers[0].setText(mLanguageDescriptions[0].getName());
        answers[1].setText(mLanguageDescriptions[1].getName());
        answers[2].setText(mLanguageDescriptions[2].getName());
    }

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

    public void invokeTryItAction(int id) {
        int index = findIndexById(mTryItButtonIds, id);
        if (index >= 0) {
            openUrl(mLanguageDescriptions[index].getTryItUrl());
        }
    }

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
