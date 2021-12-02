package org.learning.lexitron;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.learning.lexitron.datastream.HTTPReader;
import org.learning.lexitron.fileservice.FileWriter;

import java.io.IOException;

public class ChoosetextActivity extends Activity {

    private static final int PICKFILE_RESULT_CODE = 1;
//    private List<Button> buttonList = new ArrayList<Button>();
//    private FlexboxLayout layout;
    private TextView dictResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetext);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                     //   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

      //  dictResult = (TextView) findViewById(R.id.dataFromDict);

        Button chooseTextBtn = (Button) findViewById(R.id.saveTextBtn);
        chooseTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           //     addButton("test");
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

                        //    Arrays.stream(writtenText.split(" ")).forEach(t-> addButton(t));
                            Intent intent = new Intent(ChoosetextActivity.this, SettaskActivity.class);
                            intent.putExtra("text", writtenText);
                            startActivity(intent);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


//-------------------load from dict
//        TextView cf = (TextView) findViewById(R.id.tf);
////        WebView webView = (WebView) findViewById(R.id.webView);
////        webView.getSettings().setJavaScriptEnabled(true);
//        Button btnFetch = (Button)findViewById(R.id.loadFromDictBtn1);
//       EditText editText = (EditText) findViewById(R.id.quieryEt1);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                btnFetch.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        cf.setText("Загрузка...");
//                        new Thread(new Runnable() {
//                            public void run() {
//                                try {
//                                    String content = getContent("https://dle.rae.es/", editText.getText().toString().trim());
//                                    String cleanString = content.substring(content.indexOf("description"));
//                                    content = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf(".\">"));
//                                    String finalContent = content;
//                                    cf.post(new Runnable() {
//                                        public void run() {
//                                            cf.setText(finalContent);
//                                        }
//                                    });
//                                } catch (IOException ex) {
//                                    cf.post(new Runnable() {
//                                        public void run() {
//                                            cf.setText("Ошибка: " + ex.getMessage());
//                                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
//                                catch (Exception ex){
//                                    dictResult.post(new Runnable() {
//                                        public void run() {
//                                            cf.setText("Ошибка: " + ex.getMessage());
//                                            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
//                            }
//                        }).start();
//                    }
//                });
//            }
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
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