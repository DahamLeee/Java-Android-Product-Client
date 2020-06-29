package com.daham.product;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daham.product.dto.ProductDto;
import com.daham.product.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Call<List<ProductDto>> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);
        call = retrofitService.searchAll();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SUSU", "버튼 눌렸음");
                fetch();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void fetch(){
        call.enqueue(new Callback<List<ProductDto>>() {
            int size = 0;
            @Override
            public void onResponse(Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
                textView = findViewById(R.id.textView);
                textView.setText(response.body().toString());
            }
            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
                Log.d("SUSU", t.getMessage());
            }
        });
    }
}