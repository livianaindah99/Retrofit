package id.putraprima.retrofit.api.services;


import id.putraprima.retrofit.api.models.AppVersion;
import id.putraprima.retrofit.api.models.Data;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.Profile;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface{
    @GET("/")
    Call<AppVersion> getAppVersion();

    @POST("/api/auth/register")
    Call<RegisterResponse> cobaregister(@Body RegisterRequest registerRequest);

    @POST("/api/auth/login")
    Call<LoginResponse> cobalogin(@Body LoginRequest loginRequest);

    @GET("/api/auth/me")
    Call<Data<Profile>> tampilProfile(@Header("Authorization") String token);

}
