package com.opensource.tools.db.controller;

import com.opensource.tools.common.model.CommonReturn;
import com.opensource.tools.common.model.front.TreeNode;
import com.opensource.tools.db.model.*;
import com.opensource.tools.db.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Database工具启动类
 *
 * @author yangzai
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/opensource/database/")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public CommonReturn testConnection(@RequestBody ConnectInfo connectInfo) {
        databaseService.testConnection(connectInfo);
        return CommonReturn.builder().success(true).data("测试连接成功").build();
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public CommonReturn connect(@RequestBody ConnectInfo connectInfo) {
        ConnectResult connectResult = databaseService.connect(connectInfo);
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (DataBase dataBase : connectResult.getDataBaseList()) {
            TreeNode databaseTreeNode = new TreeNode();
            databaseTreeNode.setLabel(dataBase.getName());
            databaseTreeNode.setChildren(new ArrayList<>());
            databaseTreeNode.setNodeType("database");
            for (Table table : dataBase.getTableList()) {
                TreeNode tableTreeNode = new TreeNode();
                tableTreeNode.setLabel(table.getName());
                tableTreeNode.setChildren(new ArrayList<>());
                tableTreeNode.setNodeType("table");
                tableTreeNode.setParentNodeLabel(dataBase.getName());
                for (Column column : table.getColumnList()) {
                    TreeNode columnTreeNode = new TreeNode();
                    columnTreeNode.setLabel(column.getName() + " " + column.getType());
                    columnTreeNode.setNodeType("column");
                    columnTreeNode.setParentNodeLabel(table.getName());
                    tableTreeNode.getChildren().add(columnTreeNode);
                }
                databaseTreeNode.getChildren().add(tableTreeNode);
            }
            treeNodeList.add(databaseTreeNode);
        }
        return CommonReturn.builder().success(true).data(treeNodeList).build();
    }

    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public CommonReturn record(@RequestParam String tableName,
                               @RequestParam(defaultValue = "0") int pageNumber,
                               @RequestParam(defaultValue = "10") int pageSize,
                               @RequestBody ConnectInfo connectInfo) {
        RecordResult recordResult = databaseService.queryRecord(tableName, pageNumber, pageSize, connectInfo);
        return CommonReturn.builder().success(true).data(recordResult).build();
    }

}
