package com.baktiyar.splitter;

import java.util.ArrayList;
import java.util.List;


public class StringSplitter {
    public String[] splitWithQuotes(String input) {
        boolean insideQuotes = false;
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                if (insideQuotes) {
                    tokens.add(current.toString());
                    current.setLength(0);
                    insideQuotes = false;
                } else {
                    if (current.length() > 0) {
                        tokens.add(current.toString());
                        current.setLength(0);
                    }
                    insideQuotes = true;
                }
            } else if (c == ' ' && !insideQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        
        return tokens.toArray(String[]::new);
    }
}
