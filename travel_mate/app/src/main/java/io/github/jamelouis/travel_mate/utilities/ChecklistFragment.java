package io.github.jamelouis.travel_mate.utilities;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rey.material.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.jamelouis.travel_mate.R;
import io.github.jamelouis.travel_mate.objects.ChecklistEntry;
import io.github.jamelouis.travel_mate.objects.ChecklistItem;

import static io.github.jamelouis.travel_mate.utils.Constants.BASE_TASKS;
import static io.github.jamelouis.travel_mate.utils.Constants.ID_ADDED_INDB;

public class ChecklistFragment extends Fragment {

    private final ArrayList<ChecklistItem> mItems = new ArrayList<>();
    @BindView(R.id.listview) ListView listview;
    private Activity mActivity;
    private ChecklistAdapter mAdapter;
    

    public ChecklistFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.content_check_list, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new ChecklistAdapter(mActivity,mItems);
        listview.setAdapter(mAdapter);

        addDefaultItems();


        return view;
    }

    private void addDefaultItems() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String isAlreadyAdded = sharedPreferences.getString(ID_ADDED_INDB, "null");
        if(isAlreadyAdded.equals("null")) {
            for(int i=0;i<BASE_TASKS.size(); i++){
                //ContentValues insertValues = new ContentValues();
                //insertValues.put(ChecklistEntry.COLUMN_NAME, BASE_TASKS.get(i));
                //insertValues.put(ChecklistEntry.COLUMN_NAME_ISDONE, "0");

                mItems.add(new ChecklistItem(Integer.toString(i),BASE_TASKS.get(i),"0"));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    class ChecklistAdapter extends ArrayAdapter<ChecklistItem> {
        private final Activity mContext;
        private final List<ChecklistItem> mItems;

        ChecklistAdapter(Activity context, List<ChecklistItem> items) {
            super(context, R.layout.checklist_item, items);
            this.mContext = context;
            this.mItems = items;
        }

        @NonNull
        @Override
        public View getView(final int position, View view, @NonNull ViewGroup parent) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            ViewHolder holder;
            if(view == null ){
                view = inflater.inflate(R.layout.checklist_item, parent, false);
                holder = new ViewHolder();
                holder.checkBox = view.findViewById(R.id.cb1);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            if(mItems.get(position).getIsDone().equals("1")){
                holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                holder.checkBox.setChecked(true);
            }else {
                holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags()&(~ Paint.STRIKE_THRU_TEXT_FLAG));
                holder.checkBox.setChecked(false);
            }

            holder.checkBox.setText(mItems.get(position).getName());
            return view;
        }

        class ViewHolder {
            CheckBox checkBox;
        }
    }
}
