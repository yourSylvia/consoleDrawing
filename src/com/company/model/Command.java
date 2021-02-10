package com.company.model;

import java.util.List;

/**
 * @author sylvia
 */
public class Command {
    public String type;
    public List<String> params;

    public Command(String type, List<String> params){
        this.type = type;
        this.params = params;
    };
}
