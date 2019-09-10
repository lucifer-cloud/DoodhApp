package com.multirest.vishalverma.doodhmaster.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.multirest.vishalverma.doodhmaster.Common.CommonKey;
import com.multirest.vishalverma.doodhmaster.Common.FirebaseInitilization;
import com.multirest.vishalverma.doodhmaster.Model.WorkLocationModel;
import com.multirest.vishalverma.doodhmaster.R;

import java.util.List;

public class WorkLocationViewAdapter extends RecyclerView.Adapter<WorkLocationViewAdapter.WorkLocationViewHolder> {

    private List<WorkLocationModel> wlList;
    private Context context;
String uid;

    public WorkLocationViewAdapter(List<WorkLocationModel> wlList, Context context, String uid) {
        this.wlList = wlList;
        this.context = context;
        this.uid = uid;
    }


    @NonNull
    @Override
    public WorkLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worklocationrowitem, parent, false);

        return new WorkLocationViewAdapter.WorkLocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkLocationViewHolder holder, final int position) {

        final WorkLocationModel workLocationModel = wlList.get(position);
        holder.wltitle.setText(workLocationModel.getTitle());
        holder.wlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                CommonKey commonKey = new CommonKey();
//                if (uid == null){
//                    uid = commonKey.uidnull(context);
////            Toast.makeText(getActivity(),"null " + commonKey.uidnull(getActivity()),Toast.LENGTH_SHORT).show();
//                }
//                if (uid == null){
//                    uid = CommonKey.key;
//                }




                FirebaseInitilization  firebaseInitilization = new FirebaseInitilization();
                firebaseInitilization.owner.child(uid).child("WorkPlace").child(workLocationModel.getKey()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wlList.size();
    }

    public class WorkLocationViewHolder extends RecyclerView.ViewHolder {
        TextView wltitle;
        Button wlbutton;
        public WorkLocationViewHolder(View itemView) {
            super(itemView);
            wltitle = (TextView) itemView.findViewById(R.id.worklocationtitle);
            wlbutton = (Button) itemView.findViewById(R.id.worklocationdelete);
        }
    }
}
