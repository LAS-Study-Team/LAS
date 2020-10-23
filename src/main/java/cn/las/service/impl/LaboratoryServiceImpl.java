package cn.las.service.impl;

import cn.las.dao.LaboratoryDao;
import cn.las.domain.Laboratory;
import cn.las.mapper.LaboratoryMapper;
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    LaboratoryDao laboratoryDao;

    @Autowired
    LaboratoryMapper laboratoryMapper;

    @Override
    public void updateLaboratoryStatus(int id, Integer state) throws Exception {
        laboratoryMapper.updateLaboratoryStatus(state, id);
    }

    @Override
    public void updateLaboratoryPnum(int id, Integer size) throws Exception {
        laboratoryMapper.updateLaboratoryPnum(size, id);
    }

    @Override
    public void updateLaboratoryType(String type, Integer id) throws Exception {
        laboratoryMapper.updateLaboratoryType(type, id);
    }

    @Override
    public List<Laboratory> findAll() throws Exception {
        return laboratoryDao.findAll();
    }

    @Override
    public Laboratory findById(Integer id) throws Exception {
        return laboratoryDao.findById(id);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        laboratoryDao.deleteById(id);
    }

    @Override
    public void addLaboratory(Laboratory laboratory) throws Exception {
        laboratoryMapper.addLaboratory(laboratory);
    }

    @Override
    public List<Laboratory> findByType(String type) throws Exception {
        return laboratoryDao.findByType(type);
    }

    @Override
    public boolean isEnable(Integer id) throws Exception {
        return laboratoryDao.findLaboratoryStatusById(id) == 0 ? false : true;
    }
}
