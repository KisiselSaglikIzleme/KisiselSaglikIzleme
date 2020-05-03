HEALTHFRIENDLY DEMO-1 ANDROİD STUDİO KODLARI VE YORUMLANMASI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------------------------


MainActivity.java KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
package com.example.healthfriendly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button getbtnWelcomeLogin,getbtnWelcomeRegister;
    public void init() {
        getbtnWelcomeLogin=(Button)findViewById(R.id.btnWelcomeLogin);
        getbtnWelcomeRegister=(Button)findViewById(R.id.btnWelcomeRegister);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getbtnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intentLogin);
                finish();
            }
        });
        getbtnWelcomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                finish();
            }
        });

    }
}
//BU KODLARIN ÇALIŞTIRILMASI İÇİN UYGULAMANIN BAŞLATILMASI GEREK.

**********************************************************************************************************************************************************************

RegisterActivity.java KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
package com.example.healthfriendly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;				
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;			
import com.google.firebase.auth.FirebaseAuth;			

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    private Toolbar actionbarRegister;	
    private EditText txtUsername, txtEmail, txtPassword;		
    private Button btnRegister;						
    public void init() {

        actionbarRegister=(Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        txtUsername = (EditText) findViewById(R.id.txtUsernameRegister);
        txtEmail=(EditText)findViewById(R.id.txtEmailRegister);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        btnRegister=(Button)findViewById(R.id.btnRegister);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }


    private void createNewAccount() {
        String username= txtUsername.getText().toString();
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent LoginIntent =new Intent(RegisterActivity.this, Login.class);
                    startActivity(LoginIntent);
                    finish();
                }else;
            }
        });


    }
}

//BU KODUN ÇALIŞTIRILMASI İÇİN GİRİŞ EKRANINDAN  KAYIT OL BUTONUNA TIKLANMASI GEREKMEKTEDİR.
//KODUN ÇALIŞMA PRENSİBİ İSE VERİTABANINA KULLANICI EKLEME YAPAR.
//BUNUN İÇİN VERİTABANI GETİRİLİR.
//EDİTTEXTLERE YAZILAN EMAİL ŞİFRE KULLANICI ADI BAŞARILI ŞEKİLDE KAYDI GERÇEKLEŞTİRİLİRSE SİZİ LOGİN(GİRİŞ YAP SAYFASI) SAYFASINA GÖNDERİR. 



**********************************************************************************************************************************************************************

Login.java KODALRI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
package com.example.healthfriendly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Toolbar actionbarLogin;
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    public void init(){
        actionbarLogin = (Toolbar)findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        txtEmail=(EditText)findViewById(R.id.txtEmailLogin);
        txtPassword=(EditText)findViewById(R.id.txtPasswordLogin);
        btnLogin=(Button)findViewById(R.id.btnLogin);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email=txtEmail.getText().toString();
        String password= txtPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "email alanı boş olamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "şifre alanı boş olamaz", Toast.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "GİRİŞ BAŞARILI", Toast.LENGTH_SHORT).show();
                        Intent anaIntent = new Intent(Login.this, anaekran.class);
                        startActivity(anaIntent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "giriş başarısız", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}


//BU KODUN ÇALIŞTIRILMASI İÇİN GİRİŞ EKRANINDAN LOGİN BUTONUNA TIKLANMASI GEREKMEKTEDİR.
//KODUN ÇALIŞMA PRENSİBİ LOGİN EKLRANINA GELDİĞİNİZDE PROGRAM AKTİF KULLANICILARI VERİTABANINDAN GETİRMESİNİ İSTER.
//DAHA SONRA EDİTTEXTLERE YAZILAN EMAİL VE ŞİFRE VERİ TABANINDAN KONTROLÜ SAĞLANIR.




**********************************************************************************************************************************************************************


anaekran.java KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
package com.example.healthfriendly;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class anaekran extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){
        auth = FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anaekran);
        init();
    }

    @Override
    protected void onStart() {
        if(currentUser == null){
            Intent welcomeIntent = new Intent(anaekran.this, MainActivity.class);
            startActivity(welcomeIntent);
            finish();
        }

        super.onStart();
    }


}
//KODUN ÇALIŞMA PRENSİBİ LOGİN EKRANINDAN DOĞRU BAŞARILI BİR GİRİŞ YAPILDIĞINDA ARTIK SİZİ ANA MENÜYE GETİRMELİDİR.
//BURADA YAPILACAK GÜNCELLEMELER SONUCUNDA ARTIK TASARIMDA OLAN BÖLÜMLERE GEÇİŞ SAĞLANICAK.



