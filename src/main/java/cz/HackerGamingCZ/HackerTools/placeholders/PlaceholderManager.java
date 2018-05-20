package cz.HackerGamingCZ.HackerTools.placeholders;

import java.util.ArrayList;
import java.util.Collections;

public class PlaceholderManager {

    public void updatePlaceHolders(){
        for(Placeholder placeholder : Placeholder.values()){
            placeholder.update();
        }
    }

    public String replaceString(String string){
        if(string == null){
            return null;
        }
        updatePlaceHolders();
        for(Placeholder placeholder : Placeholder.values()){
            if(placeholder.getReplacement() == null){
                continue;
            }
            string = string.replace(placeholder.getPlaceholder(), placeholder.getReplacement());
        }
        return string;
    }

    public String replaceString(String string, Placeholder[] ignore){
        updatePlaceHolders();
        ArrayList<Placeholder> ignoreList = new ArrayList<>();
        Collections.addAll(ignoreList, ignore);
        for(Placeholder placeholder : Placeholder.values()){
            if(ignoreList.contains(placeholder) || placeholder.getReplacement() == null){
                continue;
            }
            string = string.replace(placeholder.getPlaceholder(), placeholder.getReplacement());
        }
        return string;
    }

    public String replaceSpecialPlaceholder(String string, Placeholder placeholder, String replacement){
        updatePlaceHolders();
        string = string.replace(placeholder.getPlaceholder(), replacement);
        return string;
    }

}
