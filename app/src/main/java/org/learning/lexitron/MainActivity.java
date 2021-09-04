package org.learning.lexitron;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.learning.lexitron.model.User;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    String login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        Button findBtn = (Button) findViewById(R.id.findUserBtn);
        EditText userLoginInput = (EditText) findViewById(R.id.loginInput);


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
}