package com.hef.review.designpatterns.structural.composite;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录的实现
 * @Date 2022/10/20
 * @Author lifei
 */
public class DirectoryItem extends FileSystemNodeParent{

    private List<FileSystemNodeParent> subNodes = new ArrayList<>();

    public DirectoryItem(String path) {
        super(path);
    }

    @Override
    public int countNumberOfFiles() {
        int res = 0;
        for (FileSystemNodeParent fileDir : subNodes) {
            res += fileDir.countNumberOfFiles();
        }
        return res;
    }

    @Override
    public long countSizOfFiles() {
        long res = 0;
        for (FileSystemNodeParent fileDir : subNodes) {
            res += fileDir.countSizOfFiles();
        }
        return res;
    }

    public void addSubNode(FileSystemNodeParent fileSystemNode) {
        this.subNodes.add(fileSystemNode);
    }

    public void removeSubNode(FileSystemNodeParent fileSystemNode) {
        int size = subNodes.size();
        int i = 0;
        for (; i<subNodes.size(); i++) {
            if (StringUtils.equals(subNodes.get(i).getPath(), fileSystemNode.path)) {
                break;
            }
        }
        if (i<subNodes.size()) {
            subNodes.remove(i);
        }
    }
}
