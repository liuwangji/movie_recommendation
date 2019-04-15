package com.bjut.dao;

import com.bjut.domain.MovieDO;
import com.bjut.domain.RatingDO;
import com.bjut.domain.TagDO;
import com.bjut.util.TransferUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据源Dao
 *
 */
@Service
public class MysqlDao {
    @Resource(name = "mysqlConnection")
    public Connection mysqlConnection;

    public static  String movieTableName;
    public static  String preferenceTableName;
    public static  String predictTableName;
    public static  String tagTableName;

    @Value("${movie.tableName}")
    public void setMovieTableName(String movieTableName) {
        MysqlDao.movieTableName = movieTableName;
    }
    @Value("${preference.tableName}")
    public void setPreferenceTableName(String preferenceTableName) {
        MysqlDao.preferenceTableName = preferenceTableName;
    }
    @Value("${predict.tableName}")
    public void setPredictTableName(String predictTableName) {
        MysqlDao.predictTableName = predictTableName;
    }
    @Value("${tag.tableName}")
    public void setTagTableName(String tagTableName) {
        MysqlDao.tagTableName = tagTableName;
    }

    /**
     * 将movies.csv中的数据插入movies 表中
     * @param movie
     * @return
     */
    public int insertMovie(MovieDO movie) {
        int i = 0;
        String sql = "insert into " + movieTableName + " (movieId,title,genres) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, movie.getMovieId());
            pstmt.setString(2, movie.getMovieTitle());
            pstmt.setString(3, movie.getGenres());
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
     * @param rating
     * @return
     */
    public int insertPreference(RatingDO rating) {
        int i = 0;
        String sql = "insert into " + preferenceTableName + " (userId,movieId,rating) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)mysqlConnection.prepareStatement(sql);
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
     * @param rating
     * @return
     */
    public int insertPredict(RatingDO rating) {
        int i = 0;
        String sql = "insert into " + predictTableName + " (userId,movieId,rating) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)mysqlConnection.prepareStatement(sql);
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
     * @param rating
     * @return
     */
    public int insertTags(TagDO rating) {
        int i = 0;
        String sql = "insert into " + tagTableName + " (userId,movieId,tag,timeStamp) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)mysqlConnection.prepareStatement(sql);
            pstmt.setInt(1, rating.getUserId());
            pstmt.setInt(2, rating.getMovieId());
            pstmt.setString(3, rating.getTag());
            pstmt.setString(4,rating.getTimeStamp());
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
            System.out.println("delete table " + tableName + ", resultCode = " + i + ", delete sql = "+deleteSql);
            deletePstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            createPstmt = mysqlConnection.prepareStatement(createSql);
            i = createPstmt.executeUpdate();
            System.out.println("create table " + tableName + ", resultCode = " + i +", create sql = "+ createSql);
            createPstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * 根据movieId获取movieDO
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
                    resultList.add(TransferUtil.transfer2MovieDO(rs.getInt("movieId"), rs.getString("title"),
                            rs.getString(
                                    "genres")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 根据userId获取推荐电影
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
    public List<RatingDO> getTopMovieRatingList(Integer userId, String tableName, int page, int pageCount) {
        List<RatingDO> resultList = new ArrayList<RatingDO>();
        String sql = "select * from " + tableName + " where userId = ? order by rating desc limit " + page * pageCount;
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
     * 获取大众评分前十的电影
     *
     * @param tableName
     * @return
     */
    public List<RatingDO> getTopMovieAvgRatingList( String tableName, int page, int pageCount) {
        List<RatingDO> resultList = new ArrayList<RatingDO>();
        String sql = "select movieId,avg(rating) as avgRating from " + tableName + " group by movieId having count(1)>10 order by avgRating desc" + " limit " + page * pageCount;
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2RatingDO(0, rs.getInt("movieId"),
                        rs.getDouble(
                                "avgRating")));
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
    public List<RatingDO> getTopMovieLookingCountList(String tableName, int page, int pageCount) {
        List<RatingDO> resultList = new ArrayList<RatingDO>();
        String sql = "select movieId, count(1) as count from " + tableName + " group by movieId  order by count desc" + " limit " + page * pageCount;
        PreparedStatement pstmt;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2RatingDO(1, rs.getInt("movieId"),
                        null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
