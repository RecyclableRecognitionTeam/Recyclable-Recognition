package com.trash.recyclablerecognition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public class Disposables {

    public List<String> getRecyclables(){
        return Arrays.asList("paper","aluminum","aerosol" , "carton","glass","tin can", "newspaper", "cardboard", "bottle", "can","magazine","envelope","plastic","book","tray", "sheet", "writing", "document");
    }

    public List<String> getTrash(){
        return Arrays.asList("plate", "food", "utensil", "napkin" ,"plastic bag","foam","light bulb", "straw","cup","cap","diaper");
    }

    public boolean isRecyclable(String obj){
        List<String> recycle = getRecyclables();
        return recycle.contains(obj);
    }

    public boolean isTrash(String obj){
        List<String> trash = getTrash();
        return trash.contains(obj);
    }
}
