package com.hef.util;

import org.apache.commons.lang3.StringUtils;

public enum MyExcelTypeEnum {
    XLS("xls"), XLSX("xlsx");

    private String suffix;

    MyExcelTypeEnum(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    /**
     * 查询excel类型
     * @param suffix
     * @return
     */
    public static MyExcelTypeEnum findExcelSuffixEnum(String suffix) {
        for (MyExcelTypeEnum item : values()) {
            if (StringUtils.equalsIgnoreCase(item.suffix, suffix)) {
                return item;
            }
        }
        return null;
    }
}
