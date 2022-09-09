package rs.raf.projekat1.nikola_trajkovic_4519rn.recycler;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.function.Consumer;

import rs.raf.projekat1.nikola_trajkovic_4519rn.R;
import rs.raf.projekat1.nikola_trajkovic_4519rn.model.Ticket;

public class TicketAdapterInProgress extends ListAdapter<Ticket, TicketAdapterInProgress.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;
    private final Consumer<Ticket> getOnButtonDesnoClicked;
    private final Consumer<Ticket> getOnButtonLevoClicked;

    public TicketAdapterInProgress(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked, Consumer<Ticket> getOnButtonDesnoClicked, Consumer<Ticket> getOnButtonDeleteClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        this.getOnButtonDesnoClicked = getOnButtonDesnoClicked;
        this.getOnButtonLevoClicked = getOnButtonDeleteClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item_progress, parent, false);



        return new ViewHolder(view, parent.getContext(), position -> {

            onTicketClicked.accept(getItem(position));

        }, position2 -> {

            getOnButtonDesnoClicked.accept(getItem(position2));

        }, position3 -> {

            getOnButtonLevoClicked.accept(getItem(position3));

        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket =getItem(position);
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final Context context;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked, Consumer<Integer> onNextClicked, Consumer<Integer> onPreviousClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClicked.accept(getBindingAdapterPosition());
                }

            });

            itemView.findViewById(R.id.nextBtnInProgress).setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onNextClicked.accept(getBindingAdapterPosition());
                }

            });

            itemView.findViewById(R.id.imageButtonInProgress).setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onPreviousClicked.accept(getBindingAdapterPosition());
                }

            });

        }

        public void bind(Ticket ticket) {
            ImageView imageView = itemView.findViewById(R.id.ticketPictureIvInProgress);
            if(ticket.getType().equals("bug")){
                Glide
                        .with(context)
                        .load(R.drawable.bug)
                        .into(imageView);
            }else{
                Glide
                        .with(context)
                        .load(R.drawable.rocket)
                        .into(imageView);
            }

            ((TextView) itemView.findViewById(R.id.naslovTvInProgress)).setText(ticket.getNaslov());
            ((TextView) itemView.findViewById(R.id.opisTvInProgress)).setText(ticket.getOpis());

        }


    }
}
