package com.adorgroup.sorta.sql.service;

import com.adorgroup.sorta.sql.dao.UserDao;
import com.adorgroup.sorta.sql.entity.UserEntity;
import com.adorgroup.sorta.sql.util.GroupBy;
import com.adorgroup.sorta.sql.util.Limit;
import com.adorgroup.sorta.sql.util.OrderBy;
import com.adorgroup.sorta.sql.util.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserDao userDao;

    /**
     *  用户查询方法
     *
     * @param list    当前User集合
     * @param where   过滤条件
     * @param orderBy 排序条件
     * @param groupBy 分组条件
     * @param limit   限制条件
     * @return 返回查询结果User集合
     */
    @Override
    public List<UserEntity> query(List<UserEntity> list , Where where, OrderBy orderBy, GroupBy groupBy, Limit limit) {
        list = userDao.filter(list,  where);
        list = userDao.order(list,  orderBy);
        list = userDao.group(list,  groupBy);
        list = userDao.limit(list, limit);
        return list;
    }
}
