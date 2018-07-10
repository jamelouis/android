package io.github.jamelouis.travel_mate.objects;


import io.github.jamelouis.travel_mate.utilities.Checklist;

public class ChecklistItem {
    private final String mId;
    private final String mName;
    private final String mIsDone;

    public ChecklistItem(String id, String name, String isDonw) {
        this.mId = id;
        this.mIsDone = isDonw;
        this.mName = name;
    }

    public String getiq() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getIsDone() {
        return mIsDone;
    }
}
