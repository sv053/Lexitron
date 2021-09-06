package org.learning.lexitron;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.learning.lexitron.model.User;
import org.learning.lexitron.service.FileReader;
import org.learning.lexitron.service.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    String login;
    static List<String> names;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        Button findBtn = (Button) findViewById(R.id.findUserBtn);
        EditText userLoginInput = (EditText) findViewById(R.id.loginInput);

        FileWriter fw = new FileWriter(this);
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
        userLoginInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                findBtn.isEnabled();
               login = userLoginInput.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
     //   }
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