package rs.raf.projekat1.nikola_trajkovic_4519rn.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;
import rs.raf.projekat1.nikola_trajkovic_4519rn.viewmodels.RecyclerViewModel;


public class SecondFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button add;
    private EditText est;
    private EditText naslov;
    private EditText opis;
    private RecyclerViewModel recyclerViewModel;

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        recyclerViewModel = new ViewModelProvider(this.getActivity()).get(RecyclerViewModel.class);
    }

    private void init(View view) {

        Spinner spinner = view.findViewById(R.id.spiner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext() ,R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);

        Spinner spinner1 = view.findViewById(R.id.spiner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(), R.array.priority, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(this);
        spinner1.setAdapter(adapter1);

        add = view.findViewById(R.id.addBtn);

        add.setOnClickListener(click -> {

            Boolean flage = true;
            String naslovStr = null;
            String opisStr = null;
            int estInt = 0;


            String type = spinner.getSelectedItem().toString();
            String priority = spinner1.getSelectedItem().toString();

            est = view.findViewById(R.id.estEt);
            naslov = view.findViewById(R.id.naslovEt);
            opis = view.findViewById(R.id.opisEt);

            if(!naslov.getText().toString().isEmpty()){
                 naslovStr = naslov.getText().toString();
            }else{
                flage = false;
                Toast.makeText(this.getActivity(), "Morate uneti naslov", Toast.LENGTH_SHORT).show();
            }

            if(!opis.getText().toString().isEmpty()){
                 opisStr = opis.getText().toString();
            }else{
                flage = false;
                Toast.makeText(this.getActivity(), "Morate uneti opis", Toast.LENGTH_SHORT).show();
            }

            if(!est.getText().toString().isEmpty()){
                 estInt = Integer.valueOf(est.getText().toString());
            }else{
                flage = false;
                Toast.makeText(this.getActivity(), "Morate uneti est", Toast.LENGTH_SHORT).show();
            }


            if(flage) {

                recyclerViewModel.addTicketTodo(naslovStr,opisStr,type,priority,estInt);
                est.getText().clear();
                naslov.getText().clear();
                opis.getText().clear();

            }





        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
