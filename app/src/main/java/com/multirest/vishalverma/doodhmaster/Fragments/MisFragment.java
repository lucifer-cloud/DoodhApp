package com.multirest.vishalverma.doodhmaster.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.multirest.vishalverma.doodhmaster.Common.CommonKey;
import com.multirest.vishalverma.doodhmaster.Common.FirebaseInitilization;
import com.multirest.vishalverma.doodhmaster.Model.VendorGeneralModel;
import com.multirest.vishalverma.doodhmaster.Model.VendorGlobalPriceModel;
import com.multirest.vishalverma.doodhmaster.OTPvarification.OTOvarificationActivity;
import com.multirest.vishalverma.doodhmaster.OwnerPkg.UpdateOwnerActivity;
import com.multirest.vishalverma.doodhmaster.OwnerPkg.WorkLocationActivity;
import com.multirest.vishalverma.doodhmaster.R;

import java.util.Objects;


public class MisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String uid;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MisFragment() {
        // Required empty public constructor
    }

    public static MisFragment newInstance(String param1, String param2) {
        MisFragment fragment = new MisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis, container, false);
       final TextView  fmn = (TextView) view.findViewById(R.id.fmn);
        final TextView  fmphone = (TextView) view.findViewById(R.id.fmphone);
        final TextView  fmemail = (TextView) view.findViewById(R.id.fmemail);
        final TextView  fclp = (TextView) view.findViewById(R.id.fclp);
        final TextView  fclhp = (TextView) view.findViewById(R.id.fclhp);
        final TextView  fblp = (TextView) view.findViewById(R.id.fblp);
        final TextView  fblhp = (TextView) view.findViewById(R.id.fblhp);
        final TextView fvt  = (TextView) view.findViewById(R.id.fvt);
        final  Button buttoedit = (Button) view.findViewById(R.id.buttoedit);
        final  Button btnworkloc = (Button) view.findViewById(R.id.btnworkloc);



        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Fonts/LucidaGrande.ttf");
        fvt.setTypeface(typeface);

        CommonKey commonKey = new CommonKey();
            uid = commonKey.uidnull(getActivity());



        FirebaseInitilization firebaseInitilization = new FirebaseInitilization();
        firebaseInitilization.owner.child(uid).child("General").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VendorGeneralModel vendorGeneralModel = dataSnapshot.getValue(VendorGeneralModel.class);
                fmn.setText(String.valueOf(vendorGeneralModel.getName()));
                fmphone.setText(String.valueOf(vendorGeneralModel.getPhone()));
                fmemail.setText(String.valueOf(vendorGeneralModel.getEmail()));


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        firebaseInitilization.owner.child(uid).child("GlobalPrice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VendorGlobalPriceModel vendorGlobalPriceModel = dataSnapshot.getValue(VendorGlobalPriceModel.class);
                fclp.setText(String.valueOf(vendorGlobalPriceModel.getCowLitPrice()));
                fclhp.setText(String.valueOf(vendorGlobalPriceModel.getCowhalfLitPrice()));
                fblp.setText(String.valueOf(vendorGlobalPriceModel.getBuffaloLitPrice()));
                fblhp.setText(String.valueOf(vendorGlobalPriceModel.getBuffalohalfLitPrice()));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        view.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), OTOvarificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                getActivity().startActivity(intent);
            }
        });

        view.findViewById(R.id.buttoedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), UpdateOwnerActivity.class);
                intent.putExtra("phno", fmphone.getText().toString());
                intent.putExtra("uidw", uid);

                getActivity().startActivity(intent);
            }
        });



        view.findViewById(R.id.btnworkloc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getContext(), WorkLocationActivity.class);
                intent.putExtra("phno", fmphone.getText().toString());
                intent.putExtra("uidw", uid);
                intent.putExtra("ina", "misfrag");


                getActivity().startActivity(intent);
            }
        });




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
