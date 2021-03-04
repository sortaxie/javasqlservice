package com.adorgroup.sorta.sql.util;
import java.util.Map;

/**
 * 排序条件
 */
public class OrderBy {
    private Map<String, String> sort;

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }
}
