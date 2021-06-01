package com.qkm.TTMS.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="movie_role")
@Data
public class Roles implements Serializable {
    /**
     * 权限ID
     */
    @TableId(value = "role_id")
    private Integer roleId;

    /**
     * 权限名称
     */
    @TableField(value = "role_name")
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}