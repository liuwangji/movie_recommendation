package com.port.service.service;

import com.port.domain.CompleteDO;
import com.port.configuration.TableNameConfig;
import com.port.constant.MovieCategoriesEnum;
import com.port.constant.SearchTypeEnum;
import com.port.dao.MysqlDao;
import com.port.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐服务
 */
@Service
public class ReadRecommadationResultService {
    @Autowired
    MysqlDao mysqlDao;

    /**
     * 获取用户历史偏好电影
     *
     * @param userId
     * @return
     */
    public List<CompleteDO> getPreferenceMovies(Integer userId, int page, int pageCount) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        return mysqlDao.getTopMovieRatingList(userId, TableNameConfig.completePreferenceTableName, page, pageCount);

    }

    /**
     * 获取基于用户的个性化推荐结果
     *
     * @param userId
     * @return
     */
    public List<CompleteDO> getRecommdationMovies(Integer userId, int page, int pageCount) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        return mysqlDao.getTopMovieRatingList(userId, TableNameConfig.completePredictTableName, page, pageCount);
    }

    /**
     * 基于分类选项进行电影搜索
     *
     * @param page
     * @param pageCount
     * @param tag
     * @param type
     * @return
     */
    public List<CompleteDO> getCategorySearchResult(int page, int pageCount, String tag, String type) {
        if (tag.equals(MovieCategoriesEnum.ALL.getCategory())) {
            tag = "";
        }
        if (type.equals(SearchTypeEnum.COUNT.getType())) {
            return getPeopleLikeByLookingCountMovies(page, pageCount, tag);
        } else {
            return getPeopleLikeByRatingMovies(page, pageCount, tag);
        }

    }

    /**
     * 基于电影名称关键词进行电影搜索
     *
     * @param page
     * @param pageCount
     * @param searchTest
     * @return
     */

    public List<CompleteDO> getSearchResult(int page, int pageCount, String searchTest) {
        return mysqlDao.getSearchMovieList(TableNameConfig.completePreferenceTableName, page, pageCount, searchTest);


    }

    /**
     * 获取大家评分高的电影
     *
     * @param page
     * @param pageCount
     * @return
     */
    public List<CompleteDO> getPeopleLikeByRatingMovies(int page, int pageCount, String tag) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        return mysqlDao.getTopMovieAvgRatingList(TableNameConfig.completePreferenceTableName, page, pageCount, tag);
    }

    /**
     * 获取大众观看次数多的电影
     *
     * @param page
     * @param pageCount
     * @return
     */
    public List<CompleteDO> getPeopleLikeByLookingCountMovies(int page, int pageCount, String tag) {
        page = ParamUtil.dealPage(page);
        pageCount = ParamUtil.dealPageCount(pageCount);
        return mysqlDao.getTopMovieLookingCountList(TableNameConfig.completePreferenceTableName, page, pageCount, tag);
    }
}
