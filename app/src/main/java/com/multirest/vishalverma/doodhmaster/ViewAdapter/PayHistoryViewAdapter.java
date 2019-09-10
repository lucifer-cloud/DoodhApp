package com.multirest.vishalverma.doodhmaster.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multirest.vishalverma.doodhmaster.Model.HomeModel;
import com.multirest.vishalverma.doodhmaster.Model.PayHistoryModel;
import com.multirest.vishalverma.doodhmaster.R;

import java.util.List;

public class PayHistoryViewAdapter extends RecyclerView.Adapter<PayHistoryViewAdapter.PayHistoryViewHolder> {

    private List<PayHistoryModel> phList;
    private Context context;

    public PayHistoryViewAdapter(List<PayHistoryModel> phList, Context context) {
        this.phList = phList;
        this.context =

                context;
    }



    @NonNull
    @Override
    public PayHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payhistoryitem, parent, false);

        return new PayHistoryViewAdapter.PayHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PayHistoryViewHolder holder, int position) {

        final PayHistoryModel payHistoryModel = phList.get(position);
        holder.phdate.setText(payHistoryModel.getDate());
        holder.phamt.setText(String.valueOf("₹ " +payHistoryModel.getAmount()));

    }

    @Override
    public int getItemCount() {
        return  phList.size();
    }

    public class PayHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView phdate,phamt;
        public PayHistoryViewHolder(View itemView) {
            super(itemView);
            phdate =  (TextView) itemView.findViewById(R.id.phdate);
            phamt =  (TextView) itemView.findViewById(R.id.phap);

        }
    }
}
