package io.github.jamelouis.travel_mate.utilities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.jamelouis.travel_mate.R;

public class EmergencyFragment extends Fragment {

    @BindView(R.id.police)    Button police;
    @BindView(R.id.fire) Button fire;
    @BindView(R.id.ambulance) Button ambulance;
    @BindView(R.id.blood_bank) Button blood_bank;
    @BindView(R.id.bomb) Button bomb;
    @BindView(R.id.railways) Button railways;

    public EmergencyFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);

        ButterKnife.bind(this,view);
        return view;
    }
}
