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

import java.util.List;

/**
 * Adapter class for Recycler view
 * Responsible for providing views of items from Dataset
 *
 * @author Keegan George
 * @version 1.0
 */
public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {
    // FIELDS //
    private List<DataItem> mItems; // list of data items
    private Context mContext;

    // CONSTRUCTOR //
    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    /**
     * Responsible for creating the view holder object for each Recycler view item
     *
     * @param parent   parent view group
     * @param viewType current type of view
     * @return view holder object
     */
    @NonNull
    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    /**
     * Responsible for binding data from the data source to each object in the recycler view
     *
     * @param holder   current view holder object
     * @param position position in data item list
     */
    @Override
    public void onBindViewHolder(@NonNull DataItemAdapter.ViewHolder holder, int position) {

        final DataItem item = mItems.get(position); // get index of data item in data item list
        holder.tvName.setText(item.getItemName()); // set the text of the item in recycler view
        holder.mView.setOnClickListener(new View.OnClickListener() {
            /**
             * Responsible for click of item in recycler view list
             *
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                // explicit intent to open new activity containing details of
                // the specific sensor clicked on
                Intent sensorIntent = new Intent(mContext, SensorDetails.class);
                sensorIntent.putExtra("sensorKey", item.getItemId());
                mContext.startActivity(sensorIntent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            /**
             * Responsible for long click of item in recycler view list
             *
             * @param v current view
             * @return true to only trigger long press / false to trigger short press as well
             */
            @Override
            public boolean onLongClick(View v) {
                // displays sensor name in toast
                Toast.makeText(mContext, "View \"" + item.getItemName() + "\" sensor values", Toast.LENGTH_SHORT).show();
                return true; // true to not trigger short press
            }
        });
    }

    /**
     * getter method for the size of items in the recycler view
     *
     * @return size of item list
     */
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * View holder class
     *
     * @version 1.0
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // FIELDS //
        public TextView tvName;
        public View mView;

        // CONSTRUCTOR //
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.list_item);
            mView = itemView;
        }
    }
}
