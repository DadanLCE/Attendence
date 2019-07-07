package com.attend.dream.service;

import com.attend.dream.domain.Classes;
import com.attend.dream.domain.Station;
import com.attend.dream.mapper.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: 班次
 * */

@Service
public class ClassesService {

    @Autowired(required = false)
    private ClassesMapper classesMapper;

    //模糊查询 分页查询的页面数据
    public List<Classes> getClassesByCode(int currentPage, int pageSize, String claCode){
        PageHelper.startPage(currentPage,pageSize);
        List<Classes> clas = classesMapper.getClassesByCalCode(claCode);
        return clas;
    }
    public PageInfo<Classes> getClassesByCodePage(int currentPage, int pageSize, String claCode){
        PageHelper.startPage(currentPage,pageSize);
        List<Classes> clas = classesMapper.getClassesByCalCode(claCode);
        PageInfo<Classes> pageInfo = new PageInfo<>(clas);
        return pageInfo;
    }

    //查询单个班次
    public Classes getClaById(int id){
        Classes cla = classesMapper.getClassesById(id);
        return  cla;
    }

    //删除单个班次
    public Boolean deleteCla(int id){
        classesMapper.deleteClasses(id);
        return true;
    }
    //1 插入成功 2有相同的calCode编码 失败
    public String insertCla(Classes cla){
        String code = cla.getClaCode();
        String flag = "2";
        if (classesMapper.getClassesByCode(code) == null) {
            classesMapper.insertClasses(cla);
            flag = "1";
        }

        return flag;
    }

        //更新，1成功，2重复
    public String updateCla(Classes cla){
        classesMapper.updateClasses(cla);
        return "1";
    }

}
