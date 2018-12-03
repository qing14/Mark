package com.bwie.asus.mark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tv_context;
    private EditText et_input;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_context = findViewById(R.id.tv_content);
        et_input = findViewById(R.id.et_input);
        img = findViewById(R.id.img);

        findViewById(R.id.btnSan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,CaptureActivity.class),0);
            }
        });
        findViewById(R.id.btn_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et_input.getText().toString();
                if (s.equals("")){
                    Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_LONG).show();
                }else {
                    try{
                        Bitmap bitmap = EncodingUtils.createQRCode(s, 500, 500, null);
                        img.setImageBitmap(bitmap);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_OK){
            String result = data.getExtras().getString("result");
            Toast.makeText(MainActivity.this,"mmmm"+result,Toast.LENGTH_LONG).show();
            Log.d("Main",result);
            tv_context.setText(result);

        }
    }
}
