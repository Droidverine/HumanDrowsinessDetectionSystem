package com.droidverine.hdds.hdds.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.droidverine.hdds.hdds.Adapters.MessagesAdapter;
import com.droidverine.hdds.hdds.R;
import com.droidverine.hdds.hdds.Utils.Connmanager;
import com.droidverine.hdds.hdds.Utils.DetailsManager;
import com.droidverine.hdds.hdds.Utils.HDDS;
import com.droidverine.hdds.hdds.Utils.Offlinedatabase;
import com.droidverine.hdds.hdds.models.Messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseAuth firebaseAuth;

    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    Offlinedatabase offlinedatabase;
    ProgressBar progressBar;
    TextView textView;

    RecyclerView.LayoutManager layoutManager;
    public Context context;
    List<Messages> list;
    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerView recyclerview;
    MessagesAdapter recycler;
    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
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
        View view= inflater.inflate(R.layout.fragment_messages, container, false);
        getActivity().setTitle("Messages");
        firebaseAuth=FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        progressBar = (ProgressBar) view.findViewById(R.id.messages_fragment_progressBar);
        textView=(TextView)view.findViewById(R.id.messages_textview);
        recyclerview = (RecyclerView)view.findViewById(R.id.msgrecyler);
        Connmanager connmanager=new Connmanager(getActivity());

        if(connmanager.checkNetworkConnection())
        {
            offlinedatabase=new Offlinedatabase(getContext());
             String email=firebaseAuth.getCurrentUser().getEmail();

            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();//= //database.getReference("patient/"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString()+"/");
            myRef.keepSynced(true);
            myRef.child("users").child(email.replace(".","_")).child("logs").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    offlinedatabase.truncateNotifications();

                    // StringBuffer stringbuffer = new StringBuffer();

                    for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                        Log.d("he ","chlla"+ dataSnapshot);

                        Messages messages = dataSnapshot1.getValue(Messages.class);
                        Messages listdata = new Messages();
                        String head=messages.getMsghead();
                        String body=messages.getMsgtext();
                        long time=messages.getMsgtime();
                        String te=String.valueOf(time);
                        String uid=dataSnapshot1.getKey();
                    //    Log.d("bcala",""+uid);
                        listdata.setUid(uid);
                      //  Log.d("bcala",""+listdata.getUid());

                        listdata.setMsghead(head);
                        listdata.setMsgtext(body);
                        listdata.setMsgtime(time);
                        list.add(listdata);
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put(DetailsManager.MESSAGES_COLUMN_TITLE,head);
                        hashMap.put(DetailsManager.MESSAGES_COLUMN_BODY,body);
                        hashMap.put(DetailsManager.MESSAGES_COLUMN_TIME,te);
                        hashMap.put(DetailsManager.MESSAGES_UID,uid);

                        offlinedatabase.insertNotifications(hashMap);
                        // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                    }
                    new getMessagesFromDb().execute();




                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                      Log.w("error", "Failed to read value.", error.toException());
                }
            });

        }


        new getMessagesFromDb().execute();

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
    public class getMessagesFromDb extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {
            Log.d("MessageFragment", "onPreExecute ");
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params)
        {


            Offlinedatabase offlinedatabase=new Offlinedatabase( HDDS.getInstance().getApplicationContext());
            list= offlinedatabase.getAllNotifications();


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            recycler = new MessagesAdapter(getActivity(),list);
            LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
            layoutmanager.setReverseLayout(false);
            layoutmanager.setStackFromEnd(false);
            recyclerview.setLayoutManager(layoutmanager);
            recyclerview.setItemAnimator( new DefaultItemAnimator());

            recyclerview.setAdapter(recycler);

            progressBar.setVisibility(View.GONE);
            if (list.isEmpty())
            {
                textView.setVisibility(View.VISIBLE);
            }
        }
    }
}
