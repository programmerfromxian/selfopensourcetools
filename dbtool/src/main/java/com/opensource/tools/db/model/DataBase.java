package com.opensource.tools.db.model;

import lombok.Data;

import java.util.List;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-20
 */
@Data
public class DataBase {

    private String name;

    private List<Table> tableList;

}
