package cn.las.controller;

<<<<<<< HEAD
import cn.las.domain.Message;
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("laboratory")
public class LaboratoryController {

<<<<<<< HEAD
    @Autowired
    LaboratoryService laboratoryService;


    /**
     * @param id 实验室id
     * @return  返回成功 | 失败信息
     * @throws Exception
     *
     * 更新实验室状态
     */
    public Message updateLaboratoryState(int id)throws Exception{
        Message message = new Message();
        laboratoryService.updateLaboratoryState(id);
        message.setCode(200);
        message.setMessage("实验室状态更新成功");
        return message;
    }
=======
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0

}
