package com.odev.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OrtaActivity extends AppCompatActivity {
    DataHelper dataHelper;
    TextView sorularr,puann,isim_oyun,gecsayi;
    ImageButton dogru,yanlis,anasayfayagit;
    RelativeLayout gec;
    int gecc;
    Random r=new Random();
    int n;
    int points=0;
    int SKIP_NUMBER=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orta);
        dataHelper=new DataHelper(this);
        gecsayi=(TextView) findViewById(R.id.gecsayi);
        sorularr=(TextView) findViewById(R.id.sorular);
        isim_oyun=(TextView) findViewById(R.id.isim_oyun);
        puann=(TextView) findViewById(R.id.puan);
        dogru=(ImageButton) findViewById(R.id.dogru);
        yanlis=(ImageButton) findViewById(R.id.yanlis);
        isim_oyun.setText(dataHelper.receiveDataString("İsim","Kullanici"));
        anasayfayagit=(ImageButton) findViewById(R.id.anasayfayagit);
        gec=(RelativeLayout) findViewById(R.id.gec);
        anasayfayagit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(OrtaActivity.this,MainActivity.class));
                finish();
            }
        });
        gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));


        final String[]arrayQ={getString(R.string.o1),getString(R.string.o2),getString(R.string.o3),getString(R.string.o4),getString(R.string.o5),getString(R.string.o6),getString(R.string.o7),getString(R.string.o8),getString(R.string.o9),getString(R.string.o10),getString(R.string.o11),getString(R.string.o12),getString(R.string.o13),getString(R.string.o14),getString(R.string.o15),getString(R.string.o16),getString(R.string.o17),getString(R.string.o18),getString(R.string.o19),getString(R.string.o20),getString(R.string.o21),getString(R.string.o22),getString(R.string.o23),};
        final Boolean[]arrayA={true,true,false,true,true,false,true,false,false,false,false,false,true,false,true,true,false,true,false,true,false,true,true};
        final ArrayList<String> sorular=new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean>cevaplar= new ArrayList<Boolean>(Arrays.asList(arrayA));
        n=r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n)); //soruların karışık olmasını sağlar.
        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
                gecc=dataHelper.receiveDataInt("Geç",SKIP_NUMBER);
                if (dataHelper.receiveDataInt("Geç",SKIP_NUMBER)==0)
                {
                    Toast.makeText(OrtaActivity.this,"0 Geçme Hakkını Kaldı.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    gecc--;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    if (sorular.size()==0)
                    {
                        result();
                    }
                    else
                    {
                        n=r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                        dataHelper.saveDataInt("Geç",gecc);
                    }
                }
            }
        });
        dogru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevaplar.get(n))
                {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor:"+points);
                    if (sorular.size()==0)
                    {
                        result();
                    }
                    else
                    {
                        n=r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));

                    }
                }
                else
                {
                    result();
                }
            }
        });
        yanlis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cevaplar.get(n))
                {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor:"+points);
                    if (sorular.size()==0)
                    {
                        result();
                    }
                    else
                    {
                        n=r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));

                    }
                }
                else
                {
                    result();
                }
            }
        });


    }

    private void result() {
        dataHelper.saveDataInt("PUAN ORTA:",points);
        startActivity(new Intent(OrtaActivity.this,OrtaResultActivity.class));
        finish();

    }
}
