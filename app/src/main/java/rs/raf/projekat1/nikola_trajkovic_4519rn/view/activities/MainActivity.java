package rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.viewpagers.PageAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String USER_KEY = "user_key";
    private ViewPager viewPager;
    public static String username = "";
    public static String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initViewPager();
        initNavigation();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        Intent intent = getIntent();
        if(intent != null){
            String user = intent.getStringExtra(USER_KEY);
            System.out.println("USER U MAINU");
            System.out.println(user);
            String[] split = user.split(",");
            username = split[0];
            email = split[1];
            System.out.println(username);
            System.out.println(email);

        }


    }

    private void initNavigation() {
        ((BottomNavigationView)findViewById(R.id.bottomNavigation)).setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                // setCurrentItem metoda viewPager samo obavesti koji je Item trenutno aktivan i onda metoda getItem u adapteru setuje odredjeni fragment za tu poziciju
                case R.id.navigation_1: viewPager.setCurrentItem(PageAdapter.FRAGMENT_1, false); break;
                case R.id.navigation_2: viewPager.setCurrentItem(PageAdapter.FRAGMENT_2, false); break;
                case R.id.navigation_3: viewPager.setCurrentItem(PageAdapter.FRAGMENT_3, false); break;
                case R.id.navigation_4: viewPager.setCurrentItem(PageAdapter.FRAGMENT_4, false); break;
            }
            return true;
        });
    }
}