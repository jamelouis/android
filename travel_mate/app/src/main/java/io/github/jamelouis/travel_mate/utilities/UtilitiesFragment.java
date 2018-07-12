package io.github.jamelouis.travel_mate.utilities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapters.CardViewOptionsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.jamelouis.travel_mate.R;
import io.github.jamelouis.travel_mate.utils.CardItemEntity;

public class UtilitiesFragment extends Fragment implements CardViewOptionsAdapter.OnItemClickListener {

    @BindView(R.id.utility_options_recycle_view) RecyclerView mUtilityOptionsRecycleView;
    private Activity mActivity;

    public UtilitiesFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_utility, container, false);
        ButterKnife.bind(this,view);

        List<CardItemEntity> cardItemEntities = getUtilityItems();
        CardViewOptionsAdapter cardViewOptionsAdapter = new CardViewOptionsAdapter(this,cardItemEntities);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        mUtilityOptionsRecycleView.setLayoutManager(mLayoutManager);
        mUtilityOptionsRecycleView.setItemAnimator(new DefaultItemAnimator());
        mUtilityOptionsRecycleView.setAdapter(cardViewOptionsAdapter);

        return view;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(mActivity, ShareContact.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(mActivity, Checklist.class);
                startActivity(intent);
                break;
        }
    }
    List<CardItemEntity> getUtilityItems() {
        List<CardItemEntity> cardItemEntities = new ArrayList<>();
        cardItemEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.contact),
                        getResources().getString(R.string.share_contact_text)
                )
        );
        cardItemEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.checklist),
                        getResources().getString(R.string.text_checklist)
                )
        );
        return cardItemEntities;
    }
}
