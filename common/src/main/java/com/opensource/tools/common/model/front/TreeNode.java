package com.opensource.tools.common.model.front;

import lombok.Data;

import java.util.List;

/**
 * 描述信息
 *
 * @author yangzai
 * @date 2022-02-21
 */
@Data
public class TreeNode {

    private String label;

    private String nodeType;

    private String parentNodeLabel;

    private List<TreeNode> children;

}
