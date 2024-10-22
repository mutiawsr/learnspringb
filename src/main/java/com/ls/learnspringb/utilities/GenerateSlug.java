package com.ls.learnspringb.utilities;

import com.github.slugify.Slugify;

public class GenerateSlug {
    
    final static Slugify slg = Slugify.builder().build();

    public static String generateSlug(String input) {
        return slg.slugify(input);
    }

}
