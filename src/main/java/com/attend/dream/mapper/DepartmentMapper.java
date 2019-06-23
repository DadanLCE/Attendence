package com.attend.dream.mapper;

import com.attend.dream.domain.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface DepartmentMapper {
    //查询所有的部门
    @Select("select id,depCode,depJob,depName,depBossCode,depTopId\n" +
            "from department")
    List<Department> getDepartments();

    //通过编码查询Code
    @Select("select id,depCode,depJob,depName,depBossCode,depTopId\n" +
            "from department where depCode = #{depCode} ")
    Department getDepartmentByDepCode(String depCode);

    //模糊查询Code
    @Select("select id,depCode,depJob,depName,depBossCode,depTopId\n" +
            "from department where depCode LIKE CONCAT('%',#{depCode},'%') ")
    List<Department> getDepartmentsByDepCode(String depCode);




    //模糊查询CodeMax
    @Select("<script>"+
            "select id,depCode,depJob,depName,depBossCode,depTopId from department" +
            "<where>"+
             "<if test='depCode != null'>"+
             "and depCode LIKE '%${depCode}%'" +
             "</if>"+
            "</where>"+
            "</script>")
    List<Department> getDepartmentsByDepCodeMax(@Param("depCode") String depCode);
//    @Select("select id,depCode,depJob,depName,depBossCode,depTopId\n" +
//            "from department where depCode LIKE CONCAT('%',#{depCode},'%') ")

    @Select("select id,depCode,depJob,depName,depBossCode,depTopId\n" +
            "from department where depCode LIKE CONCAT('%',#{depCode},'%') ")

    //添加 部门
    @Insert("insert into department (depCode,depName,depBossCode,depJob,depTopId)\n" +
            "values(#{depCode},#{depName},#{depBossCode},#{depJob},#{depTopId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean insertDepartments(Department department);

    //更新部门
    @Update("update department set depCode=#{depCode},depName=#{depName},depBossCode=#{depBossCode},\n" +
            "depJob=#{depJob},depTopId=#{depTopId} \n " +
            "where id = #{id}\n")
    Boolean updateDepartment(Department department);

    //删除
    @Delete("delete from department where id = #{id}")
    Boolean deleteDepartment(int id);


}
