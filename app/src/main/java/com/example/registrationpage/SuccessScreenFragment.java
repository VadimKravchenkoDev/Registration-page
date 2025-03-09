package com.example.registrationpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SuccessScreenFragment extends Fragment {

    private TextView textViewResult;

    public static SuccessScreenFragment newInstance(String name, String surname) {
        SuccessScreenFragment fragment = new SuccessScreenFragment();
        Bundle args = new Bundle();
        args.putString("NAME", name);
        args.putString("SURNAME", surname);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.success_screen, container, false);

        textViewResult = view.findViewById(R.id.textLeft);

        if (getArguments() != null) {
            String name = getArguments().getString("NAME");
            String surname = getArguments().getString("SURNAME");

            textViewResult.setText(name + " " + surname);
        }
        return view;
    }
}
