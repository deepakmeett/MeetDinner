package com.example.MeetDinner.adaptors;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeetDinner.R;
public class VegDishesAdapter extends RecyclerView.Adapter<VegDishesAdapter.ViewHolder> {
    
    Context context;

    public VegDishesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.big_dishes_item, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String cutPrice = "<strike>₹170</font></strike>";
        holder.cutPrice.setText( Html.fromHtml( cutPrice ) );

        holder.normalPrice.setText( "₹134");
        holder.savePrice.setText( "Save ₹34");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cutPrice, normalPrice, savePrice;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cutPrice = itemView.findViewById( R.id.item_cut_price );
            normalPrice = itemView.findViewById( R.id.normalPrice );
            savePrice = itemView.findViewById( R.id.savePrice );
        }
    }
}
