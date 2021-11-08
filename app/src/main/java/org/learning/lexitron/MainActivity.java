package org.learning.lexitron;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
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
import org.learning.lexitron.model.Suffix;
import org.learning.lexitron.model.User;
import org.learning.lexitron.fileservice.FileReader;
import org.learning.lexitron.fileservice.FileWriter;

import java.io.BufferedReader;
import java.io.File;
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
    static List<String> sufs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextActivityGo = (Button) findViewById(R.id.gonext);
        nextActivityGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChoosetextActivity.class);
                startActivity(intent);

            }
        });

      //  downloadAllUsers();
        downloadAdjSuf();
       }

//    private void downloadAllUsers(){
//        NetworkService.getInstance()
//                .getUsersJSONApi()
//                .getAllUsers()
//                .enqueue(new Callback<List<User>>() {
//                    @Override
//                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                        List<User> users = response.body();
//                        names = new ArrayList<>();
//                        for(User u: users)
//                            names.add(u.getName());
//
//
//                        ListView usersList = (ListView) findViewById(R.id.allUsersListView);
//
//                        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,
//                                android.R.layout.simple_list_item_1, names);
//                        usersList.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<User>> call, Throwable t) {
//                     //   textView.append("Error occurred while getting request!");
//                        t.printStackTrace();
//                    }
//
//                });
//    }

    private void downloadAdjSuf(){
        NetworkService.getInstance()
                .getSufJSONApi()
                .getAllAdjSuffix()
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        List<String> suffixes = response.body();
                        sufs = new ArrayList<>();
                        for(String s: suffixes)
                            sufs.add(s);


                        ListView usersList = (ListView) findViewById(R.id.allUsersListView);

                        ArrayAdapter<List<String>> adapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1, sufs);
                        usersList.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        //   textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });
    }

}