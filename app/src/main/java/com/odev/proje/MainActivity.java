package com.odev.proje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout kly,ort,zor;
    TextView isim,b_kly,b_ort,b_zor;
    ImageView isimduz;
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataHelper=new DataHelper(this);
        kly=(RelativeLayout) findViewById(R.id.kly);
        ort=(RelativeLayout) findViewById(R.id.ort);
        zor=(RelativeLayout) findViewById(R.id.zor);
        isimduz=(ImageView) findViewById(R.id.kaydi_duzenle);
        isim= (TextView) findViewById(R.id.kullaniciadi);
        b_kly= (TextView) findViewById(R.id.b_kly);
        b_ort= (TextView) findViewById(R.id.b_ort);
        b_zor= (TextView) findViewById(R.id.b_zor);
        b_kly.setText(""+dataHelper.receiveDataInt("En Yüksek Kolay:",0));
        b_ort.setText(""+dataHelper.receiveDataInt("En Yüksek Orta:",0));
        b_zor.setText(""+dataHelper.receiveDataInt("En Yüksek Zor:",0));
        isimduz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alertDialog();
            }
        });
        kly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,KolayActivity.class));

            }
        });
        ort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OrtaActivity.class));

            }
        });
        zor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ZorActivity.class));

            }
        });

    }
    public void alertDialog()
    {
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.uyaripenceresi,null);
        final EditText name = (EditText) view.findViewById(R.id.name);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Kullanıcı Adınızı Girin:")
                .setView(view)
                .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String s= name.getText().toString();
                        dataHelper.saveDataString("İsim",s);
                        isim.setText(dataHelper.receiveDataString("İsim","Kullanıcı"));
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
