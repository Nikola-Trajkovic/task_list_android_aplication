package rs.raf.projekat1.nikola_trajkovic_4519rn.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;
import rs.raf.projekat1.nikola_trajkovic_4519rn.model.Ticket;
import rs.raf.projekat1.nikola_trajkovic_4519rn.viewmodels.RecyclerViewModel;


public class FirstFragment extends Fragment {

    private TextView todo;
    private TextView inProgress;
    private TextView done;

    private TextView todoE;
    private TextView todoB;

    private TextView inProgressE;
    private TextView inProgressB;

    private TextView doneE;
    private TextView doneB;

    private RecyclerViewModel recyclerViewModel;

    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //System.out.println("USAO U CREATED!!!");
        recyclerViewModel = new ViewModelProvider(this.getActivity()).get(RecyclerViewModel.class);
        init(view);
        initObserver();
    }

    private void initObserver() {

        recyclerViewModel.getTiketiToDo().observe(getViewLifecycleOwner(), tickets -> {

            todo.setText(Integer.toString(tickets.size()));

            int bug =0;
            int ec =0;

            for (Ticket t : tickets){

                System.out.println(t.getType());
                if(t.getType().equals("bug")){
                    bug++;
                }else{
                    ec++;
                }

            }

            todoE.setText(Integer.toString(ec));
            todoB.setText(Integer.toString(bug));

        });

        recyclerViewModel.getTiketiInProgress().observe(getViewLifecycleOwner(), tickets -> {

            inProgress.setText(Integer.toString(tickets.size()));

            int bug =0;
            int ec =0;

            for (Ticket t : tickets){

                System.out.println(t.getType());
                if(t.getType().equals("bug")){
                    bug++;
                }else{
                    ec++;
                }

            }

            inProgressE.setText(Integer.toString(ec));
            inProgressB.setText(Integer.toString(bug));

        });

        recyclerViewModel.getTiketiDone().observe(getViewLifecycleOwner(), tickets -> {

            done.setText(Integer.toString(tickets.size()));

            int bug =0;
            int ec =0;

            for (Ticket t : tickets){

                System.out.println(t.getType());
                if(t.getType().equals("bug")){
                    bug++;
                }else{
                    ec++;
                }

            }

            doneE.setText(Integer.toString(ec));
            doneB.setText(Integer.toString(bug));

        });

    }


    private void init(View view) {

        todo = view.findViewById(R.id.toDo);
        inProgress = view.findViewById(R.id.inProgress);
        done = view.findViewById(R.id.done);

        todoE = view.findViewById(R.id.toDoE);
        todoB = view.findViewById(R.id.toDob);

        inProgressE = view.findViewById(R.id.inProgressE);
        inProgressB = view.findViewById(R.id.inProgressB);

        doneE = view.findViewById(R.id.doneE);
        doneB = view.findViewById(R.id.doneB);




    }

}
