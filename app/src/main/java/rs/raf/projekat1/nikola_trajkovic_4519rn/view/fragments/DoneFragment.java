package rs.raf.projekat1.nikola_trajkovic_4519rn.view.fragments;



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
import rs.raf.projekat1.nikola_trajkovic_4519rn.recycler.TicketAdapterDone;
import rs.raf.projekat1.nikola_trajkovic_4519rn.recycler.TicketDiffItemCallBack;
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.EditActivityUser;
import rs.raf.projekat1.nikola_trajkovic_4519rn.viewmodels.RecyclerViewModel;

public class DoneFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewModel recyclerViewModel;
    private TicketAdapterDone ticketAdapter;
    private ConstraintLayout mainLayout;
    private EditText searchDone;

    public DoneFragment() {
        super(R.layout.fragment_done);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(this.getActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view) {

        initView(view);
        initListeners(view);
        initObservers(view);
        initRecycler(view);

    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.ListDone);
        mainLayout = view.findViewById(R.id.recyclerMainLayoutDone);
        searchDone = view.findViewById(R.id.searchDone);


    }

    private void initListeners(View view) {

        searchDone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable) {

                recyclerViewModel.filterTicketsDone(editable.toString());

            }
        });

    }

    private void initObservers(View view) {

        recyclerViewModel.getTiketiDoneFilter().observe(getViewLifecycleOwner(), tickets -> {
//            System.out.println("TIKETIII");
//            System.out.println(tickets);
            ticketAdapter.submitList(tickets);
        });
    }

    private void initRecycler(View view) {

        ticketAdapter = new TicketAdapterDone(new TicketDiffItemCallBack(), ticket -> {

            String tiket = ticket.getId() + "," + ticket.getNaslov() + "," + ticket.getOpis()
                    + "," + ticket.getType() + "," + ticket.getPriority() + "," + ticket.getEstimation() + "," + ticket.getLoggedTime();

            Intent intent = new Intent(this.getActivity(), EditActivityUser.class);
            intent.putExtra(EDIT_KEY_USER,tiket);
            startActivity(intent);




        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(ticketAdapter);

    }

}
