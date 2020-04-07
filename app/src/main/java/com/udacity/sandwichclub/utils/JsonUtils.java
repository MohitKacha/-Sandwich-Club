package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String NAME = "name";
    private final static String MAIN_NAME = "mainName";
    private final static String ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE_URL = "image";
        private final static String INGREDIENTS = "ingredients";



    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject nameObject = sandwichObject.getJSONObject(NAME);

            String mainName = nameObject.getString(MAIN_NAME);
            JSONArray knownAsJsonArray = nameObject.getJSONArray(ALSO_KNOWN_AS);
            List<String> knownAsListData = getListData(knownAsJsonArray);
            String placeOfOrigin = sandwichObject.getString(PLACE_OF_ORIGIN);
            String description = sandwichObject.getString(DESCRIPTION);
            String imageURL = sandwichObject.getString(IMAGE_URL);

            JSONArray ingredientsJsonArray = sandwichObject.getJSONArray(INGREDIENTS);
            List<String> ingredientsList = getListData(ingredientsJsonArray);

            return new Sandwich(mainName,knownAsListData,placeOfOrigin,description,imageURL,ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    private static  List<String> getListData(JSONArray jsonArray) throws JSONException{


        if(jsonArray == null){
            return  null;
        }
        List<String> listData = new ArrayList<>();
        for(int i = 0; i <jsonArray.length(); i++){
            listData.add(jsonArray.getString(i));
        }

        return listData;
    }
}
