package com.adorgroup.sorta.sql.test;


import com.adorgroup.sorta.sql.dao.UserDao;
import com.adorgroup.sorta.sql.entity.UserEntity;
import com.adorgroup.sorta.sql.service.UserService;
import com.adorgroup.sorta.sql.util.GroupBy;
import com.adorgroup.sorta.sql.util.Limit;
import com.adorgroup.sorta.sql.util.OrderBy;
import com.adorgroup.sorta.sql.util.Where;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserServiceTestCase extends CaseBase {


    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    /**
     * 所有过滤情况
     */
    @Test
    public void testAll() {
        //本测试项覆盖了 过滤 排序 分组 最大返回结果数

        //创建3个用户数据
        List<UserEntity> list = userDao.createUserList();
        // 1、过滤 设置 字段and为true表示与，false表示或
        Where where = new Where();
        List<String> filters = new ArrayList<>();
        where.setAnd(true);
        where.setUserName("谢本居");
        filters.add("userPassword");
        where.setUserPassword("123456");
        where.setFilters(filters);
        //2、排序设置
        OrderBy orderBy = new OrderBy();
        HashMap<String, String> sort = new HashMap<>();
        sort.put("id", "DESC");
        orderBy.setSort(sort);
        //3、分组设置
        GroupBy groupBy = new GroupBy();
        groupBy.setGroup("userName");
        //4、最大返回数限制
        Limit limit = new Limit();
        limit.setCnt(3);
        List<UserEntity> data = userService.query(list, where, orderBy, groupBy, limit);
        for (UserEntity userEntity : data) {
            System.out.println("id:" + userEntity.getId());
            System.out.println("userName:" + userEntity.getUserName());
        }
    }

    /**
     * 过滤测试
     */
    @Test
    public void testWhere() {
        //创建用户数据
        List<UserEntity> list = userDao.createUserList();
        // 过滤设置 字段and为true表示与，false表示或
        Where where = new Where();
        List<String> filters = new ArrayList<>();
        where.setAnd(true);
        filters.add("userName");
        where.setUserName("谢本居");
        filters.add("userPassword");
        where.setUserPassword("123456");
        where.setFilters(filters);

        List<UserEntity> data = userService.query(list, where, null, null, null);
        for (UserEntity userEntity : data) {
            System.out.println("id:" + userEntity.getId());
            System.out.println("userName:" + userEntity.getUserName());
        }
    }

    @Test
    public void testWhereOr() {
        //创建用户数据
        List<UserEntity> list = userDao.createUserList();
        // 过滤设置 字段and为true表示与，false表示或
        Where where = new Where();
        List<String> filters = new ArrayList<>();
        where.setAnd(false);
        filters.add("userName");
        where.setUserName("谢本居");
        filters.add("userPassword");
        where.setUserPassword("123456");
        where.setFilters(filters);

        List<UserEntity> data = userService.query(list, where, null, null, null);
        for (UserEntity userEntity : data) {
            System.out.println("id:" + userEntity.getId());
            System.out.println("userName:" + userEntity.getUserName());
        }
    }


    /**
     * 测试排序
     */
    @Test
    public void testOrderBy() {
        //创建用户数据
        List<UserEntity> list = userDao.createUserList();
        //设置排序
        OrderBy orderBy = new OrderBy();
        HashMap<String, String> sort = new HashMap<>();
        sort.put("id", "DESC");
        orderBy.setSort(sort);
        List<UserEntity> data = userService.query(list, null, orderBy, null, null);
        for (UserEntity userEntity : data) {
            System.out.println("id:" + userEntity.getId());
            System.out.println("userName:" + userEntity.getUserName());
        }
    }

    /**
     * 返回最大限制测试
     */
    @Test
    public void testLimit() {
        //创建用户数据
        List<UserEntity> list = userDao.createUserList();
        //限制最大数量
        Limit limit = new Limit();
        limit.setCnt(2);

        List<UserEntity> data = userService.query(list, null, null, null, limit);
        for (UserEntity userEntity : data) {
            System.out.println("id:" + userEntity.getId());
            System.out.println("userName:" + userEntity.getUserName());
        }
    }


    /**
     * 测试分组
     */
    @Test
    public void testGroupBy() {
        //创建用户数据
        List<UserEntity> list = userDao.createUserList();
        //分组设置
        GroupBy groupBy = new GroupBy();
        groupBy.setGroup("userName");

        List<UserEntity> data = userService.query(list, null, null, groupBy, null);
        for (UserEntity userEntity : data) {
            System.out.println("id:" + userEntity.getId());
            System.out.println("userName:" + userEntity.getUserName());
        }
    }


}
