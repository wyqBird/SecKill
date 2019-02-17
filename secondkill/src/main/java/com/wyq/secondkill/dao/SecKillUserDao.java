package com.wyq.secondkill.dao;

import com.wyq.secondkill.domain.SecKillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author coldsmoke
 * @version 1.0
 * @className: SecKillUserDao
 * @description: TODO
 * @date 2019/2/15 17:32
 */
@Mapper
public interface SecKillUserDao {
    @Select("select * from miaosha_user where id = #{id}")
    public SecKillUser getById(@Param("id")long id);

    @Update("update miaosha_user set password = #{password} where id = #{id}")
    public void update(SecKillUser toBeUpdate);
}
