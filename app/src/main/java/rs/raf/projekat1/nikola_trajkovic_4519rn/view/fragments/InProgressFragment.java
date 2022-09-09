package rs.raf.projekat1.nikola_trajkovic_4519rn.view.fragments;



import static rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.EditActivity.EDIT_KEY;
import static rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.EditActivityUser.EDIT_KEY_USER;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;
import rs.raf.projekat1.nikola_trajkovic_4519rn.recycler.TicketAdapterInProgress;
import rs.raf.projekat1.nikola_trajkovic_4519rn.recycler.TicketDiffItemCallBack;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.EditActivity;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.EditActivityUser;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.MainActivity;
import rs.raf.projekat1.nikola_trajkovic_4519rn.viewmodels.RecyclerViewModel;


public class InProgressFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewModel recyclerViewModel;
    private TicketAdapterInProgress ticketAdapter;
    private EditText search;
    private ConstraintLayout mainLayout;

    public InProgressFragment() {
        super(R.layout.fragment_inprogress);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(this.getActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(View view) {

        initView(view);
        initListeners(view);
        initObservers(view);
        initRecycler(view);

    }


    private void initView(View view) {

        recyclerView = view.findViewById(R.id.listProgress);
        mainLayout = view.findViewById(R.id.mainLayoutProgress);
        search = view.findViewById(R.id.searchProgress);

    }

    private void initListeners(View view) {

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {

                recyclerViewModel.filterTicketsInProgress(editable.toString());


            }
        });

    }

    private void initObservers(View view) {

        recyclerViewModel.getTiketiInProgressFilter().observe(getViewLifecycleOwner(), tickets -> {
//            System.out.println("TIKETIII");
//            System.out.println(tickets);
            ticketAdapter.submitList(tickets);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initRecycler(View view) {

        ticketAdapter = new TicketAdapterInProgress(new TicketDiffItemCallBack(), ticket -> {
            //Toast.makeText(this.getActivity(), "Kliknut tiket " + ticket.getNaslov(), Toast.LENGTH_SHORT).show();
            String tiket = ticket.getId() + "," + ticket.getNaslov() + "," + ticket.getOpis()
                    + "," + ticket.getType() + "," + ticket.getPriority() + "," + ticket.getEstimation() + "," + ticket.getLoggedTime();
            if(MainActivity.username.startsWith("admin")){
                Intent intent = new Intent(this.getActivity(), EditActivity.class);
                intent.putExtra(EDIT_KEY,tiket);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this.getActivity(), EditActivityUser.class);
                intent.putExtra(EDIT_KEY_USER,tiket);
                startActivity(intent);
            }

        }, getOnButtonDesnoClicked -> {


            //Toast.makeText(this.getActivity(), "USAO U DESNO", Toast.LENGTH_SHORT).show();
            recyclerViewModel.addTicketDone(getOnButtonDesnoClicked.getId());

            recyclerViewModel.removeTicketInProgress(getOnButtonDesnoClicked.getId());



        }, getOnButtonLevoClicked -> {

            //Toast.makeText(this.getActivity(), "USAO U LEVO", Toast.LENGTH_SHORT).show();
            recyclerViewModel.addTicketTodoProgress(getOnButtonLevoClicked.getId());

            recyclerViewModel.removeTicketInProgress(getOnButtonLevoClicked.getId());

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(ticketAdapter);

    }

}
