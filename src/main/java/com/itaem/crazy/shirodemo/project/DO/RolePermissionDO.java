package com.itaem.crazy.shirodemo.project.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role_permission")
public class RolePermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer permissionId;


}
