package com.bonc.mapper_solr;

import com.bonc.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: Created by mace on 21:10 2018/4/23.
 */
//@Mapper
public interface UsersMapper {

    @Select("SELECT * FROM solrtest WHERE id = #{id}")
    Users getUserById(@Param("id") Integer id);

    /**
     *
     * @param user
     * @return int --> 受影响的行数
     */
    @Insert("INSERT INTO solrtest (name) VALUES(#{name})")
    int insertUsers(Users user);

}
