package com.multirest.vishalverma.doodhmaster.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multirest.vishalverma.doodhmaster.Interface.ItemClickListener;
import com.multirest.vishalverma.doodhmaster.Model.CalendardaysModel;
import com.multirest.vishalverma.doodhmaster.Model.HomeModel;
import com.multirest.vishalverma.doodhmaster.R;

import java.util.List;

public class CalenderActivityAdapter extends RecyclerView.Adapter<CalenderActivityAdapter.CalenderActivityViewHolder> {

    private List<CalendardaysModel> cList;
    private Context context;
    private ItemClickListener itemClickListener;

    public CalenderActivityAdapter(List<CalendardaysModel> cList, Context context) {
        this.cList = cList;
        this.context = context;
    }



    @NonNull
    @Override
    public CalenderActivityAdapter.CalenderActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cal_row_addapt, parent, false);

        return new CalenderActivityAdapter.CalenderActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderActivityAdapter.CalenderActivityViewHolder holder, int position) {
        final CalendardaysModel calendardaysModel = cList.get(position);
       String even,morn;
        if (calendardaysModel.getEvening() == null)
        {
            even = "no milk";
        }else {
            even =  calendardaysModel.getEvening();
        }

            if (calendardaysModel.getMorning() == null){
                morn = "no milk";

            }else {
                morn = calendardaysModel.getMorning();
            }

                holder.ctvTitle.setText("Date : "+calendardaysModel.getDate());
        holder.ctvSubject.setText("Morning : "+morn);
        holder.ctvDuedate.setText("Due Date : " +even);
        holder.ctvDescription.setText("Evening : "+even);



    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    public class CalenderActivityViewHolder extends RecyclerView.ViewHolder {

        TextView ctvTitle,ctvSubject,ctvDuedate,ctvDescription;

        public CalenderActivityViewHolder(View itemView) {
            super(itemView);

            ctvTitle=(TextView)itemView.findViewById(R.id.tv_name);
             ctvSubject=(TextView)itemView.findViewById(R.id.tv_type);
             ctvDuedate=(TextView)itemView.findViewById(R.id.tv_desc);
             ctvDescription=(TextView)itemView.findViewById(R.id.tv_class);


        }
    }
}
