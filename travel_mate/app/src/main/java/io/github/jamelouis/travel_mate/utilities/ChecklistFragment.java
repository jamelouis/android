package io.github.jamelouis.travel_mate.utilities;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.jamelouis.travel_mate.R;

public class ChecklistFragment extends Fragment {
    
    public ChecklistFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.content_check_list, container, false);
        return view;
    }
}
