package rs.raf.projekat1.nikola_trajkovic_4519rn.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.LoginActivity;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.MainActivity;


public class FourthFragment extends Fragment {

    private Button logOutBtn;
    private TextView usernameTv;
    private TextView emailTv;

    public FourthFragment() {
        super(R.layout.fragment_fourth);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){

        usernameTv = view.findViewById(R.id.usernameLb);
        emailTv = view.findViewById(R.id.emailLb);

        usernameTv.setText(MainActivity.username);
        emailTv.setText(MainActivity.email);

        initListeners(view);

    }


    private void initListeners(View view) {

        view.findViewById(R.id.logOutBtn).setOnClickListener(r -> {

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(this.getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();

        });

    }

}
