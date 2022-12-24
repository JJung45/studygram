package com.studygram.utils;

import com.studygram.domain.Post;

import java.util.Comparator;

public class ListComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int likeCnt1 = ((Post)o1).getLikeCnt();
        int likeCnt2 = ((Post)o2).getLikeCnt();

        if(likeCnt1 > likeCnt2) {
            return 1;
        } else if (likeCnt1 < likeCnt2) {
            return -1;
        } else {
            return 0;
        }
    }
}
