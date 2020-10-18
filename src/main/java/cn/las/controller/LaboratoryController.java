package cn.las.controller;

import cn.las.domain.Laboratory;
import cn.las.domain.Message;
import cn.las.service.LaboratoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("laboratory")
public class LaboratoryController {

    private static Logger logger = Logger.getLogger(LaboratoryController.class);

    @Autowired
    LaboratoryService laboratoryService;

    /**
     * @param maps 前端传输的数据
     *          {
     *              id:...,
     *              status:...
     *          }
     * @return
     * @throws Exception
     *
     * 修改实验室状态信息，status=0 不可用 | 可用
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public Message updateLaboratoryState(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 进行非空验证
        Integer id = (Integer) maps.get("id");
        Integer status = (Integer) maps.get("status");
        if(id == null || status == null) {
            message.setCode(403);
            message.setMessage("参数信息有误");
            return message;
        }

        // 更改实验室的状态信息
        try {
            laboratoryService.updateLaboratoryStatus(status, id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            message.setMessage("更改实验室状态信息失败");
            message.setCode(501);
            return message;
        }

        message.setCode(200);
        message.setMessage("实验室状态更新成功");
        return message;
    }


    /**
     * @return
     *
     * 实现所有课程查询--管理员
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Message findAll() {
        Message message = new Message();

        try {
            List<Laboratory> all = laboratoryService.findAll();
            message.putData("labs", all);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            message.setCode(500);
            message.setMessage("获取课程信息失败");
            return message;
        }


        message.setCode(200);
        message.setMessage("获取课程信息成功");
        return message;
    }


    /**
     * @param maps 前端传输的数据
     *          {
     *              id:...,
     *              size:...
     *          }
     * @return
     * @throws Exception
     *
     * 修改实验室容量信息
     */
    @RequestMapping(value = "updateSize", method = RequestMethod.POST)
    @ResponseBody
    public Message updateLaboratoryPnum(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 进行非空验证
        Integer id = (Integer) maps.get("id");
        Integer size = (Integer) maps.get("size");
        if(id == null || size == null) {
            message.setCode(403);
            message.setMessage("参数信息有误");
            return message;
        }

        // 更改实验室的状态信息
        try {
            laboratoryService.updateLaboratoryPnum(size, id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            message.setMessage("更改实验室容量信息失败");
            message.setCode(501);
            return message;
        }

        message.setCode(200);
        message.setMessage("实验室容量更新成功");
        return message;
    }



}
