package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.Data;
import id.putraprima.retrofit.api.models.Profile;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView txtname,txtemail;
    String token,tk_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtname = findViewById(R.id.namanya);
        txtemail = findViewById(R.id.emailnya);

        Bundle extras = getIntent().getExtras();
        token = extras.getString("TOKEN");
        tk_type = extras.getString("TOKEN_TYPE");

        lihatProfile();
    }

    public void lihatProfile(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Data<Profile>> call = service.tampilProfile(tk_type+" "+token);
        call.enqueue(new Callback<Data<Profile>>() {
            @Override
            public void onResponse(Call<Data<Profile>> call, Response<Data<Profile>> response) {
                if (response.body()!=null){
                    Toast.makeText(ProfileActivity.this, "Halo Everyone!", Toast.LENGTH_SHORT).show();
                    txtname.setText(response.body().data.getName());
                    txtemail.setText(response.body().data.getEmail());
                }else{
                    Toast.makeText(ProfileActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Data<Profile>> call, Throwable t) {

            }
        });
    }
}
