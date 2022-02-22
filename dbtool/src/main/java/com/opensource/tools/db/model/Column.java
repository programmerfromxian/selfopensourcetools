package com.opensource.tools.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column {

    private String name;

    private String type;


}
