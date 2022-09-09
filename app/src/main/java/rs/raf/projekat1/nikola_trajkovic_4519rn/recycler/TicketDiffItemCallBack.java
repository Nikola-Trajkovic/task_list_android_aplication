package rs.raf.projekat1.nikola_trajkovic_4519rn.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.nikola_trajkovic_4519rn.model.Ticket;


public class TicketDiffItemCallBack extends DiffUtil.ItemCallback<Ticket> {
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getNaslov() == newItem.getNaslov();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getPicture().equals(newItem.getPicture())
                && oldItem.getOpis().equals(newItem.getOpis());

    }
}
