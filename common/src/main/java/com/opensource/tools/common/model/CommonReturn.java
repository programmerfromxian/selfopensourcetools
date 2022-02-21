package com.opensource.tools.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonReturn {

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 成功响应的数据
     */
    private Object data;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

}
