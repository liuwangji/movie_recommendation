package com.bjut.util;

import com.bjut.domain.MovieDO;
import com.bjut.domain.RatingDO;
import com.bjut.domain.TagDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换工具类
 */
public class TransferUtil {

    public static MovieDO transfer2MovieDO(int movieId, String title, String genres){
        title = title.replaceAll("\"","");
        title = title.replaceAll(","," ");
        genres = genres.replaceAll(","," ");
        MovieDO movie = new MovieDO();
        movie.setMovieId(movieId);
        movie.setMovieTitle(title);
        movie.setGenres(genres.replaceAll(",","|"));
        return movie;
    }

    public static RatingDO transfer2RatingDO(int userId, int movieId, Double rating){
        RatingDO userRating = new RatingDO();
        userRating.setUserId(userId);
        userRating.setMovieId(movieId);
        userRating.setRating(rating);
        return userRating;
    }

    public static TagDO transfer2TagDO(int userId, int movieId, String tag, String timeStamp){
        TagDO tagDO = new TagDO();
        tagDO.setUserId(userId);
        tagDO.setMovieId(movieId);
        tagDO.setTag(tag);
        tagDO.setTimeStamp(timeStamp);
        return tagDO;
    }

    public static List<Integer> transferRatingDO2Integer(List<RatingDO> ratingDOList){
        List<Integer> result = new ArrayList<Integer>();
        for (RatingDO ratingDO: ratingDOList){
            result.add(ratingDO.getMovieId());
        }
        return result;
    }
}
