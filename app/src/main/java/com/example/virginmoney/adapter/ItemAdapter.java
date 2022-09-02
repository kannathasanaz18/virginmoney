package com.example.virginmoney.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virginmoney.R;
import com.example.virginmoney.databinding.ItemViewBinding;
import com.example.virginmoney.service.People;
import com.example.virginmoney.service.Room;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{


    private NewsAdapterListener listener = null;

    private List<People> people;
    private List<Room> rooms;
    private LayoutInflater layoutInflater;
    ItemViewBinding itemViewBinding;


    public ItemAdapter(List<People> articles, NewsAdapterListener listener) {
        this.people = articles;
        this.listener = listener;
    }

    public void setPeoples(List<People> people) {
        if (people != null) {
            this.people = people;

            notifyDataSetChanged();
        }


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemViewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_view, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.binding.setView(people.get(i));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return people == null ? 0 : people.size();
    }


    public interface NewsAdapterListener {
        void onNewsItemClicked(People people);

        void onItemOptionsClicked(People people);


    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ItemViewBinding binding;

        public ViewHolder(final ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = this.getAdapterPosition();
            if (v instanceof ImageView) {

                if (people!=null){
                    listener.onItemOptionsClicked(people.get(index));
                }
            } else {


                if (people!=null){
                    listener.onNewsItemClicked(people.get(index));
                }

            }
        }
    }
}
