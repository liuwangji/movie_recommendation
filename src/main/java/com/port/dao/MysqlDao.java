package com.port.dao;

import com.port.configuration.TableNameConfig;
import com.port.constant.ColumnEnum;
import com.port.constant.State;
import com.port.domain.*;
import com.port.util.TransferUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据源Dao
 */
@Service
public class MysqlDao {
    @Resource(name = "mysqlConnection")
    public Connection mysqlConnection;

    /**
     * 插入注册的用户信息到用户表中
     * @param userDO
     * @return
     */
    public State insertUser(UserDO userDO){
        int i = 0;
        String sql = "insert into " + TableNameConfig.userTableName + " (userId,nickName,password) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, userDO.getUserId());
            pstmt.setString(2, userDO.getNickName());
            pstmt.setString(3, userDO.getPasswoed());
            i = pstmt.executeUpdate();
            pstmt.close();
            //mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(1 == i){
            return State.SUCCESS;
        }else{
            return State.FAIL;
        }
    }

    public UserDO getUser(Integer userId, String password){
        UserDO result = new UserDO();
        String sql = "select * from " + TableNameConfig.userTableName + " where userId = ? and password = ? limit 1";
        PreparedStatement pstmt;
            try {
                pstmt = mysqlConnection.prepareStatement(sql);
                pstmt.setInt(1, userId);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    result = TransferUtil.transfer2UserDO(rs.getInt(ColumnEnum.USERID.getName()), rs.getString(ColumnEnum.NICK_NAME.getName()),
                            rs.getString(ColumnEnum.PASSWORD.getName()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return result;
    }
    /**
     * 将movies.csv中的数据插入movies 表中
     *
     * @param movie
     * @return
     */
    public int insertMovie(MovieDO movie) {
        int i = 0;
        String sql = "insert into " + TableNameConfig.movieTableName + " (movieId,title,year,genres,imgUrl,url) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, movie.getMovieId());
            pstmt.setString(2, movie.getMovieTitle());
            pstmt.setString(3, movie.getYear());
            pstmt.setString(4, movie.getGenres());
            pstmt.setString(5, movie.getImgUrl());
            pstmt.setString(6, movie.getUrl());
            i = pstmt.executeUpdate();
            pstmt.close();
            //mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * rating.csv中的数据插入ratings表中
     *
     * @param rating
     * @return
     */
    public int insertPreference(RatingDO rating) {
        int i = 0;
        String sql = "insert into " + TableNameConfig.preferenceTableName + " (userId,movieId,rating) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, rating.getUserId());
            pstmt.setInt(2, rating.getMovieId());
            pstmt.setDouble(3, rating.getRating());
            i = pstmt.executeUpdate();
            pstmt.close();
            //mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 将建模结果插入predict预测表中
     *
     * @param rating
     * @return
     */
    public int insertPredict(RatingDO rating) {
        int i = 0;
        String sql = "insert into " + TableNameConfig.predictTableName + " (userId,movieId,rating) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, rating.getUserId());
            pstmt.setInt(2, rating.getMovieId());
            pstmt.setDouble(3, rating.getRating());
            i = pstmt.executeUpdate();
            pstmt.close();
            //mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 将标签数据插入tag表中
     *
     * @param rating
     * @return
     */
    public int insertTags(TagDO rating) {
        int i = 0;
        String sql = "insert into " + TableNameConfig.tagTableName + " (userId,movieId,tag,timeStamp) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, rating.getUserId());
            pstmt.setInt(2, rating.getMovieId());
            pstmt.setString(3, rating.getTag());
            pstmt.setString(4, rating.getTimeStamp());
            i = pstmt.executeUpdate();
            pstmt.close();
            //mysqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 插入数据前先将表中原来数据清空
     *
     * @param tableName
     * @param createSql
     * @return
     */
    public int recreateTable(String tableName, String createSql) {
        int i = 0;
        String deleteSql = "drop table " + tableName;
        PreparedStatement deletePstmt;
        PreparedStatement createPstmt;
        try {
            deletePstmt = mysqlConnection.prepareStatement(deleteSql);
            i = deletePstmt.executeUpdate();
            System.out.println("delete table " + tableName + ", resultCode = " + i + ", delete sql = " + deleteSql);
            deletePstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            createPstmt = mysqlConnection.prepareStatement(createSql);
            i = createPstmt.executeUpdate();
            System.out.println("create table " + tableName + ", resultCode = " + i + ", create sql = " + createSql);
            createPstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * 根据movieId获取movieDO
     *
     * @param movieIdList
     * @param tableName
     * @return
     */
    public List<MovieDO> getMovieNameList(List<Integer> movieIdList, String tableName) {
        List<MovieDO> resultList = new ArrayList<MovieDO>();
        String sql = "select * from " + tableName + " where movieId = ?";
        PreparedStatement pstmt;
        for (int id : movieIdList) {
            try {
                pstmt = mysqlConnection.prepareStatement(sql);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    resultList.add(TransferUtil.transfer2MovieDO(rs.getInt(ColumnEnum.MOVIEID.getName()), rs.getString(ColumnEnum.MOVIE_TITLE.getName()),
                            rs.getString(ColumnEnum.YEAR.getName()), rs.getString(ColumnEnum.GENRES.getName()), rs.getString(ColumnEnum.IMG_URL.getName()),
                            rs.getString(ColumnEnum.URL.getName())));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 根据userId获取推荐电影
     *
     * @param userId
     * @param tableName
     * @return
     */
    public List<RatingDO> getMovieRatingList(Integer userId, String tableName) {
        List<RatingDO> resultList = new ArrayList<RatingDO>();
        String sql = "select * from " + tableName + " where userId = ? order by rating desc limit 10";
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2RatingDO(rs.getInt("userId"), rs.getInt("movieId"),
                        rs.getDouble(
                                "rating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 根据userId获取评分前十的电影
     *
     * @param userId
     * @param tableName
     * @return
     */
    public List<CompleteDO> getTopMovieRatingList(Integer userId, String tableName, int page, int pageCount) {
        List<CompleteDO> resultList = new ArrayList<CompleteDO>();
        String sql = "select * from " + tableName + " where userId = ? order by rating desc limit " + page * pageCount;
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2CompleteDO(0, rs.getInt(ColumnEnum.MOVIEID.getName()), rs.getString(ColumnEnum.MOVIE_TITLE.getName()),
                        rs.getString(ColumnEnum.YEAR.getName()), rs.getString(ColumnEnum.GENRES.getName()), rs.getString(ColumnEnum.IMG_URL.getName()), rs.getString(ColumnEnum.URL.getName()),
                        rs.getDouble(ColumnEnum.RATING.getName())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    public List<CompleteDO> getSearchMovieList(String tableName, int page, int pageCount, String searchText) {
        List<CompleteDO> resultList = new ArrayList<CompleteDO>();
        String sql = "select movieId, title, year,  genres, imgUrl, url, avg(rating) as avgRating from " + tableName + " where genres like '%" + searchText + "%' or title like '%" + searchText + "%' group by movieId, title, year,  genres, imgUrl, url having count(1)>10 order by avgRating desc limit " + page * pageCount;
        System.out.println(sql);
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2CompleteDO(0, rs.getInt(ColumnEnum.MOVIEID.getName()), rs.getString(ColumnEnum.MOVIE_TITLE.getName()),
                        rs.getString(ColumnEnum.YEAR.getName()), rs.getString(ColumnEnum.GENRES.getName()), rs.getString(ColumnEnum.IMG_URL.getName()), rs.getString(ColumnEnum.URL.getName()),
                        rs.getDouble("avgRating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    /**
     * 获取大众评分前十的电影
     *
     * @param tableName
     * @return
     */
    public List<CompleteDO> getTopMovieAvgRatingList(String tableName, int page, int pageCount, String tag) {
        List<CompleteDO> resultList = new ArrayList<CompleteDO>();
        String sql = "select movieId, title, year,  genres, imgUrl, url, avg(rating) as avgRating from " + tableName + " where genres like '%" + tag + "%'" + " group by movieId, title, year,  genres, imgUrl, url having count(1)>10 order by avgRating desc limit " + page * pageCount;
        System.out.println(sql);
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2CompleteDO(0, rs.getInt(ColumnEnum.MOVIEID.getName()), rs.getString(ColumnEnum.MOVIE_TITLE.getName()),
                        rs.getString(ColumnEnum.YEAR.getName()), rs.getString(ColumnEnum.GENRES.getName()), rs.getString(ColumnEnum.IMG_URL.getName()), rs.getString(ColumnEnum.URL.getName()),
                        rs.getDouble("avgRating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 获取观看次数前十的电影
     *
     * @param tableName
     * @return
     */
    public List<CompleteDO> getTopMovieLookingCountList(String tableName, int page, int pageCount, String tag) {
        List<CompleteDO> resultList = new ArrayList<CompleteDO>();
        String sql = "select movieId, title, year,  genres, imgUrl, url, rating, count(1) as count from " + tableName + " where genres like '%" + tag + "%'" + " group by movieId, title, year,  genres, imgUrl, url, rating order by count desc " + " limit " + page * pageCount;
        System.out.println(sql);
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    resultList.add(TransferUtil.transfer2CompleteDO(0, rs.getInt(ColumnEnum.MOVIEID.getName()), rs.getString(ColumnEnum.MOVIE_TITLE.getName()),
                            rs.getString(ColumnEnum.YEAR.getName()), rs.getString(ColumnEnum.GENRES.getName()), rs.getString(ColumnEnum.IMG_URL.getName()), rs.getString(ColumnEnum.URL.getName()),
                            rs.getDouble("count")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
