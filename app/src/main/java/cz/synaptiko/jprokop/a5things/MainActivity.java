package cz.synaptiko.jprokop.a5things;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LanguageDescription[] mLanguageDescriptions = new LanguageDescription[] {
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

    private int[] mTryItButtonIds = new int[] {
            R.id.language_0_tryItButton,
            R.id.language_1_tryItButton,
            R.id.language_2_tryItButton,
            R.id.language_3_tryItButton,
            R.id.language_4_tryItButton
    };

    private int[] mWikipediaButtonIds = new int[] {
            R.id.language_0_wikipediaButton,
            R.id.language_1_wikipediaButton,
            R.id.language_2_wikipediaButton,
            R.id.language_3_wikipediaButton,
            R.id.language_4_wikipediaButton
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillShowcaseByLanguageDescriptions();
    }

    public void showMeClicked(View view) {
        View intro = findViewById(R.id.intro);
        View showcase = findViewById(R.id.showcase);
        intro.setVisibility(View.GONE);
        showcase.setVisibility(View.VISIBLE);
    }

    public void tryItClicked(View view) {
        int index = findIndexById(mTryItButtonIds, view.getId());
        if (index >= 0) {
            openUrl(mLanguageDescriptions[index].getTryItUrl());
        }
    }

    public void wikipediaClicked(View view) {
        int index = findIndexById(mWikipediaButtonIds, view.getId());
        if (index >= 0) {
            openUrl(mLanguageDescriptions[index].getWikipediaUrl());
        }
    }

    private void fillShowcaseByLanguageDescriptions() {
        int index = 0;
        String prefix = "language_";
        String packageName = getPackageName();
        Resources resources = getResources();

        for (LanguageDescription description : mLanguageDescriptions) {
            int nameId = resources.getIdentifier(prefix + index + "_name", "id", packageName);
            int logoId = resources.getIdentifier(prefix + index + "_logo", "id", packageName);
            int exampleId = resources.getIdentifier(prefix + index + "_example", "id", packageName);
            TextView nameTextView = (TextView) findViewById(nameId);
            ImageView logoImageView = (ImageView) findViewById(logoId);
            TextView exampleTextView = (TextView) findViewById(exampleId);

            nameTextView.setText(description.getName());
            logoImageView.setImageResource(description.getLogoDrawableResource());
            exampleTextView.setText(description.getExampleCode());

            index += 1;
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
        startActivity(openUrlIntent);
    }

}
