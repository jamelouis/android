package io.github.jamelouis.travel_mate.utils;


import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String USER_TOKEN = "user_token";
    public static final String USER_NAME = "user_name";
    public static final String USER_NUMBER = "user_number";

    public static final String ID_ADDED_INDB = "is_added_in_db";
    public static final List<String> BASE_TASKS = new ArrayList<String>() {
        {
            add("Bags");
            add("Keys");
            add("Charger");
            add("Earphones");
            add("Clothes");
            add("Food");
            add("Tickets");
        }
    };
}
