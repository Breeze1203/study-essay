package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.bean.Hr;
import org.springframework.stereotype.Service;

@Mapper
@Service("Hr")
public interface HrMapper {
    Hr findHrByUsername(String password);
}