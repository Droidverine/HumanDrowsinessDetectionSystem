package com.droidverine.hdds.hdds.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.droidverine.hdds.hdds.Activities.MapsActivity;
import com.droidverine.hdds.hdds.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView carsts,carno,carmod,carloc;
    Button mapview;
    String lat,longi;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View rootview=inflater.inflate(R.layout.fragment_home, container, false);
        carmod=rootview.findViewById(R.id.carmodel);
        carno=rootview.findViewById(R.id.carnumber);
        carsts=rootview.findViewById(R.id.carsts);
        mapview=rootview.findViewById(R.id.mapbtn);
        mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MapsActivity.class);
                if (lat!=null) {
                    intent.putExtra("lat", lat);
                    intent.putExtra("longi",longi);
                    startActivity(intent);
                }
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        getData();
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    private void getData()
    {   String email=firebaseAuth.getCurrentUser().getEmail();
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();//= //database.getReference("patient/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()+"/");
        myRef.keepSynced(true);

        myRef.child("users").child(email.replace(".","_")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("hotai", "onDataChange: "+dataSnapshot);

                String model=dataSnapshot.child("Carmodel").getValue(String.class);
                String crnumb=dataSnapshot.child("Carno").getValue(String.class);
                String carstat=dataSnapshot.child("status").getValue(String.class);
                lat=dataSnapshot.child("latitude").getValue(String.class);
                longi=dataSnapshot.child("longitude").getValue(String.class);
                carmod.setText(model);
                carno.setText(crnumb);
                carsts.setText("status :\n"+carstat);






            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
