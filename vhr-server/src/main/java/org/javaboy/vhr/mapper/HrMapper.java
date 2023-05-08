package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
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
}