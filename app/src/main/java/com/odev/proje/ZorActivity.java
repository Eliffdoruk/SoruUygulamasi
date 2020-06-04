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

public class ZorActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_zor);
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
                startActivity(new Intent(ZorActivity.this,MainActivity.class));
                finish();
            }
        });
        gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));

        final String[]arrayQ={getString(R.string.z1),getString(R.string.z2),getString(R.string.z3),getString(R.string.z4),getString(R.string.z5),getString(R.string.z6),getString(R.string.z7),getString(R.string.z8),getString(R.string.z9),getString(R.string.z10),getString(R.string.z11),getString(R.string.z12),getString(R.string.z13),getString(R.string.z14),getString(R.string.z15),getString(R.string.z16),getString(R.string.z17),getString(R.string.z18),getString(R.string.z19),getString(R.string.z20),getString(R.string.z21),getString(R.string.z22),getString(R.string.z23)};
        final Boolean[]arrayA={true,false,true,false,false,false,true,true,false,false,false,false,true,true,true,true,false,true,false,true,false,true,false};
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
                    Toast.makeText(ZorActivity.this,"0 Geçme Hakkını Kaldı.", Toast.LENGTH_SHORT).show();

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
        dataHelper.saveDataInt("PUAN ZOR:",points);
        startActivity(new Intent(ZorActivity.this,ZorResultActivity.class));
        finish();

    }
}
