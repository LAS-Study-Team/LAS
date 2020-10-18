package cn.las.service.impl;

import cn.las.dao.IClassDao;
import cn.las.domain.IClass;
import cn.las.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IClassServiceImpl implements IClassService {

    @Autowired
    IClassDao iClassDao;

    public IClass findByClassName(String classname) throws Exception {
        return iClassDao.findByClassName(classname);
    }

    public IClass findById(Integer id) throws Exception {
        return iClassDao.findById(id);
    }

    public List<IClass> findAll() throws Exception {
        return iClassDao.findAll();
    }

    public void addClass(IClass iClass) throws Exception {
        iClassDao.addClass(iClass);
    }

    public void updateClass(IClass iClass) throws Exception {
        iClassDao.updateClass(iClass);
    }

    public void deleteByClassId(Integer id) throws Exception {
        iClassDao.deleteByClassId(id);
    }
}
