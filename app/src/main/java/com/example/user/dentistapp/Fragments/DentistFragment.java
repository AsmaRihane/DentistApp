package com.example.user.dentistapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.dentistapp.Model.User;
import com.example.user.dentistapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class DentistFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList <String> list_of_users;

   // private UserAdapter userAdapter;
    private List<User> mUsers;

    EditText search_users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstaneceState)
    {
        View view=  inflater.inflate(R.layout.activity_chat_fragment , null);




      listView = view.findViewById(R.id.listView);
   // arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_users);
    //listView.setAdapter(arrayAdapter);

        mUsers = new ArrayList<>();

        readUsers();
        return view;
    }

    private void readUsers() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    }
}
