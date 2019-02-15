package com.wyq.secondkill.dao;

import com.wyq.secondkill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: UserDao
 * @description: TODO
 * @date 2019/2/14 18:26
 */
@Mapper
public interface UserDao {
    /**
     * 通过@Param定义参数id，然后再使用#{} 来引用这个参数
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);

    @Insert("insert into user(id, name) values(#{id}, #{name})")
    public int insert(User user);
}
