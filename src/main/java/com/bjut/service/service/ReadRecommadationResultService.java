package com.bjut.service.service;

import com.bjut.dao.MysqlDao;
import com.bjut.domain.MovieDO;
import com.bjut.domain.RatingDO;
import com.bjut.util.ParamUtil;
import com.bjut.util.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐服务
 */
@Service
public class ReadRecommadationResultService {
    @Autowired
    MysqlDao mysqlDao;

    public static String movieTableName;
    public static String preferenceTableName;
    public static String predictTableName;
    public static String tagTableName;

    @Value("${movie.tableName}")
    public void setMovieTableName(String movieTableName) {
        ReadRecommadationResultService.movieTableName = movieTableName;
    }

    @Value("${preference.tableName}")
    public void setPreferenceTableName(String preferenceTableName) {
        ReadRecommadationResultService.preferenceTableName = preferenceTableName;
    }

    @Value("${predict.tableName}")
    public void setPredictTableName(String predictTableName) {
        ReadRecommadationResultService.predictTableName = predictTableName;
    }

    @Value("${tag.tableName}")
    public void setTagTableName(String tagTableName) {
        ReadRecommadationResultService.tagTableName = tagTableName;
    }

    /**
     * 获取用户历史偏好电影
     * @param userId
     * @return
     */
    public List<MovieDO> getPreferenceMovies(Integer userId, int page, int pageCount) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        List<RatingDO> ratings = mysqlDao.getTopMovieRatingList(userId, preferenceTableName,page, pageCount);
        return mysqlDao.getMovieNameList(TransferUtil.transferRatingDO2Integer(ratings), movieTableName);
    }

    /**
     * 获取基于用户的个性化推荐结果
     * @param userId
     * @return
     */
    public List<MovieDO> getRecommdationMovies(Integer userId, int page, int pageCount) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        List<RatingDO> ratings = mysqlDao.getTopMovieRatingList(userId, predictTableName,page, pageCount);
        return mysqlDao.getMovieNameList(TransferUtil.transferRatingDO2Integer(ratings), movieTableName);
    }

    /**
     * 获取大家评分高的电影
     * @param page
     * @param pageCount
     * @return
     */
    public List<MovieDO> getPeopleLikeByRatingMovies(int page, int pageCount) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        List<RatingDO> ratings = mysqlDao.getTopMovieAvgRatingList(preferenceTableName,page, pageCount);
        return mysqlDao.getMovieNameList(TransferUtil.transferRatingDO2Integer(ratings), movieTableName);
    }

    /**
     * 获取大众观看次数多的电影
     * @param page
     * @param pageCount
     * @return
     */
    public List<MovieDO> getPeopleLikeByLookingCountMovies(int page, int pageCount) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        List<RatingDO> ratings = mysqlDao.getTopMovieLookingCountList(preferenceTableName,page, pageCount);
        return mysqlDao.getMovieNameList(TransferUtil.transferRatingDO2Integer(ratings), movieTableName);
    }
}
