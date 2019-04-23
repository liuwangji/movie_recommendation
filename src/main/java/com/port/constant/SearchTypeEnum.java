package com.port.constant;

/**
 * 电影排序类型枚举类
 */
public enum SearchTypeEnum {
    COUNT("热度排序","count"),
    RATE("评分排序","rate"),
    ;
    String cName;
    String type;
    SearchTypeEnum(String cName, String type){
        this.cName = cName;
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public String getcName(){
        return cName;
    }
    public SearchTypeEnum getByCName(String cName){
        for (SearchTypeEnum searchTypeEnum: SearchTypeEnum.values()){
            if(searchTypeEnum.getcName().equals(cName)){
                return searchTypeEnum;
            }
        }
        return null;
    }
}
