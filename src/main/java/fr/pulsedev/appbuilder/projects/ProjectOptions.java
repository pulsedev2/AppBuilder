package fr.pulsedev.appbuilder.projects;

import java.util.HashMap;

public class ProjectOptions<A> {

    public HashMap<String, A> params = new HashMap<>(){{
        for(ProjectOptionType projectOptionType : ProjectOptionType.values()){
            put(projectOptionType.name().toLowerCase(), (A) projectOptionType.defaultValue);
        }
    }};

    public A get(String param){
        for(String key: params.keySet()){
            if(key.equalsIgnoreCase(param)){
                return params.get(param);
            }
        }
        return null;
    }

    public void set(String param, A value){
        if(params.containsKey(param)){
            params.replace(param, params.get(param), value);
        } else{
            params.put(param, value);
        }
    }
}
