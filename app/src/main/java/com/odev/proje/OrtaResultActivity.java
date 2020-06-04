package com.odev.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrtaResultActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView olanpuan,eniyi,tv;
    Button anasay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orta_result);
        dataHelper=new DataHelper(this);
        olanpuan=(TextView) findViewById(R.id.yapilanpuan);
        eniyi=(TextView) findViewById(R.id.eniyiskor);
        tv=(TextView) findViewById(R.id.tv_user);
        anasay=(Button) findViewById(R.id.home);
        int puan= dataHelper.receiveDataInt("PUAN ORTA:",0);
        int eni= dataHelper.receiveDataInt("En Yüksek Orta:",0);
        olanpuan=(TextView) findViewById(R.id.yapilanpuan);
        eniyi=(TextView) findViewById(R.id.eniyiskor);
        tv.setText("KAYBETTİNİZ.. :( "+dataHelper.receiveDataString("İsim","Kullanici"));
        olanpuan.setText(""+puan);
        if (puan>eni)
        {
            eni=puan;
            dataHelper.saveDataInt("En Yüksek Orta:",eni);

        }
        eniyi.setText(""+eni);
        anasay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrtaResultActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}
