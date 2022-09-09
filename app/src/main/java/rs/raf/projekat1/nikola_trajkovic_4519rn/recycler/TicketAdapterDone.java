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

public class TicketAdapterDone extends ListAdapter<Ticket, TicketAdapterDone.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;

    public TicketAdapterDone(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item_done, parent, false);



        return new ViewHolder(view, parent.getContext(), position -> {

            onTicketClicked.accept(getItem(position));

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
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(view -> {

                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClicked.accept(getBindingAdapterPosition());
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

            ((TextView) itemView.findViewById(R.id.naslovTv)).setText(ticket.getNaslov());
            ((TextView) itemView.findViewById(R.id.opisTv)).setText(ticket.getOpis());

        }


    }
}
