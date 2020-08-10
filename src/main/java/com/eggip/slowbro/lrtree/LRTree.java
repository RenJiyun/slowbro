package com.eggip.slowbro.lrtree;

import java.util.List;

public interface LRTree {

    /**
     * 获取指定节点下的所有叶子节点
     * 
     * @param pNode
     * @return
     */
    List<LRTreeNode> getLeafList(LRTreeNode pNode);

    /**
     * 获取指定节点下所有叶子节点的总数
     * 
     * @param pNode
     * @return
     */
    int countLeaf(LRTreeNode pNode);

    /**
     * 获取指定节点所在的层级，定义根节点的层级为0
     * 
     * @param node
     * @return
     */
    int getLevel(LRTreeNode node);

    /**
     * 获取指定节点的所有祖先节点，返回的列表从根节点开始
     * 
     * @param cNode
     * @return
     */
    List<LRTreeNode> getAncestorList(LRTreeNode cNode);

    /**
     * 获取指定节点的父节点
     * 
     * @param cNode
     * @return
     */
    LRTreeNode getParentNode(LRTreeNode cNode);

    /**
     * 获取指定节点的所有子节点
     * 
     * @param pNode
     * @return
     */
    List<LRTreeNode> getChildrenNodeList(LRTreeNode pNode);

    /**
     * 获取指定节点的所有直接子节点
     * 
     * @param pNode
     * @return
     */
    List<LRTreeNode> getSonNodeList(LRTreeNode pNode);

    /**
     * 获取指定节点的所有兄弟节点
     * 
     * @param node
     * @return
     */
    List<LRTreeNode> getBrotherNodeList(LRTreeNode node);

    /**
     * 获取相对层级下的所有节点
     * 
     * @param node
     * @param level
     * @return
     */
    List<LRTreeNode> getNodeListByLevel(LRTreeNode node, int level);

    /**
     * 在指定节点下新增子节点
     * 
     * @param pNode
     * @param cNode
     */
    void addChild(LRTreeNode pNode, LRTreeNode cNode);

    /**
     * 删除节点
     * 
     * @param node
     */
    void removeNode(LRTreeNode node);

    /**
     * 将指定节点以及它的子节点全部移动到指定的父节点下
     * 
     * @param pNode
     * @param cNode
     */
    void moveNode(LRTreeNode pNode, LRTreeNode cNode);

}