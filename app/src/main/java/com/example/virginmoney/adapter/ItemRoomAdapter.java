package com.example.virginmoney.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virginmoney.R;
import com.example.virginmoney.databinding.ItemRoomsViewBinding;
import com.example.virginmoney.databinding.ItemViewBinding;
import com.example.virginmoney.service.People;
import com.example.virginmoney.service.Room;

import java.util.List;



public class ItemRoomAdapter extends RecyclerView.Adapter<ItemRoomAdapter.ViewHolder>{


    private NewsAdapterListener listener = null;

    private List<People> people;
    private List<Room> rooms;
    private LayoutInflater layoutInflater;
    ItemRoomsViewBinding itemViewBinding;


    public ItemRoomAdapter(List<Room> articles, NewsAdapterListener listener) {
        this.rooms = articles;
        this.listener = listener;
    }

    public void setPeoples(List<Room> rooms) {
        if (rooms!=null){
            this.rooms = rooms;
        }

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRoomsViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rooms_view, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.binding.setView(rooms.get(i));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return rooms == null ? 0 : rooms.size();
    }


    public interface NewsAdapterListener {
        void onNewsItemClicked(Room people);

        void onItemOptionsClicked(Room people);


    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ItemRoomsViewBinding binding;

        public ViewHolder(final ItemRoomsViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //this.binding.ivOptions.setOnClickListener(this);
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = this.getAdapterPosition();
            if (v instanceof ImageView) {

                if (rooms!=null){
                    listener.onItemOptionsClicked(rooms.get(index));
                }
            } else {


                if (rooms!=null){
                    listener.onNewsItemClicked(rooms.get(index));
                }

            }
        }
    }
}
