package com.multirest.vishalverma.doodhmaster.ViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.multirest.vishalverma.doodhmaster.Interface.ItemClickListener;
import com.multirest.vishalverma.doodhmaster.Model.HomeModel;
import com.multirest.vishalverma.doodhmaster.R;
import com.multirest.vishalverma.doodhmaster.SubActivity.ClientDetail;
import com.multirest.vishalverma.doodhmaster.SubActivity.ClientPayHistoryActivity;
import com.multirest.vishalverma.doodhmaster.SubActivity.ClientViewActivity;

import org.w3c.dom.Text;

import java.nio.Buffer;
import java.util.List;

public class ClientViewAdapter extends RecyclerView.Adapter<ClientViewAdapter.ClientViewHolder> {

    private List<HomeModel> cList;
    private Context context;
    private ItemClickListener itemClickListener;

    public ClientViewAdapter(List<HomeModel> cList, Context context) {
        this.cList = cList;
        this.context = context;
    }



    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clientdetailitem, parent, false);

        return new ClientViewAdapter.ClientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, final int position) {
        final HomeModel clienthomeModel = cList.get(position);

        holder.cname.setText(clienthomeModel.getName());
        holder.caddress.setText(clienthomeModel.getAddress());
        holder.cdueamount.setText(String.valueOf("₹ " +clienthomeModel.getCurrentdue()));
        holder.cphone.setText(clienthomeModel.getPhone());

        holder.ccall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",clienthomeModel.getPhone() , null));
                context.startActivity(intent);
            }
        });


        holder.addedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  =  new Intent(context,ClientDetail.class);
                //sending food id to FoodDetail
                intent.putExtra("clientPhone",cList.get(position).getPhone());

                context.startActivity(intent);
            }
        });
//                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();

        holder.addpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  =  new Intent(context,ClientPayHistoryActivity.class);
                //sending food id to FoodDetail
                intent.putExtra("clientPhone",cList.get(position).getPhone());

                context.startActivity(intent);
            }
        });


        holder.addview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  =  new Intent(context,ClientViewActivity.class);
                //sending food id to FoodDetail
                intent.putExtra("clientPhone",cList.get(position).getPhone());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }


    public class ClientViewHolder extends RecyclerView.ViewHolder  {

        TextView cname,cphone,caddress,cdueamount;
        private ItemClickListener itemClickListener;
        Button addedit,addview,addpay;
        ImageButton ccall;

        public ClientViewHolder(View itemView) {
            super(itemView);
            cname =  (TextView) itemView.findViewById(R.id.cname);
            cphone =  (TextView) itemView.findViewById(R.id.cphone);
            caddress =  (TextView) itemView.findViewById(R.id.caddress);
            cdueamount =  (TextView) itemView.findViewById(R.id.cdueamount);
            addedit = (Button) itemView.findViewById(R.id.addedit);
            addview = (Button) itemView.findViewById(R.id.addview);
            addpay = (Button) itemView.findViewById(R.id.addpay);
            ccall =  (ImageButton) itemView.findViewById(R.id.ccall);

        }

    }
}
