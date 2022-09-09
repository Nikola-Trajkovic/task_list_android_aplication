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
import rs.raf.projekat1.nikola_trajkovic_4519rn.view.activities.MainActivity;

public class TicketAdapter extends ListAdapter<Ticket, TicketAdapter.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;
    private final Consumer<Ticket> getOnButtonDesnoClicked;
    private final Consumer<Ticket> getOnButtonDeleteClicked;

    public TicketAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked, Consumer<Ticket> getOnButtonDesnoClicked, Consumer<Ticket> getOnButtonDeleteClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        this.getOnButtonDesnoClicked = getOnButtonDesnoClicked;
        this.getOnButtonDeleteClicked = getOnButtonDeleteClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item, parent, false);




        return new ViewHolder(view, parent.getContext(), position -> {

            onTicketClicked.accept(getItem(position));

        }, position2 -> {

            getOnButtonDesnoClicked.accept(getItem(position2));

        }, position3 -> {

            getOnButtonDeleteClicked.accept(getItem(position3));

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
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked, Consumer<Integer> onNextClicked, Consumer<Integer> onDeleteClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClicked.accept(getBindingAdapterPosition());
                }

            });

            itemView.findViewById(R.id.nextBtn).setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onNextClicked.accept(getBindingAdapterPosition());
                }

            });

            itemView.findViewById(R.id.deleteBtn).setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onDeleteClicked.accept(getBindingAdapterPosition());
                }

            });

        }

        public void bind(Ticket ticket) {
            ImageView imageView = itemView.findViewById(R.id.ticketPictureIv);
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

            if(!MainActivity.username.startsWith("admin")){
                itemView.findViewById(R.id.deleteBtn).setVisibility(View.GONE);
            }


            ((TextView) itemView.findViewById(R.id.naslovTv)).setText(ticket.getNaslov());
            ((TextView) itemView.findViewById(R.id.opisTv)).setText(ticket.getOpis());

        }


    }

}
