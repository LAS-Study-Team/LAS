package cn.las.service.impl;

import cn.las.dao.LaboratoryDao;
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    LaboratoryDao laboratoryDao;

<<<<<<< HEAD
    public void updateLaboratoryState(int id)throws Exception {
        laboratoryDao.updateLaboratoryState(id);
    }

    public void updateLaboratoryPnum(int id)throws Exception {
        laboratoryDao.updateLaboratoryPnum(id);
    }

    public void updateLaboratoryType(int id)throws Exception {
=======
    public void updateLaboratoryState(int id) {
        laboratoryDao.updateLaboratoryState(id);
    }

    public void updateLaboratoryPnum(int id) {
        laboratoryDao.updateLaboratoryPnum(id);
    }

    public void updateLaboratoryType(int id) {
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        laboratoryDao.updateLaboratoryType(id);
    }

}
