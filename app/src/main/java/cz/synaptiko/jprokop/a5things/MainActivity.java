package cz.synaptiko.jprokop.a5things;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LanguageDescription[] mLanguageDescriptions = new LanguageDescription[] {
            new LanguageDescription("Java", R.drawable.java, "try", "wiki", "class Main {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(&quot;Hello, World!&quot;);\n\t}\n}"),
            new LanguageDescription("Groovy", R.drawable.groovy, "try", "wiki", "aaa"),
            new LanguageDescription("Javascript", R.drawable.javascript, "try", "wiki", "aaa"),
            new LanguageDescription("Go", R.drawable.go, "try", "wiki", "aaa"),
            new LanguageDescription("Brainf*ck", R.drawable.brainfck, "try", "wiki", "aaa")
    };

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

    public void tryItClicked(View view) {
        Intent openTryItUrlIntent = new Intent(Intent.ACTION_VIEW);
        openTryItUrlIntent.setData(Uri.parse("http://google.com"));
        startActivity(openTryItUrlIntent);
    }
}
