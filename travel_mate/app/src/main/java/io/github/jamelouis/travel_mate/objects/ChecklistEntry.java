package io.github.jamelouis.travel_mate.objects;


import android.provider.BaseColumns;

public class ChecklistEntry implements BaseColumns {
    public static final String TABLE_NAME = "events_new";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NAME_ISDONE = "isdone";
}
