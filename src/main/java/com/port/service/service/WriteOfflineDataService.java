package com.port.service.service;

import com.port.dao.MysqlDao;
import com.port.util.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 离线初始化数据服务
 */
@Component
public class WriteOfflineDataService {
    @Autowired
    MysqlDao mysqlDao;

    public static String userTableName;
    public static String movieTableName;
    public static String preferenceTableName;
    public static String predictTableName;
    public static String tagTableName;
    public static String completePreferenceTableName;
    public static String completePredictTableName;

    @Value("${user.tableName}")
    public void setUserTableName(String userTableName){
        WriteOfflineDataService.userTableName = userTableName;
    }

    @Value("${movie.tableName}")
    public void setMovieTableName(String movieTableName) {
        WriteOfflineDataService.movieTableName = movieTableName;
    }

    @Value("${preference.tableName}")
    public void setPreferenceTableName(String preferenceTableName) {
        WriteOfflineDataService.preferenceTableName = preferenceTableName;
    }

    @Value("${predict.tableName}")
    public void setPredictTableName(String predictTableName) {
        WriteOfflineDataService.predictTableName = predictTableName;
    }

    @Value("${tag.tableName}")
    public void setTagTableName(String tagTableName) {
        WriteOfflineDataService.tagTableName = tagTableName;
    }

    @Value("${completePreference.tableName}")
    public void setCompletePreferenceTableName(String completePreferenceTableName) {
        WriteOfflineDataService.completePreferenceTableName = completePreferenceTableName;
    }
    @Value("${completePredict.tableName}")
    public void setCompletePredictTableName(String completePredictTableName) {
        WriteOfflineDataService.completePredictTableName = completePredictTableName;
    }

    @Value("${movie.fileName}")
    public String movieFile;
    @Value("${rating.fileName}")
    public String ratingFile;
    @Value("${predict.fileName}")
    public String predictFile;
    @Value("${tag.fileName}")
    public String tagFile;

    public void doInit() {
        System.out.println("start to init ……");
        recreateUserTable();
        recreateMovieTable();
        recreatePreferenceTable();
        recreatePredictTable();
        recreateTagTable();
        initMovieData();
        initPrefenceData();
        initPredictData();
        initTagData();
        recreategCompletePredictTable();
        recreategCompletePreferenceTable();
        System.out.println("init stop");
    }
    public void recreateUserTable() {
        String createSql = "create table " + userTableName
                + " (userId int(5), nickName varchar(250), password varchar(250))";
        mysqlDao.recreateTable(userTableName, createSql);
    }

    public void recreateMovieTable() {
        String createSql = "create table " + movieTableName
            + " (movieId int(5), title varchar(250), year varchar(250), imgUrl varchar(250), url varchar(250), genres varchar(250))";
        mysqlDao.recreateTable(movieTableName, createSql);
    }

    public void recreatePreferenceTable() {
        String createSql = "create table " + preferenceTableName
            + " (userId int(5), movieId int(5), rating float)";
        mysqlDao.recreateTable(preferenceTableName, createSql);
    }

    public void recreatePredictTable() {
        String createSql = "create table " + predictTableName
            + " (userId int(5), movieId int(5), rating float)";
        mysqlDao.recreateTable(predictTableName, createSql);
    }

    public void recreateTagTable() {
        String createSql = "create table " + tagTableName
                + " (userId int(5), movieId int(5), tag varchar(250), timeStamp varchar(20))";
        mysqlDao.recreateTable(tagTableName, createSql);
    }

    public void recreategCompletePreferenceTable() {
        String createSql = "create table " + completePreferenceTableName
                + " select ratings.movieId, ratings.rating, userId, title, year, imgUrl, url,  genres  from "+preferenceTableName+" left join "+ movieTableName+" on movies.movieId = ratings.movieId";
        mysqlDao.recreateTable(completePreferenceTableName, createSql);
    }

    public void recreategCompletePredictTable() {
        String createSql = "create table " + completePredictTableName
                + " select predict.movieId, predict.rating, userId, title, year, imgUrl, url,  genres from "+predictTableName+" left join "+ movieTableName+" on movies.movieId = predict.movieId";
        mysqlDao.recreateTable(completePredictTableName, createSql);
    }

    /**
     * 初始化newMovies.csv文件,写入movies表
     */
    public void initMovieData() {
        // 电影ID名称映射文件路径
        File csv = new File(movieFile);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String temp = "";
        String currentLint = "";
        try {
            List<String> allString = new ArrayList<String>();
            //读取到的内容给line变量
            while ((temp = br.readLine()) != null) {
                currentLint = temp;
                String[] data = currentLint.split(",",-1);
                if (!data[0].equalsIgnoreCase("movieId")) {
                    if (data.length == 6) {
                        mysqlDao.insertMovie(TransferUtil.transfer2MovieDO(Integer.parseInt(data[0].substring(1)), data[1], data[2], data[3].substring(0, data[3].length() > 1 ? data[3].length() - 1 : 0), data[4], data[5]));
                    }else{
                        mysqlDao.insertMovie(TransferUtil.transfer2MovieDO(Integer.parseInt(data[0].substring(1)), data[1], data[2], data[3].substring(0, data[3].length() > 1 ? data[3].length() - 1 : 0), "http://img31.mtime.cn/mt/2014/02/22/233500.35373350_96X128.jpg", "http://movie.mtime.com/14572/"));
                    }
                }
                allString.add(currentLint);
            }
            System.out.println("movie表中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化rating.csv，写入ratings表
     */
    public void initPrefenceData() {
        // 电影ID名称映射文件路径
        File csv = new File(ratingFile);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String temp = "";
        String currentLint = "";
        try {
            List<String> allString = new ArrayList<String>();
            //读取到的内容给line变量
            while ((temp = br.readLine()) != null) {
                currentLint = temp;
                String[] data = currentLint.split(",");
                if (!data[0].equalsIgnoreCase("userId")) {
                    mysqlDao.insertPreference(TransferUtil.transfer2RatingDO(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Double.parseDouble(data[2])));
                }
                allString.add(currentLint);
            }
            System.out.println("rating表中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化predict.csv，写入predict表
     */
    public void initPredictData() {
        // 电影ID名称映射文件路径
        File csv = new File(predictFile);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String temp = "";
        String currentLint = "";
        try {
            List<String> allString = new ArrayList<String>();
            //读取到的内容给line变量
            while ((temp = br.readLine()) != null) {
                currentLint = temp;
                String[] data = currentLint.split(",");
                if (!data[0].equalsIgnoreCase("userId")) {
                    mysqlDao.insertPredict(TransferUtil.transfer2RatingDO(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Double.parseDouble(data[2])));
                    allString.add(currentLint);
                    if (allString.size() > 100000) {
                        break;
                    }
                }
            }
            System.out.println("predict表中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化tag.csv，写入tag表
     */
    public void initTagData() {
        // 电影ID名称映射文件路径
        File csv = new File(tagFile);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String temp = "";
        String currentLint = "";
        try {
            List<String> allString = new ArrayList<String>();
            //读取到的内容给line变量
            while ((temp = br.readLine()) != null) {
                currentLint = temp;
                String[] data = currentLint.split(",");
                if (!data[0].equalsIgnoreCase("userId")) {
                    mysqlDao.insertTags(TransferUtil.transfer2TagDO(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        data[2], data[3]));
                    allString.add(currentLint);
                    if (allString.size() > 100000) {
                        break;
                    }
                }
            }
            System.out.println("tag表中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
