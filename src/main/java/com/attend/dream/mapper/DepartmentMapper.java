package com.attend.dream.mapper;

import com.attend.dream.domain.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    //查询所有的雇员
    @Select("select id,depCode,depJob,depName,depBoss,depTopId\n" +
            "from department")
    List<Department> getDepartments();


}
