package com.port.helper;

import com.port.constant.ColumnEnum;
import com.port.domain.MovieDO;
import com.port.domain.RatingDO;
import com.port.util.TransferUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MysqlHelper {

    @Resource(name = "mysqlConnection")
    public Connection mysqlConnection;

    public int updateResult(String sql) {
        PreparedStatement pstmt;
        int i = 0;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public ResultSet queryResult(String sql) {
        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            pstmt = mysqlConnection.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public List<RatingDO> transfer2RatingDO(ResultSet rs) {
        List<RatingDO> resultList = new ArrayList<RatingDO>();
        try {
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2RatingDO(rs.getInt("userId"), rs.getInt("movieId"),
                        rs.getDouble("rating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public List<MovieDO> transfer2MovieDO(ResultSet rs) {
        List<MovieDO> resultList = new ArrayList<MovieDO>();
        try {
            while (rs.next()) {
                resultList.add(TransferUtil.transfer2MovieDO(rs.getInt("movieId"), rs.getString("title"),
                        rs.getString(ColumnEnum.YEAR.getName()), rs.getString(ColumnEnum.IMG_URL.getName()), rs.getString(ColumnEnum.URL.getName()),
                        rs.getString("genres")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
