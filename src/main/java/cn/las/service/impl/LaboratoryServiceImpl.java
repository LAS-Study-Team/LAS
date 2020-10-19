package cn.las.service.impl;

import cn.las.dao.LaboratoryDao;
<<<<<<< HEAD
=======
import cn.las.domain.Laboratory;
import cn.las.mapper.LaboratoryMapper;
>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    LaboratoryDao laboratoryDao;

<<<<<<< HEAD
    public void updateLaboratoryState(int id) {
        laboratoryDao.updateLaboratoryState(id);
=======
    @Autowired
    LaboratoryMapper laboratoryMapper;

    @Override
    public void updateLaboratoryStatus(Integer status, Integer id) throws Exception {
        laboratoryMapper.updateLaboratoryStatus(status, id);
    }

    @Override
    public void updateLaboratoryPnum(Integer size, Integer id) throws Exception {
        laboratoryMapper.updateLaboratoryPnum(size, id);
    }

    @Override
    public void updateLaboratoryType(String type, Integer id) throws Exception {
        laboratoryMapper.updateLaboratoryType(type, id);
    }

    @Override
    public List<Laboratory> findAll() throws Exception {
        return laboratoryDao.findAll();
>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d
    }

    public void updateLaboratoryPnum(int id) {
        laboratoryDao.updateLaboratoryPnum(id);
    }

<<<<<<< HEAD
    public void updateLaboratoryType(int id) {
        laboratoryDao.updateLaboratoryType(id);
    }

=======
    @Override
    public void deleteById(Integer id) throws Exception {
        laboratoryDao.deleteById(id);

    }

    @Override
    public void addLaboratory(Laboratory laboratory) throws Exception {
        laboratoryMapper.addLaboratory(laboratory);
    }
>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d
}
