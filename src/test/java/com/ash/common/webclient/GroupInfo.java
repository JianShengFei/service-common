package com.ash.common.webclient;


import lombok.Data;

import java.util.Date;

@Data
public class GroupInfo {

    private Integer id;

    private String groupName;

    private Integer groupOwnership;

    private String groupDesc;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}
