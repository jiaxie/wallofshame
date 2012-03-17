package com.wallofshame.domain;

import com.wallofshame.controller.ShameController;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private ArrayList<String> names;


    public static PeopleMissingTimeSheet getInstance(){
        return instance;
    }

    private PeopleMissingTimeSheet() {
        names = new ArrayList<String>();
    }

    public void addName(String name) {
        names.add(name);
    }
    
    public void replaceAll(List<String> names){
        this.names.clear();
        this.names.addAll(names);
    }

    public List<String> names() {
        return names;
    }
}
