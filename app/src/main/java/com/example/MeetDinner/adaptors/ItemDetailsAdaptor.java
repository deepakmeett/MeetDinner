package com.example.MeetDinner.adaptors;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MeetDinner.R;
import com.example.MeetDinner.modals.ItemDetailsModel;

import java.util.List;
public class ItemDetailsAdaptor extends RecyclerView.Adapter<ItemDetailsAdaptor.ViewHolder> {

    Context context;
    List<ItemDetailsModel> model;
    String veg;
    int listSize, addItem = 0;
    String cutPrice;

    public ItemDetailsAdaptor(Context context, List<ItemDetailsModel> model, String veg) {
        this.context = context;
        this.model = model;
        this.veg = veg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.dishes_item, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemDetailsModel list = model.get( position );
        if (veg.equalsIgnoreCase( "both" )) {
            holder.itemName.setText( list.getItemName() );
            holder.itemDesc.setText( list.getDescription() );
            holder.normalPrice.setText( String.valueOf( list.getPrice() ) );
//            cutPrice = "<strike>₹170</font></strike>";
//            holder.cutPrice.setText( Html.fromHtml( cutPrice ) );
//            holder.savePrice.setText( "Save ₹34" );
            if (list.getCategory().equalsIgnoreCase( "non veg" )) {
                holder.itemCategory.setBackground( ContextCompat.getDrawable( context, R.drawable.ic_non_veg_mark ) );

            }
        }
        if (veg.equalsIgnoreCase( "vegetarian" )) {
            for (ItemDetailsModel item : model) {
                if (item.getCategory().equals( "veg" )) {
                    holder.itemName.setText( list.getItemName() );
                    holder.itemDesc.setText( list.getDescription() );
                    holder.normalPrice.setText( String.valueOf( list.getPrice() ) );
//            cutPrice = "<strike>₹170</font></strike>";
//            holder.cutPrice.setText( Html.fromHtml( cutPrice ) );
//            holder.savePrice.setText( "Save ₹34" );
                }
            }
        }
        if (veg.equalsIgnoreCase( "non_vegetarian" )) {
            for (ItemDetailsModel item : model) {
                if (item.getCategory().equals( "non veg" )) {
                    holder.itemCategory.setBackground( ContextCompat.getDrawable( context, R.drawable.ic_non_veg_mark ) );
                    holder.itemName.setText( list.getItemName() );
                    holder.itemDesc.setText( list.getDescription() );
                    holder.normalPrice.setText( String.valueOf( list.getPrice() ) );
//            cutPrice = "<strike>₹170</font></strike>";
//            holder.cutPrice.setText( Html.fromHtml( cutPrice ) );
//            holder.savePrice.setText( "Save ₹34" );
                }
            }
        }
        
        holder.addLL.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (addItem == 0) {
                    holder.addLL.setBackground( ContextCompat.getDrawable( context, R.drawable.square_red_btn ) );
                    holder.addText.setTextColor( ContextCompat.getColor( context, android.R.color.white ) );
                    holder.addText.setText( "ADDED" );
                    holder.addImage.setVisibility( View.GONE );
                    holder.addChecked.setVisibility( View.VISIBLE );
                    addItem++;
                }else {
                    holder.addLL.setBackground( ContextCompat.getDrawable( context, R.drawable.textborder2 ) );
                    holder.addText.setTextColor( ContextCompat.getColor( context, android.R.color.black ) );
                    holder.addText.setText( "ADD" );
                    holder.addImage.setVisibility( View.VISIBLE );
                    holder.addChecked.setVisibility( View.GONE );
                    addItem--;
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        listSize = model.size();
        if (veg.equalsIgnoreCase( "vegetarian" )) {
            listSize = 0;
            for (ItemDetailsModel item : model) {
                if (item.getCategory().equals( "veg" )) {
                    listSize++;
                }
            }

        }
        if (veg.equalsIgnoreCase( "non_vegetarian" )) {
            listSize = 0;
            for (ItemDetailsModel item : model) {
                if (item.getCategory().equals( "non veg" )) {
                    listSize++;
                }
            }
        }
        return listSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDesc, cutPrice, normalPrice, savePrice, addText;
        ImageView itemCategory, addImage, addChecked;
        RelativeLayout addLL;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            itemName = itemView.findViewById( R.id.itemName );
            itemDesc = itemView.findViewById( R.id.itemDesc );
            itemCategory = itemView.findViewById( R.id.itemCategory );
            cutPrice = itemView.findViewById( R.id.item_cut_price );
            normalPrice = itemView.findViewById( R.id.normalPrice );
            savePrice = itemView.findViewById( R.id.savePrice );
            addLL = itemView.findViewById( R.id.addLL );
            addText = itemView.findViewById( R.id.addText );
            addImage = itemView.findViewById( R.id.addImage );
            addChecked = itemView.findViewById( R.id.addChecked );
            
        }
    }
}