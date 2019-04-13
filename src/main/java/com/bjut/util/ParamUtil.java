package com.bjut.util;

/**
 * 参数处理工具类
 */
public class ParamUtil {
    /**
     * page默认值为1
     * @param page
     * @return
     */
    public static int dealPage(Integer page){
        if(page==null || page<1){
            return 1;
        }else{
           return page;
        }
    }

    /**
     * pageCount默认为10
     * @param pageCount
     * @return
     */
    public static int dealPageCount(Integer pageCount){
        if(pageCount == null || pageCount<1){
            return 10;
        }else{
            return pageCount;
        }
    }
}
