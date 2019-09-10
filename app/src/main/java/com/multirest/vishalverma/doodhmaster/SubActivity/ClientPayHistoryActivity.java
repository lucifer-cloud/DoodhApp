package com.multirest.vishalverma.doodhmaster.SubActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.multirest.vishalverma.doodhmaster.Common.CommonKey;
import com.multirest.vishalverma.doodhmaster.Common.FirebaseInitilization;
import com.multirest.vishalverma.doodhmaster.Model.HomeModel;
import com.multirest.vishalverma.doodhmaster.Model.PayHistoryModel;
import com.multirest.vishalverma.doodhmaster.R;
import com.multirest.vishalverma.doodhmaster.ViewAdapter.ClientViewAdapter;
import com.multirest.vishalverma.doodhmaster.ViewAdapter.PayHistoryViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClientPayHistoryActivity extends AppCompatActivity {

    String clientphone,uid;
    private RecyclerView recyclerView;
    private PayHistoryViewAdapter payhistoryViewAdapter;
    private List<PayHistoryModel> paylistList =  new ArrayList<>();
    Context context;
    HomeModel homeModel;
    TextView cphname,cphphone,cphaddreaa,cphadate,cphdue,cphcattle;
    Button updatepay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_pay_history);

        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.header);

        cphname = (TextView) findViewById(R.id.cphname);
        cphphone = (TextView) findViewById(R.id.cphphone);
        cphaddreaa = (TextView) findViewById(R.id.cphaddress);
        cphadate = (TextView) findViewById(R.id.cphdate);
        cphcattle = (TextView) findViewById(R.id.cphcattle);
        cphdue = (TextView) findViewById(R.id.cphdue);

        updatepay = (Button) findViewById(R.id.cphbtn);

        Typeface typeface = Typeface.createFromAsset(ClientPayHistoryActivity.this.getAssets(), "Fonts/LucidaGrande.ttf");
        cphname.setTypeface(typeface);



        recyclerView = (RecyclerView) findViewById(R.id.cprecy);
        payhistoryViewAdapter = new PayHistoryViewAdapter(paylistList,context);
        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);

        header.attachTo(recyclerView);

        if (getIntent() != null) {
            clientphone = getIntent().getStringExtra("clientPhone");


        }

        updatepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClientPayHistoryActivity.this,ClientPayActivity.class);
                intent.putExtra("clientPhone",clientphone);
                intent.putExtra("clientdue",homeModel.getCurrentdue());

                startActivity(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


        CommonKey commonKey = new CommonKey();
        uid = commonKey.uidnull(this);


        FirebaseInitilization firebaseInitilization = new FirebaseInitilization();
        firebaseInitilization.amountPaid.child(uid).child(clientphone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                paylistList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    PayHistoryModel clientModel = dataSnapshot1.getValue(PayHistoryModel.class);
                    clientModel.setDate(clientModel.getDate());
                    clientModel.setAmount(clientModel.getAmount());



                    paylistList.add(clientModel);
                }
                recyclerView.setAdapter(payhistoryViewAdapter);

            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebaseInitilization.users.child(uid).child(clientphone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                homeModel = dataSnapshot.getValue(HomeModel.class);
                cphname.setText(String.valueOf(homeModel.getName()));
                cphphone.setText(String.valueOf(homeModel.getPhone()));
                cphaddreaa.setText(String.valueOf(homeModel.getAddress()));
                cphadate.setText(String.valueOf(homeModel.getDate()));
                cphdue.setText(String.valueOf("₹ " + homeModel.getCurrentdue()));
                cphcattle.setText(String.valueOf(homeModel.getCattle()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
