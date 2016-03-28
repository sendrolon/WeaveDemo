package com.google.samples.apps.ledtoggler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by B50027 on 3/25/2016.
 */
public class SensorElementAdapter extends RecyclerView.Adapter<SensorElementAdapter.ViewHolder>
{
    private final ArrayList<Sensor> mDataSet;
    public SensorElementAdapter()
    {
        mDataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_items, parent, false);
        return new ViewHolder(v,
                (TextView) v.findViewById(R.id.textView));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String buff = new String();
        for (Sensor s : mDataSet ) {
            buff = buff + s.getReport();
        }
        holder.mSensorInfo.setText(buff);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void add(Sensor sensor) {
        mDataSet.add(sensor);
        notifyItemInserted(mDataSet.size() -1);
    }

    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mSensorInfo;
        public final View parnetView;

        public ViewHolder(final View parentView, final TextView sensorInfo) {
            super(parentView);
            this.mSensorInfo = sensorInfo;
            this.parnetView = parentView;

        }

    }
}
