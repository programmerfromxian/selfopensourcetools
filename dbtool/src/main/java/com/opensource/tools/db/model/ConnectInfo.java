package com.opensource.tools.db.model;

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
public class ConnectInfo {

    private String type;

    private String username;

    private String password;

    private String host;

    private int port;

    private String url;

}