**********************************************************************************************************************************************************************


actionba_app.xml KODALRI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.Toolbar xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/pnlActionbar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/colorPrimary"
    android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"

    />
//ACİTONBAR IMIZIN TANIMLAMASI YAPILDI HER SAYFADA KULLANILIYOR.


**********************************************************************************************************************************************************************



activity_anaekran.xml KODALRI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".anaekran">


    <include layout="@layout/actionbar_app" android:id="@+id/actionBar" ></include>

    <Button
        android:id="@+id/hedef"
        android:layout_below="@+id/actionBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hedef"
        />
    <Button
        android:id="@+id/Günlük"
        android:layout_below="@+id/hedef"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/gunluk"

        ></Button>

    <Button
        android:id="@+id/Uyku"
        android:layout_below="@+id/Günlük"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text=""></Button>
</RelativeLayout>

//ANAEKRANDAKİ BUTONLARIN KODLARI MEVCUTTUR ŞİMDİLİK SADECE TASARIMDADIR.DEMO 2 DE HEPSİ KULLANIŞLI OLACAKTIR.

**********************************************************************************************************************************************************************


activity_main.xml  KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:padding="20dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/app_name"
        android:textSize="35sp"
        android:textColor="@color/colorPrimaryDark"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="35dp"
        ></TextView>

    <Button
        android:id="@+id/btnWelcomeRegister"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/create_new_account"
        android:background="@android:color/darker_gray"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="50dp"
        ></Button>

<Button
    android:id="@+id/btnWelcomeLogin"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="@string/i_have_account"
    android:background="@android:color/darker_gray"
    android:layout_above="@id/btnWelcomeRegister"
    android:layout_marginBottom="25dp"



    ></Button>




</RelativeLayout>

// BU KOD UYGULAMANIN İLK AÇILDIĞINDAKİ GİRİŞ EKRANIDIR.ÇALIŞMA PRENSİBİ UYGULAMANIN KURULDUKTAN SONRA ÇALIŞTIĞINDA KARŞIMIZA ÇIKAR

**********************************************************************************************************************************************************************


activty_login.xml KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Login">
<include layout="@layout/actionbar_app" android:id="@+id/actionbarLogin"></include>

    <TextView
        android:id="@+id/txtinfo1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/login_with_your_account"
        android:textColor="@android:color/black"
        android:layout_below="@+id/actionbarLogin"
        android:layout_marginTop="20dp"
        android:textSize="20sp"


        />


    <TextView
        android:id="@+id/txtinfo2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/email"
        android:layout_below="@+id/txtinfo1"
        android:layout_marginTop="20dp"
        android:layout_marginStart="20dp"
        android:layout_marginLeft="20dp"
        android:textSize="20sp"
        ></TextView>


    <EditText
        android:id="@+id/txtEmailLogin"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtinfo2"
        android:hint="@string/email"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:inputType="textEmailAddress"
        android:importantForAutofill="no"></EditText>

    <EditText
        android:id="@+id/txtPasswordLogin"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtEmailLogin"
        android:hint="@string/Parola"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:inputType="textPassword"
        android:autofillHints="" />

    <Button
        android:id="@+id/btnLogin"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/login"
        android:layout_alignParentBottom="true"
        android:layout_marginEnd="10dp"
        android:layout_marginStart="10dp"
        android:background="@color/colorPrimary"

        ></Button>
</RelativeLayout>

//LOGİN PANELİNDEKİ KULLANICI GİRİŞİNİN SAYFA TASARIM KODALRIDIR.
//BUTONLARI IDLERİ TANIMLANMIŞTIR.
//BU KODLŞARIN ÇALIŞMASI İÇİN LOGİN SAYFASINA GELİNMELİDİR.
//İLGİLİ SAYFANIN JAVA KODLARIYLA ÇALIŞMASINI SAĞLAMAKTADIR.
**********************************************************************************************************************************************************************


