package com.example.ali.test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ali on 2/8/2017.
 */

public abstract class SuperParser {
    public abstract void setData(String data);
    public abstract List<HashMap<String,String>> getData();
    public abstract void setObjects(String[] objects);
}
