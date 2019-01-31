package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    final static String SANDWICH_NAME = "name";
    final static String SANDWICH_MAIN_NAME = "mainName";
    final static String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
    final static String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
    final static String SANDWICH_IMAGE = "image";
    final static String SANDWICH_INGREDIENTS = "ingredients";
    final static String SANDWICH_DESCRIPTION = "description";


    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichJson = null;
        Sandwich sandwich = null;
        try {
            sandwichJson = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject sandwichNameObject = sandwichJson.getJSONObject(SANDWICH_NAME);
            sandwich.setMainName(sandwichNameObject.getString(SANDWICH_MAIN_NAME));
            sandwich.setAlsoKnownAs(getListProperty(sandwichNameObject, SANDWICH_ALSO_KNOWN_AS));
            sandwich.setPlaceOfOrigin(sandwichJson.getString(SANDWICH_PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJson.getString(SANDWICH_DESCRIPTION));
            sandwich.setIngredients(getListProperty(sandwichJson, SANDWICH_INGREDIENTS));
            sandwich.setImage(sandwichJson.getString(SANDWICH_IMAGE));
        } catch(JSONException jsonEx){
            jsonEx.printStackTrace();
        }
        return sandwich;
    }

    public static List<String> getListProperty(JSONObject jsonObj, String propertyName) throws JSONException {
        JSONArray propertyArray = jsonObj.getJSONArray(propertyName);
        List<String> objectProperty = new ArrayList<String>();

        for (int i = 0; i < propertyArray.length(); i++) {
            objectProperty.add(propertyArray.getString(i));
        }
        return objectProperty;
    }
}
