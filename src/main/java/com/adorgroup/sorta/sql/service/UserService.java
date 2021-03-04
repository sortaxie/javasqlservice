package com.adorgroup.sorta.sql.service;

import com.adorgroup.sorta.sql.entity.UserEntity;
import com.adorgroup.sorta.sql.util.GroupBy;
import com.adorgroup.sorta.sql.util.Limit;
import com.adorgroup.sorta.sql.util.OrderBy;
import com.adorgroup.sorta.sql.util.Where;

import java.util.List;

public interface UserService {
    /**
      * 查询用户服务方法
      *
      * @param list    当前User集合
      * @param where   过滤条件
      * @param orderBy 排序条件
      * @param groupBy 分组条件
      * @param limit   限制条件
      * @return 返回查询结果User集合
      */
    List<UserEntity> query(List<UserEntity> list, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit);
}
