package com.port.constant;

public enum MovieCategoriesEnum {
    ALL("全部类型","All"),
    DRAMA("戏剧", "Drama"),
    HORROR("恐怖", "Horror"),
    ADVENTURE("冒险", "Adventure"),
    ANIMATION("动物", "Animation"),
    CHILDREN("儿童","children"),
    COMEDY("喜剧","Comedy"),
    FANTASY("奇幻","Fantasy"),
    ROMANCE("浪漫","Romance"),
    THRILLER("惊悚","Thriller"),
    ACTION("动作","Action"),
    WAR("战争","War"),
    CRIME("犯罪","Crime"),
    Fantasy("神秘","Mystery"),
    MUSICAL("音乐","Musical"),
    SCIFI("科幻","Sci-Fi"),
    ;
    String cName;
    String category;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    MovieCategoriesEnum(String cName, String category) {
        this.cName = cName;
        this.category = category;
    }
}
