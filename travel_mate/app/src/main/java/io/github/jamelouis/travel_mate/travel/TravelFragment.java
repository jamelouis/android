package io.github.jamelouis.travel_mate.travel;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapters.CardViewOptionsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.jamelouis.travel_mate.R;
import io.github.jamelouis.travel_mate.travel.transport.SelectModeOfTransport;
import io.github.jamelouis.travel_mate.utils.CardItemEntity;

public class TravelFragment extends Fragment implements CardViewOptionsAdapter.OnItemClickListener {

    @BindView(R.id.travel_options_recycle_view)
    RecyclerView mTravelOptionRecycleView;
    private Activity mActivity;

    public TravelFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.content_travel, container, false);
        ButterKnife.bind(this,view);

        List<CardItemEntity> cardEntities = getTravelItems();
        CardViewOptionsAdapter cardViewOptionsAdapter = new CardViewOptionsAdapter(this,cardEntities);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        mTravelOptionRecycleView.setLayoutManager(mLayoutManager);
        mTravelOptionRecycleView.setItemAnimator(new DefaultItemAnimator());
        mTravelOptionRecycleView.setAdapter(cardViewOptionsAdapter);

        return view;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity)activity;
    }

    @Override
    public void onItemClick(int position) {
        Intent i;
        switch (position) {
            case 1:
                i = new Intent(mActivity, SelectModeOfTransport.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(mActivity,Hotels.class);
                startActivity(i);
                break;
        }
    }

    private List<CardItemEntity> getTravelItems() {
        List<CardItemEntity> cardEntities = new ArrayList<>();
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.city),
                        getResources().getString(R.string.my_trips)
                )
        );
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.transport),
                        getResources().getString(R.string.transport)
                )
        );
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.hotel),
                        getResources().getString(R.string.hotel)
                )
        );
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.shop),
                        getResources().getString(R.string.online_Shopping)
                )
        );
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.location),
                        getResources().getString(R.string.real_time_locator)
                )
        );
        return cardEntities;
    }
}
