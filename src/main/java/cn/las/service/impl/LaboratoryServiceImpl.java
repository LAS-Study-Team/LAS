package cn.las.service.impl;

import cn.las.dao.LaboratoryDao;
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    LaboratoryDao laboratoryDao;

    public void updateLaboratoryState(int id)throws Exception {
        laboratoryDao.updateLaboratoryState(id);
    }

    public void updateLaboratoryPnum(int id)throws Exception {
        laboratoryDao.updateLaboratoryPnum(id);
    }

    public void updateLaboratoryType(int id)throws Exception {
        laboratoryDao.updateLaboratoryType(id);
    }

}
