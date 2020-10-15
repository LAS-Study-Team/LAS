package cn.las.dao;

import cn.las.domain.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleDao {

    @Select("select * from role where role.id=#{id}")
    Role findById(Integer id) throws Exception;
    
}
