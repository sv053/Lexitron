package org.learning.lexitron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.learning.lexitron.datastream.HTTPReader;
import org.learning.lexitron.parcel.Dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeTaskActivity extends AppCompatActivity {

    String dict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_task);

        Toast.makeText(this, "i am ok ", 5000);

        Intent intent = getIntent();
        dict = intent.getStringExtra("dict");

        //  String s = "dict.keySet().toString()";
        TextView textView = (TextView) findViewById(R.id.tv);
        textView.setText(dict);

        ListView wordsList = (ListView) findViewById(R.id.choosenWordsListView);
        List<String> wordsWithTranslation = new ArrayList<>();
//        ArrayAdapter<String> adapter = new ArrayAdapter(MakeTaskActivity.this,
//                android.R.layout.simple_list_item_1, wordsWithTranslation);

        new Thread(new Runnable() {
            public void run() {
                try {
                    String[] strings = new String[]{};

                    strings = dict.replace(',', ' ')
                            .replace('.',' ')
                            .replace('?',' ')
                            .replace('!',' ')
                            .replace('¿',' ')
                            .replace('\'',' ')
                            .split(" ");
                    for (String s : strings) {
                        s = s.trim();
                        if(s.equals("")) continue;

                        String content = getContent("https://dle.rae.es/", s);
                        String cleanString = content.substring(content.indexOf("description"));
                        String resultParse = "word is not found";

                        if(!cleanString.isEmpty()){
                            if(content.contains("1")){
                                resultParse = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf("\">"));
                            }
                        }
                        wordsWithTranslation.add(s + " ~~ " +resultParse);
                    }
                }catch (Exception e){
                    textView.post(new Runnable() {
                        public void run() {
                           textView.setText("Ошибка: " + e.getMessage());
                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                ArrayAdapter<String> adapter = new ArrayAdapter(MakeTaskActivity.this,
                        android.R.layout.simple_list_item_1, wordsWithTranslation);

                wordsList.post(
                        new Runnable(){
                            @Override
                            public void run() {
                                wordsList.setAdapter(adapter);
                            }
                        });
            }
            }).start();

    }

//    private String translateWord(String word){
//        TextView textView = (TextView) findViewById(R.id.tv);
//        textView.setText("Загрузка...");
//
//        final Handler myHandler = new Handler();
//        final String[] translatedText = {""};
//
//        new Thread(new Runnable() {
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

    private String getContent(String path, String query) throws IOException {
        HTTPReader httpReader = new HTTPReader(path);
        return httpReader.Read(path, query);

    }

//    private String findTranslation(String inputWord) {
//        final String[] translation = {""};
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                            String content = getContent("https://dle.rae.es/", inputWord);
//                            String cleanString = content.substring(content.indexOf("description"));
//                            content = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf("\">"));
//                    String finalContent = content;
//
//                                    translation[0] = finalContent;
//                } catch (IOException e) {
//                    new Runnable(){
//                        public void run(){
//                            translation[0] = "translation is not found";
//                        }
//
//                    };
//
//                }
//
//            }
//        }).start();
//        return translation[0];
//    }
}