package org.learning.lexitron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import org.learning.lexitron.datastream.HTTPReader;
import org.learning.lexitron.fileservice.FileReader;
import org.learning.lexitron.parcel.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettaskActivity extends AppCompatActivity {

    private List<Button> buttonList = new ArrayList<>();
    private FlexboxLayout layout;
    private TextView dictResult;
    private Map<String,String> dictionary;
    private String loadedText;
    String choosenWords = "";
  //  private TextView translateField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settask);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                     //   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        layout = (FlexboxLayout) findViewById(R.id.btnLayout);
     //   dictResult = (TextView) findViewById(R.id.dataFromDict);
        Bundle arguments = getIntent().getExtras();
        loadedText = arguments.get("text").toString();
        dictionary = new HashMap<>();
      //  choosenWords = new String[]{};

        Toast.makeText(this, "dict_length ", 5000);

        //    String savedText = loadText("failik.txt");
      //  String replacedString = loadedText.replaceAll("[^A-Za-z]+", " ").trim();
//        String replacedString = loadedText.replace(',', ' ')
//                                            .replace('¿',' ');
       String replacedString = loadedText.replace(',', ' ')
                .replace('.',' ')
                .replace('?',' ')
                .replace('!',' ')
                .replace('¿',' ')
               .replace('\0151',' ')

                .replace('\'',' ')
                ;
      //  replacedString = loadedText;
        Arrays.stream(replacedString.split(" ")).forEach(this::addButton);

        Button makeTasksBtn = (Button) findViewById(R.id.makeTasksBtn);
        makeTasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MakeTaskActivity.class);
                intent.putExtra("dict", choosenWords);
                startActivity(intent);
            }
        });
    }

    private  String loadText(String filepath) throws IOException {

        List<String> resText = new FileReader(this,filepath).ReadFile();
        String theOneStr = "";
        for (String str : resText)
            theOneStr += str;

        return theOneStr;
    }
// create buttons with words from the input text
    public void addButton(String s) {

        Button button = new Button(this);
      //  button.cu
        button.setId(buttonList.size());
        button.setText(s);
        button.setPadding(10,0,10,0);
        button.setBackgroundResource(R.drawable.button_white_pressed_lightblue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = v.getId();
                Button button = buttonList.get(position);
                String wordToTranslate = button.getText().toString();
                final String[] translation = {""};

                if(!dictionary.keySet().contains(wordToTranslate)){
                    dictionary.put(wordToTranslate, translation[0]);
                    button.setBackgroundResource(R.drawable.style_btn_stroke_pressed_ligtblue);
                    choosenWords += wordToTranslate + " ";
                }
                else {
                    dictionary.remove(wordToTranslate,translation[0]);
                    button.setBackgroundResource(R.drawable.style_btn_stroke_white);

                }
            }
        });

        buttonList.add(button);  //  Добавляем в список
        layout.addView(button);  // Показываем на экран
    }
//
//    private String getContent(String path, String query) throws IOException {
//
//        HTTPReader httpReader = new HTTPReader(path);
//        return httpReader.Read(path, query);
//
//    }

//    private String translateWord(String word){
//        dictResult.setText("Загрузка...");
//        final Handler myHandler = new Handler();
//        final String[] translatedText = {""};
//
//       new Thread(new Runnable() {
//            public void run() {
//                try {
//                    String content = getContent("https://dle.rae.es/", word);
//                    String cleanString = content.substring(content.indexOf("description"));
//                    content = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf("\">"));
//                    translatedText[0] = content;
//                    dictResult.post(new Runnable() {
//                        public void run() {
//                            dictResult.setText(translatedText[0]);
//                        }
//                    });
//                } catch (IOException ex) {
//                    dictResult.post(new Runnable() {
//                        public void run() {
//                            dictResult.setText("Ошибка: " + ex.getMessage());
//                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//                catch (Exception ex){
//                    dictResult.post(new Runnable() {
//                        public void run() {
//                            dictResult.setText("Ошибка: " + ex.getMessage());
//                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        }).start();
//        return translatedText[0];
//    }
}