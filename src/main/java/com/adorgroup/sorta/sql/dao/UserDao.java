package com.adorgroup.sorta.sql.dao;

import com.adorgroup.sorta.sql.entity.UserEntity;
import com.adorgroup.sorta.sql.util.GroupBy;
import com.adorgroup.sorta.sql.util.Limit;
import com.adorgroup.sorta.sql.util.OrderBy;
import com.adorgroup.sorta.sql.util.Where;

import java.util.List;

public interface UserDao {

    /**
     * 创建User集合
     *
     * @return 返回User集合
     */
    List<UserEntity> createUserList();


    /**
     * 过滤，当前只做了用户id，userName和password的过滤
     *
     * @param list  当前User集合
     * @param where 过滤条件
     * @return 返回过滤后的User集合
     */
    List<UserEntity> filter(List<UserEntity> list, Where where);

    /**
     * 排序，当前只做了用户id，userName和password的排序
     *
     * @param list    当前User集合
     * @param orderBy 排序条件
     * @return 返回排序后的User集合
     */
    List<UserEntity> order(List<UserEntity> list, OrderBy orderBy);

    /**
     * 分组
     *
     * @param list    当前User集合
     * @param groupBy 分组条件
     * @return 返回分组后的User集合
     */
    List<UserEntity> group(List<UserEntity> list, GroupBy groupBy);

    /**
     * 设置最大返回结果数
     *
     * @param list  当前User集合
     * @param limit 限制条件
     * @return 返回指定元素数量的User集合
     */
    List<UserEntity> limit(List<UserEntity> list, Limit limit);
}
