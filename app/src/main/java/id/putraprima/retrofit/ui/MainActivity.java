package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtname,txtversion;
    EditText email,password;
    private String emailnya,passwordnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        txtname = findViewById(R.id.mainTxtAppName);
        txtversion = findViewById(R.id.mainTxtAppVersion);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);

        txtname.setText(extras.getString("APP_KEY"));
        txtversion.setText(extras.getString("VER_KEY"));
    }

    public void handleRegisterMain(View view) {
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void handleLogin(View view) {
        emailnya = email.getText().toString();
        passwordnya = password.getText().toString();

        login();
    }

    private void login() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<LoginResponse> call = service.cobalogin(new LoginRequest(emailnya, passwordnya));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);

                intent.putExtra("TOKEN",response.body().token);
                intent.putExtra("TOKEN_TYPE",response.body().token_type);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Koneksi Ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
