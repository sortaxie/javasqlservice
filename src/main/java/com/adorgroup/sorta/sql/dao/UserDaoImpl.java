package com.adorgroup.sorta.sql.dao;

import com.adorgroup.sorta.sql.execption.ArgsException;
import com.adorgroup.sorta.sql.entity.UserEntity;
import com.adorgroup.sorta.sql.util.GroupBy;
import com.adorgroup.sorta.sql.util.Limit;
import com.adorgroup.sorta.sql.util.OrderBy;
import com.adorgroup.sorta.sql.util.Where;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    /**
     * 创建User集合
     *
     * @return 返回User集合
     */
    @Override
    public List<UserEntity> createUserList() {

        List<UserEntity> list = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("系统管理员");
        user.setUserPassword("123456");
        user.setUserGender(0);
        user.setUserPhone("13981719940");
        user.setUserAddress("成都市天府三街");
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        list.add(user);
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setUserName("谢本居");
        user2.setUserPassword("123456");
        user2.setUserGender(1);
        user2.setUserPhone("13981719940");
        user2.setUserAddress("成都市天府三街");
        user2.setGmtCreate(new Date());
        user2.setGmtModified(new Date());
        list.add(user2);

        UserEntity user3 = new UserEntity();
        user3.setId(3L);
        user3.setUserName("谢本居");
        user3.setUserPassword("1234567");
        user3.setUserGender(1);
        user3.setUserPhone("13981719941");
        user3.setUserAddress("成都市天府三街123好");
        user3.setGmtCreate(new Date());
        user3.setGmtModified(new Date());
        list.add(user3);
        return list;
    }



    /**
     * 过滤，目前只做了用户id，userName的过滤
     *
     * @param list  当前User集合
     * @param where 过滤条件
     * @return 返回过滤后的User集合
     */
    @Override
    public List<UserEntity> filter(List<UserEntity> list,  Where where) {

        if (where != null && where.getFilters() != null) {
            if (where.isAnd()) {
                // 与逻辑
                if (where.getMinId() != null && where.getMaxId() != null) {
                    if (where.getMinId() > list.get(list.size() - 1).getId() || where.getMaxId() < list.get(0).getId()) {
                        throw new ArgsException("参数输入有误,请重新输入");
                    }
                    list = list.stream().filter(userEntity -> userEntity.getId() <= where.getMaxId()
                            && userEntity.getId() >= where.getMinId()).collect(Collectors.toList());
                }
                if (where.getUserName() != null) {
                    list = list.stream().filter(userEntity -> userEntity.getUserName().equals(where.getUserName()))
                            .collect(Collectors.toList());
                }

                if(where.getUserPassword()!=null){
                    list = list.stream().filter(userEntity -> userEntity.getUserPassword().equals(where.getUserPassword()))
                            .collect(Collectors.toList());
                }

            } else {
                //或者逻辑
                List<UserEntity> list1 = null;
                if (where.getMinId() != null && where.getMaxId() != null) {
                    if (where.getMinId() > list.get(list.size() - 1).getId() || where.getMaxId() < list.get(0).getId()) {
                        throw new ArgsException("参数输入有误,请重新输入");
                    }
                    list1 = list.stream().filter(userEntity -> userEntity.getId() <= where.getMaxId()
                            && userEntity.getId() >= where.getMinId()).collect(Collectors.toList());
                }
                List<UserEntity> list2 = null;
                if (where.getUserName() != null) {
                    list2 = list.stream().filter(userEntity -> userEntity.getUserName().equals(where.getUserName()))
                            .collect(Collectors.toList());
                }

                if (where.getUserPassword() != null) {
                    list2.addAll(list.stream().filter(userEntity -> userEntity.getUserName().equals(where.getUserPassword()))
                            .collect(Collectors.toList()));
                }

                if (list1 == null && list2 != null) {
                    list1 = list2;
                }else{
                    list1.addAll(list2);
                }
                list = list1;
            }
        }

        return list;
    }

    /**
     * 排序，目前只做了用户id，userNamed的排序
     *
     * @param list    当前User集合
     * @param orderBy 排序条件
     * @return 返回排序后的User集合
     */
    @Override
    public List<UserEntity> order(List<UserEntity> list,  OrderBy orderBy) {

        if (orderBy != null && orderBy.getSort() != null) {

            if ("ASC".equals(orderBy.getSort().get("id"))) {
                list = list.stream().sorted(Comparator.comparingLong(UserEntity::getId))
                        .collect(Collectors.toList());
            }
            if ("DESC".equals(orderBy.getSort().get("id"))) {
                list = list.stream().sorted((o1, o2) -> Long.compare(o2.getId(), o1.getId()))
                        .collect(Collectors.toList());
            }
            if ("ASC".equals(orderBy.getSort().get("userName"))) {
                list = list.stream().sorted(Comparator.comparing(UserEntity::getUserName))
                        .collect(Collectors.toList());
            }
            if ("DESC".equals(orderBy.getSort().get("userName"))) {
                list = list.stream().sorted((o1, o2) -> o2.getUserName().compareTo(o1.getUserName()))
                        .collect(Collectors.toList());
            }

            if ("ASC".equals(orderBy.getSort().get("userPassword"))) {
                list = list.stream().sorted(Comparator.comparing(UserEntity::getUserPassword))
                        .collect(Collectors.toList());
            }
            if ("DESC".equals(orderBy.getSort().get("userPassword"))) {
                list = list.stream().sorted((o1, o2) -> o2.getUserName().compareTo(o1.getUserPassword()))
                        .collect(Collectors.toList());
            }
        }

        return list;
    }

    /**
     * 分组
     *
     * @param list    当前User集合
     * @param groupBy 分组条件
     * @return 返回分组后的User集合
     */
    @Override
    public List<UserEntity> group(List<UserEntity> list, GroupBy groupBy) {

        if (groupBy != null && groupBy.getGroup() != null) {
            List<UserEntity> result = new ArrayList<>();
            if ("id".equals(groupBy.getGroup())) {
                Map<Long, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getId));

                for(Map.Entry<Long, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setId(entry.getKey());
                    result.add(userEntity);
                }
            }
           if ("userName".equals(groupBy.getGroup())) {
               Map<String, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getUserName));

               for(Map.Entry<String, List<UserEntity>> entry : map.entrySet()){
                   UserEntity userEntity = new UserEntity();
                   userEntity.setUserName(entry.getKey());
                   result.add(userEntity);
               }

           }
            if ("userPassword".equals(groupBy.getGroup())) {
                Map<String, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getUserPassword));
                for(Map.Entry<String, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUserPassword(entry.getKey());
                    result.add(userEntity);
                }
            }
            if ("userGender".equals(groupBy.getGroup())) {
                Map<Integer, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getUserGender));
                for(Map.Entry<Integer, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUserGender(entry.getKey());
                    result.add(userEntity);
                }
            }
            if ("userPhone".equals(groupBy.getGroup())) {
                Map<String, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getUserPhone));
                for(Map.Entry<String, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUserPhone(entry.getKey());
                    result.add(userEntity);
                }
            }
            if ("userAddress".equals(groupBy.getGroup())) {
                Map<String, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getUserAddress));
                for(Map.Entry<String, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUserAddress(entry.getKey());
                    result.add(userEntity);
                }
            }
            if ("gmtCreate".equals(groupBy.getGroup())) {
                Map<Date, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getGmtCreate));
                for(Map.Entry<Date, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setGmtCreate(entry.getKey());
                    result.add(userEntity);
                }
            }
            if ("gmtModified".equals(groupBy.getGroup())) {
                Map<Date, List<UserEntity>>  map =   list.stream().collect(Collectors.groupingBy(UserEntity::getGmtModified));
                for(Map.Entry<Date, List<UserEntity>> entry : map.entrySet()){
                    UserEntity userEntity = new UserEntity();
                    userEntity.setGmtModified(entry.getKey());
                    result.add(userEntity);
                }
            }
            return result;
        }else{
            return list;
        }
    }

    /**
     * 设置最大返回结果数
     *
     * @param list  当前User集合
     * @param limit 限制条件
     * @return 返回指定元素数量的User集合
     */
    @Override
    public List<UserEntity> limit(List<UserEntity> list, Limit limit) {

        if (limit != null && limit.getCnt() != null) {
            if (limit.getCnt() < 0) {
                throw new ArgsException("参数输入有误,请重新输入!");
            }
            if (limit.getCnt() != null) {
                list = list.stream().limit(limit.getCnt()).collect(Collectors.toList());
            }
        }

        return list;
    }
}