activity_register.xml KODALRI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Login">
    <include layout="@layout/actionbar_app" android:id="@+id/actionbarRegister"></include>

    <TextView
        android:id="@+id/txtinfo1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/create_new_account"
        android:textColor="@android:color/black"
        android:layout_below="@+id/actionbarRegister"
        android:layout_marginTop="20dp"
        android:textSize="20sp"


        />


            <TextView
             android:id="@+id/txtinfo2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/username"
            android:layout_below="@+id/txtinfo1"
            android:layout_marginTop="20dp"
            android:layout_marginStart="20dp"
            android:layout_marginLeft="20dp"
            android:textSize="20sp"
            ></TextView>


            <EditText
                android:id="@+id/txtUsernameRegister"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_below="@+id/txtinfo2"
                android:hint="@string/username"
                android:layout_marginTop="10dp"
                android:layout_marginStart="10dp"
                android:layout_marginEnd="10dp"
                android:inputType="text"
                android:importantForAutofill="no"></EditText>

    <EditText
        android:id="@+id/txtEmailRegister"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtUsernameRegister"
        android:hint="@string/email"
        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:inputType="textEmailAddress"
        android:autofillHints="" />
    <EditText

        android:id="@+id/txtPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtEmailRegister"

        android:hint="@string/Parola"

        android:layout_marginTop="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:inputType="textPassword"
        android:autofillHints="" />

    <EditText
        android:id="@+id/txtdate"
        android:hint="@string/DoğumTarihi"
        android:layout_width="133dp"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtPassword"
        android:layout_marginTop="56dp"

        android:inputType="date"
        android:text="@string/DoğumTarihi"

        android:autofillHints="" />

    <EditText
        android:id="@+id/txtBoy"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/txtdate"
        android:layout_margin="10dp"
        android:layout_marginTop="35dp"
        android:layout_marginBottom="20sp"
        android:autofillHints=""
        android:hint="@string/Boy"
        android:inputType="numberDecimal"></EditText>

    <EditText
        android:id="@+id/txtKilo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtBoy"
        android:layout_margin="10dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:hint="@string/Kilo"
        android:inputType="numberDecimal"
        android:importantForAutofill="no"></EditText>

    <Button
        android:id="@+id/btnRegister"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/hesap_olustur"
        android:layout_alignParentBottom="true"
        android:layout_marginEnd="10dp"
        android:layout_marginStart="10dp"
        android:background="@color/colorPrimary"

        ></Button>


</RelativeLayout>
//YUKARIDAKİ KOD REGİSTER.JAVA NIN XML KODALRIDIR.
//KAYIT OLMA SAYFASININ TASARIM KODALRIDIR.
//İLGİLİ SAYFAMIN JAVA KODLARIYLA BİRLİKTE ÇALIŞMASINI SAĞLAMAKTADIR.



**********************************************************************************************************************************************************************


ADIM SAYACI XML KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------------------------------


<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".adimsayaci">

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="1dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/topbar" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="29dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="Adım Ölçer"
        android:textColor="@android:color/background_light"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="@+id/imageView2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.222"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/imageView2"
        app:layout_constraintVertical_bias="0.79" />

    <android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/cardView"
        android:layout_width="match_parent"
        android:layout_height="140dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:cardBackgroundColor="@color/colorAccent1"
        app:layout_constraintBottom_toTopOf="@+id/navigation"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView2"
        app:layout_constraintVertical_bias="0.0">


        <android.support.constraint.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ImageView
                android:id="@+id/imageView3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="8dp"
                android:layout_marginLeft="8dp"
                android:layout_marginTop="8dp"
                android:layout_marginEnd="8dp"
                android:layout_marginRight="8dp"
                android:layout_marginBottom="8dp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:srcCompat="@drawable/ellipse" />

            <TextView
                android:id="@+id/textView3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="8dp"
                android:text="Hedef : 6000"
                android:textColor="@android:color/black"
                app:layout_constraintBottom_toBottomOf="@+id/imageView3"
                app:layout_constraintEnd_toEndOf="@+id/imageView3"
                app:layout_constraintHorizontal_bias="0.51"
                app:layout_constraintStart_toStartOf="@+id/imageView3"
                app:layout_constraintTop_toTopOf="@+id/imageView3"
                app:layout_constraintVertical_bias="0.343" />

            <TextView
                android:id="@+id/textView4"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="8dp"
                android:layout_marginLeft="8dp"
                android:layout_marginTop="8dp"
                android:layout_marginEnd="8dp"
                android:layout_marginRight="8dp"
                android:layout_marginBottom="8dp"
                android:text="11 adım"
                android:textColor="@android:color/black"
                app:layout_constraintBottom_toBottomOf="@+id/imageView3"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/textView3" />
        </android.support.constraint.ConstraintLayout>

    </android.support.v7.widget.CardView>


    <android.support.design.widget.BottomNavigationView
        android:id="@+id/navigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:background="@color/colorAccent1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/navigation">

    </android.support.design.widget.BottomNavigationView>

    <ImageView
        android:id="@+id/imageView5"
        android:layout_width="203dp"
        android:layout_height="27dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintBottom_toTopOf="@+id/navigation"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.477"
        app:layout_constraintStart_toStartOf="@+id/cardView"
        app:layout_constraintTop_toBottomOf="@+id/cardView"
        app:layout_constraintVertical_bias="0.024"
        app:srcCompat="@drawable/group" />

    <ImageView
        android:id="@+id/imageView7"
        android:layout_width="22dp"
        android:layout_height="23dp"
        app:layout_constraintBottom_toBottomOf="@+id/imageView5"
        app:layout_constraintEnd_toEndOf="@+id/imageView5"
        app:layout_constraintHorizontal_bias="0.077"
        app:layout_constraintStart_toStartOf="@+id/imageView5"
        app:layout_constraintTop_toTopOf="@+id/imageView5"
        app:layout_constraintVertical_bias="0.354"
        app:srcCompat="@drawable/button" />

    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="88dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toTopOf="@+id/navigation"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView5"
        app:layout_constraintVertical_bias="1.0">

        <ImageButton
            android:id="@+id/imageButton"
            android:layout_width="wrap_content"
            android:layout_height="73dp"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:srcCompat="@drawable/harita" />

        <ImageButton
            android:id="@+id/imageButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.211"
            app:layout_constraintStart_toEndOf="@+id/imageButton"
            app:layout_constraintTop_toTopOf="parent"
            app:srcCompat="@drawable/kalp" />

        <ImageButton
            android:id="@+id/imageButton3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="1.0"
            app:layout_constraintStart_toEndOf="@+id/imageButton2"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.0"
            app:srcCompat="@drawable/stop" />
    </android.support.constraint.ConstraintLayout>

