package com.attend.dream.service;

import com.attend.dream.domain.Classes;
import com.attend.dream.domain.Station;
import com.attend.dream.mapper.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    //单个删除
    public Classes getClaById(int id){
        Classes cla = classesMapper.getClassesById(id);
        return  cla;
    }

    //删除单个班次
    public boolean deleteCla(int id){
        classesMapper.deleteClasses(id);
        return true;
    }

}
