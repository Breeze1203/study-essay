package org.javaboy.vhr.exception;

import org.javaboy.vhr.utils.StatusUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 * @author Breeze
 * @address Shenzhen China
 * @email 3548297839@qq.com
 */
/*
自定义异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandle {
   @ExceptionHandler(SQLException.class)
    public StatusUtils SQLException(SQLException e){
       StatusUtils statusUtils = new StatusUtils();
       if(e instanceof SQLIntegrityConstraintViolationException){
           statusUtils.setMessage("该数据有关联数据，无法删除");
       }else {
           statusUtils.setMessage("数据库异常，操作失败");
       }
       return statusUtils;
   }
}