</android.support.constraint.ConstraintLayout>


//YUKARIDAKİ KOD BLOĞU ADIM SAYACI SAYFASININ TASARIM KODLARIDIR.
**********************************************************************************************************************************************************************



ARAMA GIDA XML KODLARI
-------------------------------------------------------------------------------------------------------------------------------------------

<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".aramagida">

    <ImageView
        android:id="@+id/imageView6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="0dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0"
        app:srcCompat="@drawable/aramagida" />

    <ImageView
        android:id="@+id/imageView9"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="0dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView6"
        app:layout_constraintVertical_bias="0.0"
        app:srcCompat="@drawable/rectangle" />

    <Button
        android:id="@+id/button"
        android:layout_width="147dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:shadowRadius="100dp"
        android:text="Benim Gıdalar"
        app:layout_constraintBottom_toBottomOf="@+id/imageView9"
        app:layout_constraintEnd_toStartOf="@+id/button2"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/imageView9"
        app:layout_constraintVertical_bias="0.52" />

    <Button
        android:id="@+id/button2"
        android:layout_width="147dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:shadowRadius="100dp"
        android:text="Yemekler"
        app:layout_constraintBottom_toBottomOf="@+id/imageView9"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.835"
        app:layout_constraintStart_toStartOf="@+id/imageView9"
        app:layout_constraintTop_toTopOf="@+id/imageView9"
        app:layout_constraintVertical_bias="0.52" />

    <ImageView
        android:id="@+id/imageButton4"
        android:layout_width="wrap_content"
        android:layout_height="362dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/imageView9"
        app:srcCompat="@drawable/kedi" />

    <TextView
        android:id="@+id/textView8"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:layout_marginBottom="8dp"
        android:text="Hiçbir yeni eklenen gıdalar yok"
        android:textColor="@android:color/darker_gray"
        android:textSize="18sp"
        app:layout_constraintBottom_toTopOf="@+id/navigation2"
        app:layout_constraintEnd_toEndOf="@+id/imageButton4"
        app:layout_constraintStart_toStartOf="@+id/imageButton4"
        app:layout_constraintTop_toTopOf="@+id/imageButton4"
        app:layout_constraintVertical_bias="0.813" />

    <android.support.design.widget.BottomNavigationView
        android:id="@+id/navigation2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:background="@color/colorAccent1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/navigation2">

    </android.support.design.widget.BottomNavigationView>

</android.support.constraint.ConstraintLayout>

//YUKARIDAKİ KOD BLOĞU TASARIMDA OLAN GIDA ARAMAA SAYFASININ  XML KODLARIDIR.
**********************************************************************************************************************************************************************


