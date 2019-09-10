package com.multirest.vishalverma.doodhmaster.SubActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.multirest.vishalverma.doodhmaster.CalendarPkg.HwAdapter;
import com.multirest.vishalverma.doodhmaster.CalendarPkg.HomeCollection;
import com.multirest.vishalverma.doodhmaster.Common.CommonKey;
import com.multirest.vishalverma.doodhmaster.Common.FirebaseInitilization;
import com.multirest.vishalverma.doodhmaster.Model.CalendardaysModel;
import com.multirest.vishalverma.doodhmaster.Model.HomeModel;
import com.multirest.vishalverma.doodhmaster.R;
import com.multirest.vishalverma.doodhmaster.ViewAdapter.CalenderActivityAdapter;
import com.multirest.vishalverma.doodhmaster.ViewAdapter.ClientViewAdapter;

public class ClientViewActivity extends AppCompatActivity {
    public GregorianCalendar cal_month, cal_month_copy;
    private HwAdapter hwAdapter;
    private TextView tv_month;
    String uid, clientphone;
    RecyclerView clarecy;
    private static SimpleDateFormat inSDF = new SimpleDateFormat("dd-MM-yyyy");
    private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-MM-dd");
    private List<CalendardaysModel> daysList =  new ArrayList<>();
    private CalenderActivityAdapter calenderViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);


        if (getIntent() != null)
            clientphone = getIntent().getStringExtra("clientPhone");

        CommonKey commonKey = new CommonKey();
        if (uid == null){
            uid = commonKey.uidnull(ClientViewActivity.this);
//            Toast.makeText(getActivity(),"null " + commonKey.uidnull(getActivity()),Toast.LENGTH_SHORT).show();
        }
        if (uid == null){
            uid = CommonKey.key;
        }


        clarecy =  (RecyclerView) findViewById(R.id.clarecy);
        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        hwAdapter = new HwAdapter(this, cal_month,HomeCollection.date_collection_arr);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));



        HomeCollection.date_collection_arr=new ArrayList<HomeCollection>();

     loaddata();


//        HomeCollection.date_collection_arr.add( new HomeCollection("2017-07-08" ,"Holi","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2017-07-08" ,"Statehood Day","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2017-08-08" ,"Republic Unian","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2017-07-09" ,"ABC","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2017-06-15" ,"demo","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2017-09-26" ,"weekly off","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-08" ,"Events","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-16" ,"Dasahara","Holiday","this is holiday"));
//        HomeCollection.date_collection_arr.add( new HomeCollection("2018-02-09" ,"Christmas","Holiday","this is holiday"));
//

        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(ClientViewActivity.this);
        clarecy.setLayoutManager(mLayoutManager);

        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 4&&cal_month.get(GregorianCalendar.YEAR)==2017) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(ClientViewActivity.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setPreviousMonth();
                    refreshCalendar();
                }


            }
        });
        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 12&&cal_month.get(GregorianCalendar.YEAR)==2020) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(ClientViewActivity.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setNextMonth();
                    refreshCalendar();
                }
            }
        });
        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(hwAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedGridDate = HwAdapter.day_string.get(position);
                ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, ClientViewActivity.this);
            }

        });
    }

    private void loaddata() {

        FirebaseInitilization firebaseInitilization = new FirebaseInitilization();
        firebaseInitilization.dayRs.child(uid).child(clientphone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daysList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    CalendardaysModel calendardaysModel = dataSnapshot1.getValue(CalendardaysModel.class);
                    String datew = calendardaysModel.getDate();
                    String morning  = calendardaysModel.getMorning();
                    String evening  = calendardaysModel.getEvening();

                    if (morning == null){
                        morning = "0 - 0.0";
                    }
                    if (evening == null){
                        evening = "0 - 0.0";

                    }

                    daysList.add(calendardaysModel);

                    HomeCollection.date_collection_arr.add( new HomeCollection(formatDate(calendardaysModel.getDate()), datew,morning,evening));

                }

                calenderViewAdapter = new CalenderActivityAdapter(daysList,ClientViewActivity.this);
                clarecy.setAdapter(calenderViewAdapter);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        hwAdapter.refreshDays();
        hwAdapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
    }

    public static String formatDate(String inDate) {
        String outDate = "";
        if (inDate != null) {
            try {
                Date date = inSDF.parse(inDate);
                outDate = outSDF.format(date);
            } catch (ParseException ex){
            }
        }
        return outDate;
    }

    @Override
    protected void onStart() {
        super.onStart();

        loaddata();
        refreshCalendar();
    }
}
