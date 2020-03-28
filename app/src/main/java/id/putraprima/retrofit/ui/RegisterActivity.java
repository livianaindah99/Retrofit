package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputname,inputemail,inputpassword,txtpassword_confirmation;
    private String name, email, password, password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputname = findViewById(R.id.name);
        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        txtpassword_confirmation = findViewById(R.id.password_confirmation);
    }

    public void handleRegister(View view) {
        name = inputname.getText().toString();
        email = inputemail.getText().toString();
        password = inputpassword.getText().toString();
        password_confirmation = txtpassword_confirmation.getText().toString();

        register();
    }

    public void register(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<RegisterResponse> call = service.cobaregister(new RegisterRequest(name, email, password, password_confirmation));
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
}
