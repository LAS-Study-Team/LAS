package cn.las.controller;

import cn.las.domain.Message;
import cn.las.mapper.LaboratoryMapper;
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("laboratory")
public class LaboratoryController {

    @Autowired
    LaboratoryService laboratoryService;

<<<<<<< HEAD
=======
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
            e.printStackTrace();
            logger.warn(e.getMessage());
            message.setMessage("更改实验室状态信息失败");
            message.setCode(501);
            return message;
        }

        message.setCode(200);
        message.setMessage("实验室状态更新成功");
        return message;
    }
>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d

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

<<<<<<< HEAD
=======
    /**
     *
     * @param maps  前端传输的数据
     * {
     *      name:...,
     *      type:...,
     *      size:...,
     *      location:..,
     *      status:...
     * }
     * @return
     *
     * 进行实验室的添加
     */
    @RequestMapping(value = "addLab", method = RequestMethod.POST)
    @ResponseBody
    public Message addLaboratory(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 获取参数
        String name = (String) maps.get("name");
        String type = (String) maps.get("type");
        Integer size = (Integer) maps.get("size");
        String location = (String) maps.get("location");
        Integer status = (Integer) maps.get("status");

        // 非空验证
        if(name == null || type == null || size == null || location == null || status == null) {
            message.setCode(403);
            message.setMessage("参数有误");
            return message;
        }

        // 数据封装
        Laboratory lab = new Laboratory();
        lab.setLocation(location);
        lab.setName(name);
        lab.setSize(size);
        lab.setStatus(status);
        lab.setType(type);

        // 添加实验室信息
        try {
            laboratoryService.addLaboratory(lab);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            message.setCode(401);
            message.setMessage("数据冲突");
            return message;
        }

        // 返回成功信息
        message.setMessage("添加实验室成功");
        message.setCode(200);
        return message;
    }


    /**
     * @param maps 前端传输的实验室新的类型信息
     * {
     *     id:...,
     *     type:...
     * }
     * @return
     */
    @RequestMapping(value = "updateType", method = RequestMethod.POST)
    @ResponseBody
    public Message updateLaboratoryType(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 获取数据 + 非空验证
        String type = (String) maps.get("type");
        Integer id = (Integer) maps.get("id");
        if(type == null) {
            message.setCode(403);
            message.setMessage("参数有误");
            return message;
        }

        try {
            laboratoryService.updateLaboratoryType(type, id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            message.setCode(401);
            message.setMessage("修改实验室类型信息失败");
            return message;
        }

        // 返回操作信息
        message.setMessage("修改实验室类型信息成功");
        message.setCode(200);
        return message;
    }


    /**
     * @param maps 删除实验室信息
     * {
     *     id:...
     * }
     * @return
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteLaboratoryById(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 获取参数+非空验证
        Integer id = (Integer) maps.get("id");
        if(id == null) {
            message.setCode(403);
            message.setMessage("参数有误");
            return message;
        }

        try {
            // 事先删除Arrange依赖

            /**
             *
             *
             *
             *
             *
             *
             *
             */

            laboratoryService.deleteById(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            message.setCode(401);
            message.setMessage("删除实验室失败");
            return message;
        }

        message.setMessage("删除实验室成功");
        message.setCode(200);
        return message;
    }
>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d
}
