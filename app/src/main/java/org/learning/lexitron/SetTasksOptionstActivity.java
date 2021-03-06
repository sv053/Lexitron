package org.learning.lexitron;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import org.learning.lexitron.datastream.HTTPReader;
import org.learning.lexitron.fileservice.FileReader;
import org.learning.lexitron.fileservice.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetTasksOptionstActivity extends AppCompatActivity {

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
    //    dictResult = (TextView) findViewById(R.id.dataFromDict);
        dictionary = new HashMap<>();

        Button chooseTextBtn = (Button) findViewById(R.id.saveTextBtn);
//        chooseTextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                addButton("test");
//            }
//        });


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
                        FileWriter fw = new FileWriter(SetTasksOptionstActivity.this);
                        try {
                            fw.WriteFile(writtenText);
//            textView.append(loadText("C:\\Users\\admin\\AndroidStudioProjects\\Lexitron\\words.txt").toString());
                        //    choosenTextView.setText(loadText("failik.txt").toString());

                         //   Arrays.stream(writtenText.split(" ")).forEach(t-> addButton(t));
                         //   Arrays.stream(writtenText.split(" ")).forEach(t-> dictionary.put(t, ""));
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
     //   TextView contentView = (TextView) findViewById(R.id.dataFromDict);
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
////                        dictResult.setText("????????????????...");
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
////                                            dictResult.setText("????????????: " + ex.getMessage());
////                                            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
////                                }
////                                catch (Exception ex){
////                                    dictResult.post(new Runnable() {
////                                        public void run() {
////                                            dictResult.setText("????????????: " + ex.getMessage());
////                                            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
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

//    public void addButton(String s) {
//
//        Button button = new Button(this);  // ?????????????? ?????????? Button
//        button.setId(buttonList.size());  //  ?????????????????????????? id (???????????? ?? ????????????)
//        button.setText(s);
//        button.setPadding(10,0,10,0);
//        button.setBackgroundResource(R.drawable.button_white_pressed_lightblue);
//        button.setOnClickListener(new View.OnClickListener() {  // ?????????????????????????? ??????????????????
//            @Override
//            public void onClick(View v) {
//                int position = v.getId();  //  ???????????????? id (???????????? ?? ????????????)
//                Button button = buttonList.get(position);  //  ???????????????? ????????????
//             //   button.setText("???????????? ???????????? ???" + (position + 1));  // ??????-???????????? ?????? ????????????????????????
//
//                //translate
//                String wordToTranslate = button.getText().toString();
//
//                //translate ends
//
//                if(!dictionary.keySet().contains(wordToTranslate)){
//                    String translation = translateWord(wordToTranslate);
//                    dictionary.put(wordToTranslate, translation);
//
//                }
//                else {
//                    dictionary.remove(button.getText(),wordToTranslate);
//                    button.setBackgroundResource(R.drawable.button_white_pressed_lightblue);
//                }
//
//            }
//        });
//        buttonList.add(button);  //  ?????????????????? ?? ????????????
//        layout.addView(button);  // ???????????????????? ???? ??????????
//    }

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
        dictResult.setText("????????????????...");
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
                            dictionary.put(word, translatedText[0]);
                        }
                    });
                } catch (IOException ex) {
                    dictResult.post(new Runnable() {
                        public void run() {
                            dictResult.setText("????????????: " + ex.getMessage());
                            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch (Exception ex){
                    dictResult.post(new Runnable() {
                        public void run() {
                            dictResult.setText("????????????: " + ex.getMessage());
                            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
        return translatedText[0];
    }

}