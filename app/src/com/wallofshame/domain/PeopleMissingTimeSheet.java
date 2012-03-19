package com.wallofshame.domain;

import com.wallofshame.controller.ShameController;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private Map<String, List<String>> names;


    public static PeopleMissingTimeSheet getInstance(){
        return instance;
    }

    private PeopleMissingTimeSheet() {
        names = new HashMap<String, List<String>>();
    }

  //  public void addName(String name) {
  //      names.add(name);
  //  }
    
    public void replaceAll(Map<String,List<String>> names){
        this.names = names;
    }

    public Map<String,List<String>> names() {
        return names;
    }
}