GENEL BAKIŞ XML KODLARI
--------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".genelbakis">

    <ImageView
        android:id="@+id/imageView10"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0"
        app:srcCompat="@drawable/genelbakistop" />

    <TextView
        android:id="@+id/textView9"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="Genel Bakış"
        android:textColor="@android:color/background_light"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="@+id/imageView10"
        app:layout_constraintEnd_toEndOf="@+id/imageView10"
        app:layout_constraintHorizontal_bias="0.186"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/imageView10"
        app:layout_constraintVertical_bias="0.728" />

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </ScrollView>

    <android.support.constraint.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="482dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView10"
        app:layout_constraintVertical_bias="0.533">

        <ProgressBar
            android:id="@+id/progressBar2"
            style="?android:attr/progressBarStyleHorizontal"
            android:layout_width="115dp"
            android:layout_height="18dp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView13"
            app:layout_constraintEnd_toEndOf="@+id/imageView13"
            app:layout_constraintHorizontal_bias="0.356"
            app:layout_constraintStart_toStartOf="@+id/imageView13"
            app:layout_constraintTop_toTopOf="@+id/imageView13"
            app:layout_constraintVertical_bias="0.811" />

        <ProgressBar
            android:id="@+id/progressBar3"
            style="?android:attr/progressBarStyleHorizontal"
            android:layout_width="122dp"
            android:layout_height="18dp"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView14"
            app:layout_constraintEnd_toEndOf="@+id/imageView14"
            app:layout_constraintHorizontal_bias="0.344"
            app:layout_constraintStart_toStartOf="@+id/imageView14"
            app:layout_constraintTop_toBottomOf="@+id/textView14"
            app:layout_constraintVertical_bias="0.0" />

        <TextView
            android:id="@+id/textView14"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            android:text="0 Kcal"
            android:textColor="@android:color/black"
            android:textSize="20sp"
            app:layout_constraintBottom_toTopOf="@+id/imageView15"
            app:layout_constraintEnd_toEndOf="@+id/imageView14"
            app:layout_constraintHorizontal_bias="0.25"
            app:layout_constraintStart_toStartOf="@+id/imageView14"
            app:layout_constraintTop_toTopOf="@+id/imageView14"
            app:layout_constraintVertical_bias="0.153" />

        <TextView
            android:id="@+id/textView11"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:text="65 KG"
            android:textColor="@android:color/black"
            android:textSize="20sp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView11"
            app:layout_constraintEnd_toEndOf="@+id/imageView11"
            app:layout_constraintHorizontal_bias="0.095"
            app:layout_constraintStart_toEndOf="@+id/imageView16"
            app:layout_constraintTop_toTopOf="@+id/imageView11"
            app:layout_constraintVertical_bias="0.455" />

        <TextView
            android:id="@+id/textView13"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            android:text="0 Kcal"
            android:textColor="@android:color/black"
            android:textSize="20sp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView13"
            app:layout_constraintEnd_toEndOf="@+id/imageView13"
            app:layout_constraintHorizontal_bias="0.339"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imageView12"
            app:layout_constraintVertical_bias="0.307" />

        <ImageView
            android:id="@+id/imageView11"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:srcCompat="@drawable/arka" />

        <ImageView
            android:id="@+id/imageView16"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="@+id/imageView11"
            app:layout_constraintEnd_toEndOf="@+id/imageView11"
            app:layout_constraintHorizontal_bias="0.077"
            app:layout_constraintStart_toStartOf="@+id/imageView11"
            app:layout_constraintTop_toTopOf="@+id/imageView11"
            app:layout_constraintVertical_bias="0.458"
            app:srcCompat="@drawable/tarti" />

        <ImageView
            android:id="@+id/imageView12"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toTopOf="@+id/imageView13"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imageView11"
            app:srcCompat="@drawable/arka" />

        <ImageView
            android:id="@+id/imageView17"
            android:layout_width="35dp"
            android:layout_height="33dp"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toTopOf="@+id/imageView13"
            app:layout_constraintEnd_toEndOf="@+id/imageView12"
            app:layout_constraintHorizontal_bias="0.106"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="@+id/imageView12"
            app:srcCompat="@drawable/sise" />

        <ImageView
            android:id="@+id/imageView13"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.555"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imageView12"
            app:srcCompat="@drawable/arka" />

        <ImageView
            android:id="@+id/imageView18"
            android:layout_width="37dp"
            android:layout_height="42dp"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toTopOf="@+id/imageView15"
            app:layout_constraintEnd_toEndOf="@+id/imageView14"
            app:layout_constraintHorizontal_bias="0.06"
            app:layout_constraintStart_toStartOf="@+id/imageView14"
            app:layout_constraintTop_toTopOf="@+id/imageView14"
            app:layout_constraintVertical_bias="0.405"
            app:srcCompat="@drawable/kalp" />

        <ImageView
            android:id="@+id/imageView14"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imageView13"
            app:srcCompat="@drawable/arka" />

        <ImageView
            android:id="@+id/imageView19"
            android:layout_width="45dp"
            android:layout_height="48dp"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="@+id/imageView15"
            app:layout_constraintHorizontal_bias="0.031"
            app:layout_constraintStart_toStartOf="@+id/imageView15"
            app:layout_constraintTop_toTopOf="@+id/imageView15"
            app:layout_constraintVertical_bias="0.4"
            app:srcCompat="@drawable/stop" />

        <ImageView
            android:id="@+id/imageView15"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.555"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imageView14"
            app:layout_constraintVertical_bias="0.0"
            app:srcCompat="@drawable/arka" />

        <ImageView
            android:id="@+id/imageView20"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="@+id/imageView13"
            app:layout_constraintEnd_toEndOf="@+id/imageView13"
            app:layout_constraintHorizontal_bias="0.077"
            app:layout_constraintStart_toStartOf="@+id/imageView13"
            app:layout_constraintTop_toTopOf="@+id/imageView13"
            app:srcCompat="@drawable/catal" />

        <TextView
            android:id="@+id/textView12"
            android:layout_width="74dp"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:text="0 Litre"
            android:textSize="20sp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView12"
            app:layout_constraintEnd_toEndOf="@+id/imageView12"
            app:layout_constraintHorizontal_bias="0.069"
            app:layout_constraintStart_toEndOf="@+id/imageView17"
            app:layout_constraintTop_toTopOf="@+id/imageView12"
            app:layout_constraintVertical_bias="0.2" />

        <TextView
            android:id="@+id/textView15"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:text="Kayıtlı ölçüm yok"
            android:textColor="@android:color/black"
            android:textSize="20sp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView15"
            app:layout_constraintEnd_toEndOf="@+id/imageView15"
            app:layout_constraintHorizontal_bias="0.28"
            app:layout_constraintStart_toStartOf="@+id/imageView19"
            app:layout_constraintTop_toTopOf="@+id/imageView15" />
        

        <ProgressBar
            android:id="@+id/progressBar"
            style="?android:attr/progressBarStyleHorizontal"
            android:layout_width="106dp"
            android:layout_height="18dp"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="@+id/imageView12"
            app:layout_constraintEnd_toEndOf="@+id/imageView12"
            app:layout_constraintHorizontal_bias="0.307"
            app:layout_constraintStart_toStartOf="@+id/imageView12"
            app:layout_constraintTop_toBottomOf="@+id/textView12"
            app:layout_constraintVertical_bias="0.0" />

    </android.support.constraint.ConstraintLayout>
