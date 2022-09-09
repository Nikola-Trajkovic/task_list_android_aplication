package rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities;



import static rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.MainActivity.USER_KEY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;


public class SplashActivity extends AppCompatActivity {


    public static final String PREF_KEY = "key";
    public static final String ADMIN_KEY= "admin_key";
    public static final String KORISNIK_KEY= "korisnik_key";
    private String user = null;

    private ActivityResultLauncher<Intent> preferencesActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {

                    if(result.getResultCode() == Activity.RESULT_OK){

                        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                        user = sharedPreferences.getString(PREF_KEY, null);


                    }else{
                        Toast.makeText(this, "Greska", Toast.LENGTH_SHORT).show();
                    }

            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition( () -> {

            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            user = sharedPreferences.getString(PREF_KEY, null);

            System.out.println("USERRRRRR");
            System.out.println(user);

            if(user == null){

                Intent intent = new Intent(this,LoginActivity.class);

                Toast.makeText(this, "Login activity", Toast.LENGTH_SHORT).show();

                preferencesActivityResultLauncher.launch(intent);

                finish();

            }else{

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(USER_KEY, user);

                Toast.makeText(this, "Main activity", Toast.LENGTH_SHORT).show();
                startActivity(intent);

                finish();

            }
            return false;


        });



    }
}