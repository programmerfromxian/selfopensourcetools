package com.opensource.tools.db.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-21
 */
@Data
public class RecordResult {

    private List<String> columnList;

    private List<List<Object>> recordList;

    private int totalCount;

}