</android.support.constraint.ConstraintLayout>

//YUKARIDAKİ KOD BLOĞU TASARIM AŞAMASINDA OLAN GENEL BAKIŞ XML KODLARIDIR.
************************************************************************************************************************************************************************


KALP NABZI XML KODLARI
------------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="kalpnabzi">


    <ImageView
        android:id="@+id/imageView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/topbar" />

    <TextView
        android:id="@+id/textView6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        android:text="000 bpm"
        android:textColor="@android:color/darker_gray"
        android:textSize="18sp"
        app:layout_constraintBottom_toTopOf="@+id/imageView8"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView4" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="60dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="Kalp Hızı (Nabız)"
        android:textColor="@android:color/background_light"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="@+id/imageView4"
        app:layout_constraintHorizontal_bias="0.18"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/imageView4" />

    <android.support.design.widget.BottomNavigationView
        android:id="@+id/navigation1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:background="@color/colorAccent1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/navigation1">

    </android.support.design.widget.BottomNavigationView>

    <ImageView
        android:id="@+id/imageView8"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toTopOf="@+id/navigation1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.502"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView4"
        app:layout_constraintVertical_bias="0.433"
        app:srcCompat="@drawable/nabiz" />

    <TextView
        android:id="@+id/textView7"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        android:text="Ölçümü başlatmak için dokunun"
        android:textColor="@android:color/darker_gray"
        android:textSize="18sp"
        app:layout_constraintBottom_toTopOf="@+id/navigation1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView8"
        app:layout_constraintVertical_bias="0.821" />

</android.support.constraint.ConstraintLayout>

