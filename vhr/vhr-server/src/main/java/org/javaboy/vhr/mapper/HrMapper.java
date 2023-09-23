package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.bean.Hr;
import org.javaboy.vhr.bean.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service("Hr")
public interface HrMapper {
    Hr findHrByUsername(String password);

    List<Role> getHrRolesById(Integer id);

    int insertHr(Hr hr);

    int updateHr(String name);

    int deleteById(int id);

    // 获取所有用户
    List<Hr> getAllHr(@Param("id") Integer id, @Param("keyword") String keyword);

    // 更改用户状态
    int updateEnable(Integer enabled,Integer id);

}