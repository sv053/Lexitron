package org.learning.lexitron;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import org.learning.lexitron.datastream.HTTPReader;
import org.learning.lexitron.fileservice.FileReader;
import org.learning.lexitron.fileservice.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ChoosetextActivity extends AppCompatActivity {

    private static final int PICKFILE_RESULT_CODE = 1;
    private List<Button> buttonList = new ArrayList<Button>();
    private FlexboxLayout layout;
    private TextView dictResult;
    private Map<String,String> dictionary;

 //   private static int btnId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetext);

        layout = (FlexboxLayout) findViewById(R.id.btnLayout);
        dictResult = (TextView) findViewById(R.id.dataFromDict);
        dictionary = new HashMap<>();

     //   TextView choosenTextView = (TextView) findViewById(R.id.loadSavedTextTv);
        //  final TextView textView = (TextView) findViewById(R.id.textView);
        Button chooseTextBtn = (Button) findViewById(R.id.saveTextBtn);
        chooseTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addButton("test");
            }
        });


        EditText userInputEt = (EditText) findViewById(R.id.inputText);
        userInputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                chooseTextBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String writtenText = userInputEt.getText().toString().trim();
                if(writtenText.equals(""))
                    chooseTextBtn.setEnabled(false);

                chooseTextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FileWriter fw = new FileWriter(ChoosetextActivity.this);
                        try {
                            fw.WriteFile(writtenText);
//            textView.append(loadText("C:\\Users\\admin\\AndroidStudioProjects\\Lexitron\\words.txt").toString());
                        //    choosenTextView.setText(loadText("failik.txt").toString());

                            Arrays.stream(writtenText.split(" ")).forEach(t-> addButton(t));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    //    try {
//            textView.append(loadText("C:\\Users\\admin\\AndroidStudioProjects\\Lexitron\\words.txt").toString());
          //  choosenTextView.append(loadText("failik.txt").toString());
  //      } catch (IOException e) {
   //         e.printStackTrace();
   //     }
//-------------------load from dict
        TextView contentView = (TextView) findViewById(R.id.dataFromDict);
//        WebView webView = (WebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        Button btnFetch = (Button)findViewById(R.id.loadFromDictBtn);
//    //    EditText editText = (EditText) findViewById(R.id.quieryEt);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                btnFetch.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        dictResult.setText("Загрузка...");
////                        new Thread(new Runnable() {
////                            public void run() {
////                                try {
////                                    String content = getContent("https://dle.rae.es/", editText.getText().toString().trim());
////                                    String cleanString = content.substring(content.indexOf("description"));
////                                    content = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf(".\">"));
////                                    String finalContent = content;
////                                    dictResult.post(new Runnable() {
////                                        public void run() {
////                                            dictResult.setText(finalContent);
////                                        }
////                                    });
////                                } catch (IOException ex) {
////                                    dictResult.post(new Runnable() {
////                                        public void run() {
////                                            dictResult.setText("Ошибка: " + ex.getMessage());
////                                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
////                                }
////                                catch (Exception ex){
////                                    dictResult.post(new Runnable() {
////                                        public void run() {
////                                            dictResult.setText("Ошибка: " + ex.getMessage());
////                                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
////                                }
////                            }
////                        }).start();
//                    }
//                });
//            }
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    public void addButton(String s) {

        Button button = new Button(this);  // создаём новый Button
        button.setId(buttonList.size());  //  Устанавливаем id (индекс в списке)
        button.setText(s);
        button.setPadding(10,0,10,0);
        button.setBackgroundResource(R.drawable.button_white_pressed_lightblue);
        button.setOnClickListener(new View.OnClickListener() {  // Устанавливаем слушателя
            @Override
            public void onClick(View v) {
                int position = v.getId();  //  Получаем id (индекс в списке)
                Button button = buttonList.get(position);  //  Получаем кнопку
             //   button.setText("Нажата кнопка №" + (position + 1));  // Как-нибудь это обрабатываем

                //translate
                String wordToTranslate = button.getText().toString();

                //translate ends

                if(!dictionary.keySet().contains(wordToTranslate)){
                    dictionary.put(wordToTranslate, translateWord(wordToTranslate));

                }
                else {
                    dictionary.remove(button.getText(),wordToTranslate);
                    button.setBackgroundResource(R.drawable.button_white_pressed_lightblue);
                }

            }
        });
        buttonList.add(button);  //  Добавляем в список
        layout.addView(button);  // Показываем на экран
    }

    private  String loadText(String filepath) throws IOException {

        List<String> resText = new FileReader(this,filepath).ReadFile();
        String theOneStr = "";
        for (String str : resText)
            theOneStr += str;

        return theOneStr;
    }

    private String getContent(String path, String query) throws IOException {
//
        HTTPReader httpReader = new HTTPReader(path);
        return httpReader.Read(path, query);

    }

    private String translateWord(String word){
        dictResult.setText("Загрузка...");
        final String[] translatedText = new String[1];
        new Thread(new Runnable() {
            public void run() {
                try {
                    String content = getContent("https://dle.rae.es/", word);
                    String cleanString = content.substring(content.indexOf("description"));
                    content = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf(".\">"));
                    translatedText[0] = content;
                    dictResult.post(new Runnable() {
                        public void run() {
                            dictResult.setText(translatedText[0]);
                        }
                    });
                } catch (IOException ex) {
                    dictResult.post(new Runnable() {
                        public void run() {
                            dictResult.setText("Ошибка: " + ex.getMessage());
                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch (Exception ex){
                    dictResult.post(new Runnable() {
                        public void run() {
                            dictResult.setText("Ошибка: " + ex.getMessage());
                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
        return translatedText[0];
    }

}