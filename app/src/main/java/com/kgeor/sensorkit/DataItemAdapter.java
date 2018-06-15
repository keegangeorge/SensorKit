package com.kgeor.sensorkit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kgeor.sensorkit.model.DataItem;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {
    // FIELDS //
    public static final String ITEM_ID_KEY = "item_id_key";
    private List<DataItem> mItems;
    private Context mContext;

    // CONSTRUCTOR //
    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataItemAdapter.ViewHolder holder, int position) {
        final DataItem item = mItems.get(position);

        holder.tvName.setText(item.getItemName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = item.getItemId();

                Intent sensorIntent = new Intent(mContext, SensorDetails.class);
                sensorIntent.putExtra(ITEM_ID_KEY, item.getItemId());
                mContext.startActivity(sensorIntent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "View \"" + item.getItemName() + "\" sensor values", Toast.LENGTH_SHORT).show();
                return true; // true to not trigger short press
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // FIELDS //
        public TextView tvName;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_item);
            mView = itemView;
        }
    }
}
