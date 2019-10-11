package com.khalej.helpmeAPP.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.khalej.helpmeAPP.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Shrot extends AppCompatActivity {
    TextView confirm;
    TextView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrot);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Nasser.otf", true);
        confirm=findViewById(R.id.confirm);
        String html="الهدف من التطبيق : \n" +
                "1-\tالتجول في بيئة إلكترونية سهلة تسمح للأسر المنتجة و تسمح للأعمال بالنمو و الازدهار ..\n" +
                "2-\tالمساعدة قدر المستطاع بالحفاظ على الوضع الاقتصادي للأسر المنتجة و مساعدتهم بعرض منتجاتهم لتصل لأكبر عدد من الناس ..\n" +
                "3-\tالمساهمة في تسهيل عملية البيع على التجار و الشراء بسهولة ..\n" +
                "\n" +
                "شروط البيع :\n" +
                "1-\tيجب أن يضم العقد طرفين وهما : البائع و المشتري ، فيشترط حصول الموافقة و القبول من الطرفين بالاتفاق و التراضي ..\n" +
                "2-\tيشترط أن يمتلك البائع الشيء المباع امتلاكاً كاملاً ، حتى يتم تسليمه للمشتري ..\n" +
                "3-\tيشترط أن يكون الشيء المباع حلالاً ، فلا يجوز التجارة بالمبطلات .. \n" +
                "4-\tيجب أن يكون الثمن معلوماً بين الطرفين ، يشترط أن يخلو الشيء المباع من الرهون ..\n" +
                "5-\tأن يكون البائع عاقلاً بالغاً ..\n" +
                "6-\tيشترط أن يمتلك البائع رخصة تجارية ..\n" +
                "\n" +
                "شروط الشراء: \n" +
                "1-\tيشترط أن تكون معلومات التسجيل صحيحة و دقيقة حتى تتمم عملية الشراء بنجاح ..\n" +
                "2-\tقراءة الوصف للمنتج تحت الصورة ..\n" +
                "3-\tبعض المنتجات لا تسترجع ولا تستبدل ، كالعطور ، و منتجات العناية و المكياج المفتوح و غيرها .. \n" +
                "\n";
        b= findViewById(R.id.b);
        b.setText(html);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shrot.this,MainActivity.class));
                finish();
            }
        });
    }
}
