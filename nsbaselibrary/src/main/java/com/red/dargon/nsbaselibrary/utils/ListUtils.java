package com.red.dargon.nsbaselibrary.utils;

import java.util.List;

/**
 * Created by javalong on 16-11-14.
 * <p>
 * List操作的工具类，防止数组越界等情况
 * 所有List操作，最好通过该Utils
 */
public class ListUtils {
    public static <T> T getSafeItem(List<T> list, int index) {
        T item = null;
        if (list == null || index < 0) {
            return item;
        }

        if (index < list.size()) {
            item = list.get(index);
        }
        return item;
    }

    public static <T> T removeSafeItem(List<T> list, int index) {
        if (list != null && list.size() > index) {
            return list.remove(index);
        }
        return null;
    }

    public static <T> void addAllSafeItem(List<T> srcList, List<T> desList) {
        if (srcList != null && desList != null) {
            srcList.addAll(desList);
        }
    }

    public static <T> void clear(List<T> list) {
        if (list != null) {
            list.clear();
        }
    }

    public static <T> void forceSetElement(List<T> list, T t, int index) {
        if (list != null && t != null) {
            while (true) {
                if (list.size() > index) {
                    list.set(index, t);
                    break;
                } else {
                    list.add(null);
                }
            }
        }
    }

    public static <T> void addSafeItem(List<T> list, int index, T obj) {
        if (index < 0) {
            return;
        }
        if (list != null && index <=
                list.size()) {
            list.add(index, obj);
        }
    }

    public static boolean isEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static int getSize(List<?> list) {
        if (list == null)
            return 0;
        return list.size();
    }


    public static <T> int indexOf(List<?> list, T obj) {
        if (list == null)
            return -1;
        return list.indexOf(obj);
    }

    public static <T> int lastIndexOf(List<?> list, T obj) {
        if (list == null)
            return -1;
        return list.lastIndexOf(obj);
    }

    public static <T> T getLastItem(List<T> list) {
        if (list == null || list.size() == 0) return null;
        return list.get(list.size() - 1);
    }
}
