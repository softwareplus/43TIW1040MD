package com.example.belisolbokregistratieapp;

import com.example.belisolbokregistratieapp.dummy.DummyContent;

import java.util.HashMap;
import java.util.Map;

public class Logging
{
    public String id;
    public String messageType;
    public String messageValue;
    public static Map<String, Logging> ITEM_MAP = new HashMap<String, Logging>();
}
