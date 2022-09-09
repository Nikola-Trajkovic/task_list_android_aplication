package rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;


public class EditActivityUser extends AppCompatActivity {

    public static final String EDIT_KEY_USER="edit_key_user";

    private TextView naslovEt;
    private Button loggEt;
    private TextView estEt;
    private TextView opisEt;
    private TextView priorityEt;
    private TextView typeEt;
    private Button editBtn;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        init();
    }

    public void init(){

        initViews();
        initListeners();
        setText();

    }

    private void setText() {

        Intent intent = getIntent();


        if(intent != null){
            String text = intent.getStringExtra(EDIT_KEY_USER);
            System.out.println("TEXTTTTT");
            System.out.println(text);
            String[] textSplit = text.split(",");
            String naslov = textSplit[1];
            String opis = textSplit[2];
            String type = textSplit[3];
            String priority = textSplit[4];
            String estimation = textSplit[5];
            String logg = textSplit[6];

            naslovEt.setText(naslov);
            opisEt.setText(opis);
            typeEt.setText(type);
            priorityEt.setText(priority);
            estEt.setText(estimation);
//            loggEt.setText(logg);
            ImageView imageView = findViewById(R.id.imageView5);
            if(type.equals("bug")){
                Glide
                        .with(getBaseContext())
                        .load(R.drawable.bug)
                        .into(imageView);
            }else{
                Glide
                        .with(getBaseContext())
                        .load(R.drawable.rocket)
                        .into(imageView);
            }

        }




    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        naslovEt = findViewById(R.id.textView12User);
        typeEt = findViewById(R.id.typeEtEditUser);
        priorityEt = findViewById(R.id.priorityEtEditUser);
        estEt = findViewById(R.id.textView23User);
        loggEt = findViewById(R.id.logBtnEditUser);
        opisEt = findViewById(R.id.opisEtEditUser);
        imageView = findViewById(R.id.imageView5);


    }

    private void initListeners() {

        loggEt.setOnClickListener(view -> {

            String text = loggEt.getText().toString();
            int broj = Integer.valueOf(text) + 1;
            loggEt.setText(Integer.toString(broj));

        });

        loggEt.setOnLongClickListener(view -> {

            int broj = 2;
            loggEt.setText(Integer.toString(broj));

            return false;
        });

    }

}