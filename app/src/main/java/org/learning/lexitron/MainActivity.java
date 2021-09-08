package org.learning.lexitron;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.learning.lexitron.datastream.HTTPReader;
import org.learning.lexitron.model.User;
import org.learning.lexitron.fileservice.FileReader;
import org.learning.lexitron.fileservice.FileWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String login;
    static List<String> names;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//-------------------load from dict
        TextView contentView = (TextView) findViewById(R.id.dataFromDict);
//        WebView webView = (WebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
        Button btnFetch = (Button)findViewById(R.id.loadFromDictBtn);
        EditText editText = (EditText) findViewById(R.id.quieryEt);
        btnFetch.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            contentView.setText("Загрузка...");
                                            new Thread(new Runnable() {
                                                public void run() {
                                                    try {

                                                        String content = getContent("https://dle.rae.es/", editText.getText().toString().trim());
                                                        String cleanString = content.substring(content.indexOf("description"));
                                                        if (cleanString.contains("1"))
                                                            content = cleanString.substring(cleanString.indexOf("1"), cleanString.indexOf(".\">"));
//                            webView.post(new Runnable() {
//                                                        webView.post(new Runnable() {
//                                                            public void run() {
////                                    webView.loadDataWithBaseURL("https://stackoverflow.com/",content, "text/html", "UTF-8", "https://stackoverflow.com/");
//                                                                Toast.makeText(getApplicationContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
//                                                                webView.loadDataWithBaseURL("https://stackoverflow.com/", content, "text/html", "UTF-8", "https://stackoverflow.com/");
//                                                            }
//                                                        });
                                                        String finalContent = content;
                                                            contentView.post(new Runnable() {
                                                                public void run() {
                                                                    //   contentView.setText(content);
                                                                    //   contentView.setText(content.substring(content.indexOf("QFTYd25")));

                                                                    contentView.setText(finalContent);
                                                                }
                                                            });
                                                    } catch (IOException ex) {
                                                        contentView.post(new Runnable() {
                                                            public void run() {
                                                                contentView.setText("Ошибка: " + ex.getMessage());
                                                                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                }
                                            }).start();
                                        }
                                    });


        //-------------loaded from dict

        final TextView textView = (TextView) findViewById(R.id.textView);
        Button findBtn = (Button) findViewById(R.id.findUserBtn);
   //     EditText userLoginInput = (EditText) findViewById(R.id.loginInput);

        FileWriter fw = new FileWriter(MainActivity.this);
        try {
            String s = fw.WriteFile().get(0);
            textView.append(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            textView.append(loadText("C:\\Users\\admin\\AndroidStudioProjects\\Lexitron\\words.txt").toString());
            textView.append(loadText("failik.txt").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        downloadAllUsers();
//        userLoginInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                findBtn.isEnabled();
//               login = userLoginInput.getText().toString();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
      //  if(login.length() > 0){

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        NetworkService.getInstance()
                .getJSONApi()
                .getUserWithLogin("login2")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        User user = response.body();

                        textView.append(user.getId() + "\n");
                        textView.append(user.getName() + "\n");
                        textView.append(user.getLogin() + "\n");
                        textView.append(user.getEmail() + "\n");
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

            }
        });
       }
 //   }

            private String getContent(String path, String query) throws IOException {
//                BufferedReader reader=null;
//                InputStream stream = null;
//                HttpsURLConnection connection = null;
//                try {
//                    URL url=new URL(path);
//                    connection =(HttpsURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setReadTimeout(10000);
//                    connection.connect();
//                    stream = connection.getInputStream();
//                    reader= new BufferedReader(new InputStreamReader(stream));
//                    StringBuilder buf=new StringBuilder();
//                    String line;
//                    while ((line=reader.readLine()) != null) {
//                        buf.append(line).append("\n");
//                    }
//                    return(buf.toString());
//                }
//                finally {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                    if (stream != null) {
//                        stream.close();
//                    }
//                    if (connection != null) {
//                        connection.disconnect();
//                    }

                HTTPReader httpReader = new HTTPReader(path);
               return httpReader.Read(path, query);

               }


    private void downloadAllUsers(){
        NetworkService.getInstance()
                .getJSONApi()
                .getAllUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User> users = response.body();
                        names = new ArrayList<>();
                        for(User u: users)
                            names.add(u.getName());


                        ListView usersList = (ListView) findViewById(R.id.allUsersListView);

                        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1, names);

                        usersList.setAdapter(adapter);


                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                     //   textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }


                });
    }

    private  String loadText(String s) throws IOException {

        List<String> resText = new FileReader(this,s).ReadFile();
        String theOneStr = "";
        for (String str : resText)
            theOneStr += str;

            return theOneStr;
    }
}