//YUKARIDAKİ KOD BLOĞU TASARIM AŞAMASINDA OLAN KALP NBAIZ SAYFASININ XML KODLARIDIR.
************************************************************************************************************************************************************************



MENÜ KISMI XML KODLARI
------------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/menum">

    </android.support.design.widget.NavigationView>

</android.support.v4.widget.DrawerLayout>

//YUKARIDAKİ KOD BLOĞU TASARIMDA OLAN MENÜ SAYFASININ XML KODLARIDIR.
***********************************************************************************************************************************************************************



SAĞLIK HESAPLA XML KODLARI
---------------------------------------------------------------------------------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".saglikhesapla">

    <ImageView
        android:id="@+id/imageView21"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0"
        app:srcCompat="@drawable/saglikhesap" />

    <ImageView
        android:id="@+id/imageView22"
        android:layout_width="365dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView21"
        app:srcCompat="@drawable/arka" />

    <ImageView
        android:id="@+id/imageView23"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="12dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView22"
        app:srcCompat="@drawable/arka" />

    <ImageView
        android:id="@+id/imageView24"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView23"
        app:srcCompat="@drawable/arka" />

    <ImageView
        android:id="@+id/imageView25"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView24"
        app:srcCompat="@drawable/arka" />

    <ImageView
        android:id="@+id/imageView26"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="24dp"
        android:layout_marginLeft="24dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.055"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView25"
        app:layout_constraintVertical_bias="0.0"
        app:srcCompat="@drawable/arka" />

    <TextView
        android:id="@+id/textView10"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="60dp"
        android:text="Vücut Kitle İndeksi (VKİ)"
        android:textColor="@android:color/black"
        android:textSize="18sp"
        app:layout_constraintBottom_toTopOf="@+id/imageView23"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.146"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/imageView22"
        app:layout_constraintVertical_bias="0.0" />

    <TextView
        android:id="@+id/textView16"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        android:text="23,57"
        android:textColor="@android:color/black"
        android:textSize="18sp"
        app:layout_constraintBottom_toTopOf="@+id/imageView23"
        app:layout_constraintEnd_toEndOf="@+id/imageView22"
        app:layout_constraintHorizontal_bias="0.847"
        app:layout_constraintStart_toEndOf="@+id/textView10"
        app:layout_constraintTop_toTopOf="@+id/imageView22"
        app:layout_constraintVertical_bias="0.133" />

    <TextView
        android:id="@+id/textView17"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="76,1 KG"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toBottomOf="@+id/imageView23"
        app:layout_constraintEnd_toEndOf="@+id/imageView23"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toEndOf="@+id/textView18"
        app:layout_constraintTop_toTopOf="@+id/imageView23"
        app:layout_constraintVertical_bias="0.147" />

    <TextView
        android:id="@+id/textView18"
        android:layout_width="wrap_content"
        android:layout_height="27dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:text="İdeal Vücut Ağırlığıı"
        android:textColor="@android:color/black"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="@+id/imageView23"
        app:layout_constraintStart_toStartOf="@+id/imageView23"
        app:layout_constraintTop_toTopOf="@+id/imageView23"
        app:layout_constraintVertical_bias="0.144" />

    <TextView
        android:id="@+id/textView19"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        android:text="Tavsiye  Edilen Günlük Alım Miktarı"
        android:textColor="@android:color/black"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="@+id/imageView24"
        app:layout_constraintEnd_toStartOf="@+id/textView20"
        app:layout_constraintHorizontal_bias="0.005"
        app:layout_constraintStart_toStartOf="@+id/imageView24"
        app:layout_constraintTop_toBottomOf="@+id/textView25"
        app:layout_constraintVertical_bias="0.133" />

    <TextView
        android:id="@+id/textView20"
        android:layout_width="wrap_content"
        android:layout_height="23dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="2728 Kcal"
        android:textColor="@android:color/black"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="@+id/imageView24"
        app:layout_constraintEnd_toEndOf="@+id/imageView24"
        app:layout_constraintTop_toTopOf="@+id/imageView24"
        app:layout_constraintVertical_bias="0.0" />

    <TextView
        android:id="@+id/textView21"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="İdeal Su Alımı"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toBottomOf="@+id/imageView25"
        app:layout_constraintEnd_toEndOf="@+id/imageView25"
        app:layout_constraintHorizontal_bias="0.036"
        app:layout_constraintStart_toStartOf="@+id/imageView25"
        app:layout_constraintTop_toTopOf="@+id/imageView25"
        app:layout_constraintVertical_bias="0.176" />

    <TextView
        android:id="@+id/textView22"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        android:text="2,38 Litre"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toTopOf="@+id/textView27"
        app:layout_constraintEnd_toEndOf="@+id/imageView25"
        app:layout_constraintHorizontal_bias="0.981"
        app:layout_constraintStart_toEndOf="@+id/textView21"
        app:layout_constraintTop_toTopOf="@+id/imageView25"
        app:layout_constraintVertical_bias="0.235" />

    <TextView
        android:id="@+id/textView23"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        android:text="Hedef Kalp Hızı "
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toTopOf="@+id/textView28"
        app:layout_constraintEnd_toStartOf="@+id/textView24"
        app:layout_constraintHorizontal_bias="0.005"
        app:layout_constraintStart_toStartOf="@+id/imageView26"
        app:layout_constraintTop_toTopOf="@+id/imageView26"
        app:layout_constraintVertical_bias="0.571" />

    <TextView
        android:id="@+id/textView24"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginBottom="8dp"
        android:text="108  bpm"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toTopOf="@+id/textView28"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.9"
        app:layout_constraintStart_toStartOf="@+id/imageView26"
        app:layout_constraintTop_toBottomOf="@+id/imageView25"
        app:layout_constraintVertical_bias="0.866" />

    <TextView
        android:id="@+id/textView25"
        android:layout_width="312dp"
        android:layout_height="43dp"
        android:text="Type something Type something Type something Type something Type something Type something"
        app:layout_constraintBottom_toBottomOf="@+id/imageView23"
        app:layout_constraintEnd_toEndOf="@+id/imageView23"
        app:layout_constraintHorizontal_bias="0.363"
        app:layout_constraintStart_toStartOf="@+id/imageView23"
        app:layout_constraintTop_toTopOf="@+id/imageView23"
        app:layout_constraintVertical_bias="1.0" />

    <TextView
        android:id="@+id/textView26"
        android:layout_width="322dp"
        android:layout_height="38dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="8dp"
        android:text="Type something Type something Type something Type something Type something Type something"
        app:layout_constraintBottom_toTopOf="@+id/imageView25"
        app:layout_constraintEnd_toEndOf="@+id/imageView24"
        app:layout_constraintTop_toTopOf="@+id/imageView24"
        app:layout_constraintVertical_bias="0.804" />

    <TextView
        android:id="@+id/textView27"
        android:layout_width="329dp"
        android:layout_height="wrap_content"
        android:text="Type something Type something Type something Type something Type something Type something"
        app:layout_constraintBottom_toBottomOf="@+id/imageView25"
        app:layout_constraintEnd_toEndOf="@+id/imageView25"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="@+id/imageView25"
        app:layout_constraintTop_toTopOf="@+id/imageView25"
        app:layout_constraintVertical_bias="0.865" />

    <TextView
        android:id="@+id/textView28"
        android:layout_width="314dp"
        android:layout_height="wrap_content"
        android:text="Type something Type something Type something Type something Type something Type something"
        app:layout_constraintBottom_toBottomOf="@+id/imageView26"
        app:layout_constraintEnd_toEndOf="@+id/imageView26"
        app:layout_constraintHorizontal_bias="0.4"
        app:layout_constraintStart_toStartOf="@+id/imageView26"
        app:layout_constraintTop_toTopOf="@+id/imageView26"
        app:layout_constraintVertical_bias="0.807" />

    <TextView
        android:id="@+id/textView29"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginBottom="8dp"
        android:text="Sağlık Hesaplama"
        android:textColor="@android:color/background_light"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toTopOf="@+id/imageView22"
        app:layout_constraintEnd_toEndOf="@+id/imageView21"
        app:layout_constraintHorizontal_bias="0.186"
        app:layout_constraintStart_toStartOf="@+id/imageView21"
        app:layout_constraintTop_toTopOf="@+id/imageView21"
        app:layout_constraintVertical_bias="0.692" />
</android.support.constraint.ConstraintLayout>
//YUKARIDAKİ KOD BLOĞU HENÜZ DAHA TASARIM AŞAMASINDA OLAN SAĞLIK HESAPLA SAYFASININ XML KODLARIDIR.
**********************************************************************************************************************************************************************
EĞER EKSİK DÜŞÜNDÜĞÜNÜZ KISIM VARSA AŞAĞIDA VERİLEN GİTHUB SAYFASINDAN BAKABİLİR VEYA EKLEYEBİLİRSİNİZ:
