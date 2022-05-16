package com.studygram.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class StringUtil {

    public static List<String> getTagsFromContent(String content) {
        Pattern TAG_PATTERN = Pattern.compile("#(\\S+)");
        Matcher matcher = TAG_PATTERN.matcher(content);

        List<String> tags = new ArrayList<>();
        while(matcher.find()) {
            String tag = matcher.group(1);
            if(tag.contains("#")) {
                tags = Arrays.asList(tag.split("#"));
            } else {
                tags.add(tag);
            }
        }
        // 혹시 모를 TAG 문자열 중복 제거
        List<String> newTags = tags.stream().distinct().collect(Collectors.toList());
        return newTags;
    }

    public static String getRandomString(int i)
    {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(i);

        for (int m = 0; m < i; m++) {

            // generate numeric
            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }
}
