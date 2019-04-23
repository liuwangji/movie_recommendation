package com.port.util;

import com.port.domain.CompleteDO;
import com.port.domain.MovieDO;
import com.port.domain.RatingDO;
import com.port.domain.TagDO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换工具类
 */
public class TransferUtil {

    public static CompleteDO transfer2CompleteDO(int userId, int movieId, String title, String year, String genres, String imgUrl, String url, Double rating){
if(StringUtils.isEmpty(imgUrl)){
    imgUrl="http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E6%89%BE%E4%B8%8D%E5%88%B0%E5%9B%BE%E7%89%87" +
            "&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=undefined&latest=undefined&copyright=undefined&cs=427750889,3204769508&os=1245664701,657059753" +
            "&simid=3403646119,249124006&pn=388&rn=1&di=18428603930&ln=1815&fr=&fmq=1555855239885_R&fm=rs7&ic=undefined&s=undefined&se=&sme=&tab=0" +
            "&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=14a&oriquery=%E6%89%BE%E4%B8%8D%E5%88%B0%E7" +
            "%94%B5%E5%BD%B1%E5%9B%BE%E7%89%87%E9%BB%98%E8%AE%A4%E7%9A%84%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Ffile25.mafengwo.net%2FM00%2FA8%2FC6%2FwKgB4lI8kSCAZZt9AAPxubTv5-g38" +
            ".groupinfo.w600.jpeg&rpstart=0&rpnum=0&adpicid=0&force=undefined";
}
        CompleteDO info = new CompleteDO();
        info.setUserId(userId);
        info.setMovieId(movieId);
        info.setMovieTitle(title);
        info.setYear(year);
        info.setGenres(genres);
        info.setImgUrl(imgUrl);

        info.setRating(rating);
        return info;
    }

    public static MovieDO transfer2MovieDO(int movieId, String title, String year, String genres, String imgUrl, String url){
//        title = title.replaceAll("\"","");
//        title = title.replaceAll(","," ");
//        genres = genres.replaceAll(","," ");
        MovieDO movie = new MovieDO();
        movie.setMovieId(movieId);
        movie.setMovieTitle(title);
        movie.setYear(year);
        movie.setImgUrl(imgUrl);
        movie.setUrl(url);
        movie.setGenres(genres);
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
