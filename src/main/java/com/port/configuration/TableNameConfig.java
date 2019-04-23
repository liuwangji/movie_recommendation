package com.port.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableNameConfig {

    public static  String movieTableName;
    public static  String preferenceTableName;
    public static  String predictTableName;
    public static  String tagTableName;
    public static String completePreferenceTableName;
    public static String completePredictTableName;

    @Value("${movie.tableName}")
    public void setMovieTableName(String movieTableName) {
        TableNameConfig.movieTableName = movieTableName;
    }
    @Value("${preference.tableName}")
    public void setPreferenceTableName(String preferenceTableName) {
        TableNameConfig.preferenceTableName = preferenceTableName;
    }
    @Value("${predict.tableName}")
    public void setPredictTableName(String predictTableName) {
        TableNameConfig.predictTableName = predictTableName;
    }
    @Value("${tag.tableName}")
    public void setTagTableName(String tagTableName) {
        TableNameConfig.tagTableName = tagTableName;
    }
    @Value("${completePreference.tableName}")
    public  void setCompletePredictTableName(String completePredictTableName) {
        TableNameConfig.completePredictTableName = completePredictTableName;
    }

    @Value("${completePredict.tableName}")
    public  void setCompletePreferenceTableName(String completePreferenceTableName) {
        TableNameConfig.completePreferenceTableName = completePreferenceTableName;
    }
}
