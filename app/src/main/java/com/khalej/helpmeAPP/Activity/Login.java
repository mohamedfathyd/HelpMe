package com.khalej.helpmeAPP.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.khalej.helpmeAPP.R;
import com.khalej.helpmeAPP.model.Apiclient_home;
import com.khalej.helpmeAPP.model.apiinterface_home;
import com.khalej.helpmeAPP.model.contact_userinfo;

import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    AppCompatTextView Naccount;
    AppCompatButton login,appCompatButtonbb;
    TextInputEditText textInputEditTextphone,textInputEditTextpassword;
    TextInputLayout textInputLayoutphone,textInputLayoutpassword;
    private List<contact_userinfo> contactList;
    private apiinterface_home apiinterface;
        private SharedPreferences sharedpref;
        private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    String lang;
    Switch swtch;

   Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = getSharedPreferences("Help Me", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
       lang=sharedpref.getString("language","").trim();
        if(lang.equals(null)){
            edt.putString("language","ar");
            lang="en";
            edt.apply();
        }
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_login);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Nasser.otf", true);

        swtch=findViewById(R.id.swtch);



    textInputLayoutphone=(TextInputLayout)findViewById(R.id.textInputLayoutphone);
        textInputLayoutpassword=(TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputEditTextphone=(TextInputEditText)findViewById(R.id.textInputEditTextphone);

        appCompatButtonbb=findViewById(R.id.appCompatButtonbb);
        appCompatButtonbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });




        if(sharedpref.getString("remember","").trim().equals("yes")){
            edt.putFloat("totalprice",0);
            edt.apply();
            startActivity(new Intent(Login.this,MainActivity.class));

            finish();
        }
        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(sharedpref.getString("language","").trim().equals("ar")){
                   edt.putString("language","en");
                   edt.apply();
                   startActivity(new Intent(Login.this,Login.class));
                   finish();
               }
               else
               {
                   edt.putString("language","ar");
                   edt.apply();
                   startActivity(new Intent(Login.this,Login.class));
                   finish();
               }
            }

        });
        textInputEditTextpassword=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        Naccount=(AppCompatTextView)findViewById(R.id.textViewLinkRegister);
        Naccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
        login=(AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
            }
        });
    }
    public void fetchInfo(){
        progressDialog = ProgressDialog.show(Login.this,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();
        String phone=textInputEditTextphone.getText().toString();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_userinfo>> call= apiinterface.getcontacts_login(phone,
                textInputEditTextpassword.getText().toString());
        call.enqueue(new Callback<List<contact_userinfo>>() {
            @Override
            public void onResponse(Call<List<contact_userinfo>> call, Response<List<contact_userinfo>> response) {
                if(response.isSuccessful()){

                    contactList = response.body();
                    try {
                        progressDialog.dismiss();
                        edt.putInt("id",contactList.get(0).getId());
                        edt.putString("name",contactList.get(0).getName());
                        edt.putString("phone",contactList.get(0).getPhone());
                        edt.putString("address",contactList.get(0).getmaddress());
                        edt.putString("password",contactList.get(0).getPassword());
                        edt.putString("points",contactList.get(0).getPoints());
                        edt.putString("country",contactList.get(0).getCountry());
                        edt.putString("date", String.valueOf(contactList.get(0).getAge()));
                        edt.putFloat("totalprice",0);
                        edt.putString("remember","yes");
                        edt.apply();
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Login.this);
                        dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                        dlgAlert.setTitle("انا اساعدك");
                        dlgAlert.setPositiveButton("حسنا", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        startActivity(new Intent(Login.this,Shrot.class));}
                    catch (Exception e){
                        Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري ",Toast.LENGTH_LONG).show();

                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<contact_userinfo>> call, Throwable t) {
                Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري",Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });
    }
}
