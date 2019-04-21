package com.droidverine.hdds.hdds.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.droidverine.hdds.hdds.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
CircleImageView circleImageView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth firebaseAuth;

    private OnFragmentInteractionListener mListener;
    FirebaseDatabase database;
    String Height,Weight,Contactno;
    Button edtbtn;
    Dialog myDialog;
    TextView txtweight,txtheight,txtcontact;
    FloatingActionButton floatingActionButton;
    public ProfileFragment() {
        // Required empty public constructor init check
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("Profile");
        TextView textView=(TextView)view.findViewById(R.id.usernameprofile);
        textView.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        circleImageView=(CircleImageView)view.findViewById(R.id.avatar);
        firebaseAuth=FirebaseAuth.getInstance();
//        Log.d("dp",FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().normalizeScheme().toString());
     //   Picasso.with(getActivity()).load(R.drawable.ic_user).into(circleImageView);

        edtbtn=(Button)view.findViewById(R.id.edtbtn);
        txtweight=(TextView)view.findViewById(R.id.txtweight);
        txtheight=(TextView)view.findViewById(R.id.txtheight);
        txtcontact=(TextView)view.findViewById(R.id.txtcontact);

        getData();

      /*  floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(getContext());

                TextView txtclose,particollege,username;
                final EditText contactno,height,weight;
                Button btncall;
                myDialog.setContentView(R.layout.custom_popup);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                btncall = (Button) myDialog.findViewById(R.id.btncall);
                myDialog.setContentView(R.layout.custom_popup);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                username=(TextView)myDialog.findViewById(R.id.partiname);
                particollege=(TextView)myDialog.findViewById(R.id.collegename);
                contactno=(EditText)myDialog.findViewById(R.id.customdiagcontactnoedt);
                height=(EditText) myDialog.findViewById(R.id.customdiagheightedt);
                weight=(EditText) myDialog.findViewById(R.id.customdiagweightedt);
                txtclose.setText("X");
                btncall = (Button) myDialog.findViewById(R.id.btncall);
                btncall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Height=height.getText().toString();
                        Weight=weight.getText().toString();
                        Contactno=contactno.getText().toString();
                        updateData();
                    }
                });
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
*/
   /*     edtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(getContext());

                TextView txtclose,particollege,username;
                final EditText contactno,height,weight;
                Button btncall;
                myDialog.setContentView(R.layout.custom_popup);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                username=(TextView)myDialog.findViewById(R.id.partiname);
                particollege=(TextView)myDialog.findViewById(R.id.collegename);
                contactno=(EditText)myDialog.findViewById(R.id.customdiagcontactnoedt);
                height=(EditText) myDialog.findViewById(R.id.customdiagheightedt);
                weight=(EditText) myDialog.findViewById(R.id.customdiagweightedt);
                txtclose.setText("X");
                btncall = (Button) myDialog.findViewById(R.id.btncall);
                btncall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Height=height.getText().toString();
                        Weight=weight.getText().toString();
                        Contactno=contactno.getText().toString();
                        updateData();
                    }
                });
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
        */
        return view;
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
    {             String email=firebaseAuth.getCurrentUser().getEmail();

        database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();//= //database.getReference("patient/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()+"/");
        myRef.keepSynced(true);
        myRef.child("users").child(email.replace(".","_")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    String usrname=dataSnapshot.child("Carmodel").getValue(String.class);
                    String height=dataSnapshot.child("Carno").getValue(String.class);
                String weight=dataSnapshot.child("status").getValue(String.class);
                    txtcontact.setText("Car Model : "+usrname);
                    txtweight.setText("Status : "+weight);
                    txtheight.setText("Car No. : "+height);






            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void updateData() {
        database = FirebaseDatabase.getInstance();
        String email=firebaseAuth.getCurrentUser().getEmail();

        DatabaseReference myRef = database.getReference();
        myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("Car").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                dataSnapshot.getRef().child("CarNumber").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                dataSnapshot.getRef().child("status").setValue(Height);





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });
    }
}
