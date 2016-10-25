
package com.websystique.springsecurity.model;

import java.util.HashMap;
import java.util.Map;


public class Transliteration {
    private Map dictionary;
    
    public Transliteration(){
        dictionary = new HashMap<String, String>();
        fullDictionary();
    }

    public Map getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map dictionary) {
        this.dictionary = dictionary;
    }
    
    private void fullDictionary(){
        dictionary.put("а", "a");
        dictionary.put("б", "b");
        dictionary.put("в", "v");
        dictionary.put("г", "g");
        dictionary.put("д", "d");
        dictionary.put("е", "e");
        dictionary.put("ё", "e");
        dictionary.put("ж", "zh");
        dictionary.put("з", "z");
        dictionary.put("и", "i");
        dictionary.put("й", "iy");
        dictionary.put("к", "k");
        dictionary.put("л", "l");
        dictionary.put("м", "m");
        dictionary.put("н", "n");
        dictionary.put("о", "o");
        dictionary.put("п", "p");
        dictionary.put("р", "r");
        dictionary.put("c", "s");
        dictionary.put("т", "t");
        dictionary.put("у", "y");
        dictionary.put("ф", "f");
        dictionary.put("х", "kh");
        dictionary.put("ц", "ts");
        dictionary.put("ч", "сh");
        dictionary.put("ш", "sh");
        dictionary.put("щ", "tsc");
        dictionary.put("ъ", "");
        dictionary.put("ы", "i");
        dictionary.put("ь", "");
        dictionary.put("э", "e");
        dictionary.put("ю", "u");
        dictionary.put("я", "ya");
    }
    
}
