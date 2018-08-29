package br.com.igorbagliotti.school.data.remote;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.igorbagliotti.school.BuildConfig;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginRequest;
import br.com.igorbagliotti.school.data.remote.model.auth.LoginResponse;
import br.com.igorbagliotti.school.data.remote.model.student.Student;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface APIService {

    String ENDPOINT = "http://35.224.143.193/";

    @POST("auth")
    Observable<LoginResponse> autenticar(@Body LoginRequest loginRequest);

    @GET("student")
    Observable<List<LoginResponse>> getAllStudents();

    @POST("student")
    Observable<Student> saveStudent(@Body Student student);

    @PUT("student/{id}")
    Observable<Student> editStudent(@Path("id") String id, @Body Student student);

    @DELETE("student/{id}")
    Observable<Student> deleteStudent(@Path("id") String id);

    class Factory {

        public static APIService create(Context context) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(interceptor);
            }

            //Extra Headers
            builder.addNetworkInterceptor(chain -> {
                Request request = chain.request().newBuilder().addHeader("Authorization", "Basic aWdvcmJhZ0Bob3RtYWlsLmNvbTp0ZXN0ZQ==").build();
                return chain.proceed(request);
            });

            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);

            builder.addInterceptor(new UnauthorisedInterceptor(context));
            OkHttpClient client = builder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIService.ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}