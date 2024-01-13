package com.learning.customcollection;

import java.util.ArrayList;

public class UniqueObjectList<T> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        if (containsObject(t)) {
            return false;
        }
        return super.add(t);
    }

    private boolean containsObject(T object) {
        for (T element : this) {
            if (object.equals(element)) return true;
        }
        return false;
    }
}
