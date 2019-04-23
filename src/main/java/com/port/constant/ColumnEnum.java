package com.port.constant;

public enum ColumnEnum {
    NICK_NAME("nickName"),

    PASSWORD("password"),

    MOVIEID("movieId"),

    USERID("userId"),

    MOVIE_TITLE("title"),

    GENRES("genres"),

    RATING("rating"),

    IMG_URL("imgUrl"),

    YEAR("year"),

    URL("url");
    String name;

    ColumnEnum(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
