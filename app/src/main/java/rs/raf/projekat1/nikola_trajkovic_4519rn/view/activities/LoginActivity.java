package rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities;



import static rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.MainActivity.USER_KEY;
import static rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.SplashActivity.PREF_KEY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;


public class LoginActivity extends AppCompatActivity {

    public static final String adminPassword = "admin";
    public static final String korisnikPassword = "korisnik";
    private Button loginBtn;
    private EditText usernameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){

        initView();
        initListeners();

    }

    private void initView() {
        loginBtn = findViewById(R.id.loginBtn);
        usernameEdit = findViewById(R.id.usernameEditText);
        emailEdit = findViewById(R.id.emailEditText);
        passwordEdit = findViewById(R.id.passwordEditText);

    }


    private void initListeners() {

        loginBtn.setOnClickListener(res -> {
            
            Boolean flag = true;
            String username = "";
            String email = "";
            String password = "";

            if(!usernameEdit.getText().toString().isEmpty()){

                 username = usernameEdit.getText().toString();

            }else{
                flag = false;
                Toast.makeText(this, "Morate popuniti username!", Toast.LENGTH_SHORT).show();
            }

            if(!emailEdit.getText().toString().isEmpty()){

                 email = emailEdit.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(this, "Niste lepo uneli email", Toast.LENGTH_SHORT).show();
                    flag = false;
                }

            }else {
                flag = false;
                Toast.makeText(this, "Morate uneti email", Toast.LENGTH_SHORT).show();
            }
            
            if(!passwordEdit.getText().toString().isEmpty()){
                
                 password = passwordEdit.getText().toString();
                 
                if(username.startsWith("admin")){
                     if(!password.equals(adminPassword)){
                         flag = false;
                         Toast.makeText(this, "Pogresan password", Toast.LENGTH_SHORT).show();
                     }
                }else{
                    if(!password.equals(korisnikPassword)){
                        flag = false;
                        Toast.makeText(this, "Pogresan password", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }else{
                flag = false;
                Toast.makeText(this, "Morate uneti password", Toast.LENGTH_SHORT).show();
            }

            int result;

            if (flag){



                String preferense = username + "," + email;

                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(PREF_KEY, preferense).apply();

                result = Activity.RESULT_OK;

                setResult(result);

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra(USER_KEY, preferense);
                startActivity(intent);
                finish();

            }else{
                result = Activity.RESULT_CANCELED;
                setResult(result);
                Toast.makeText(this, "Neuspesno logovanje", Toast.LENGTH_SHORT).show();
            }




        });

    }


}