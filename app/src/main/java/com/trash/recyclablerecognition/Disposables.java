package com.trash.recyclablerecognition;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public class Disposables {
    public List<String> getRecyclables(){
        return new LinkedList<>();
    }

    public List<String> getTrash(){
        return new LinkedList<>();
    }

    public boolean isRecyclable(String obj){
        return true;
    }

    public boolean isTrash(String obj){
        return true;
    }
}
