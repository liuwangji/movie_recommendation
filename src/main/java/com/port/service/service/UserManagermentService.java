package com.port.service.service;

import com.port.configuration.TableNameConfig;
import com.port.constant.MovieCategoriesEnum;
import com.port.constant.SearchTypeEnum;
import com.port.constant.State;
import com.port.dao.MysqlDao;
import com.port.domain.CompleteDO;
import com.port.domain.UserDO;
import com.port.util.ParamUtil;
import com.port.util.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐服务
 */
@Service
public class UserManagermentService {
    @Autowired
    MysqlDao mysqlDao;

    /**
     * 获取用户历史偏好电影
     *
     * @param userId
     * @return
     */
    public State registerUser(Integer userId, String nickName, String password) {
        return mysqlDao.insertUser(TransferUtil.transfer2UserDO(userId, nickName, password));
    }

    public UserDO loginUser(Integer userId, String password) {
        return mysqlDao.getUser(userId, password);
    }

}
