package com.hef.review.designpatterns.structural.composite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式： 树形结构
 * \@Date 2022/10/20
 *
 * @Author lifei
 */
public class FileSystemNode {

    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    public int currentFileSize() {
        if (isFile) {
            return 1;
        }
        int res = 0;
        for (FileSystemNode subNode : subNodes) {
            res += subNode.currentFileSize();
        }
        return res;
    }

    private int currentFileSize(String path) {
        File file = new File(path);
        int res = 0;
        if (file.exists() && file.isDirectory()) {
            String[] subFileList = file.list((subFile, filename) -> subFile.isFile());
            res += subFileList.length;
            String[] subDirList = file.list((subFile, filename) -> subFile.isDirectory());
            for (String dir : subDirList) {
                String subDir = path + File.separator + dir;
                res += currentFileSize(subDir);
            }
        } else if (file.exists() && file.isFile()) {
            res += 1;
        }
        return res;
    }


    public long countSizeOfFiles() {
        if (isFile) {
            File file = new File(path);
            if (!file.exists()) return 0l;
            return file.length();
        }
        long res = 0;
        for (FileSystemNode subNode : subNodes) {
            res += subNode.currentFileSize();
        }
        return res;
    }

    private long countSizeOfFiles(String path) {
        File file = new File(path);
        long res = 0;
        if (file.exists() && file.isDirectory()) {
            String[] subFileList = file.list((subFile, filename) -> subFile.isFile());
            for (String filename : subFileList) {
                String subFilePath = path + File.separator + filename;
                res += new File(subFilePath).length();
            }
            String[] subDirList = file.list((subFile, filename) -> subFile.isDirectory());
            for (String dir : subDirList) {
                String subDir = path + File.separator + dir;
                res += currentFileSize(subDir);
            }
        } else if (file.exists() && file.isFile()) {
            res += file.length();
        }
        return res;
    }

    public String getPath() {
        return path;
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; ++i) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }
        if (i < size) {
            subNodes.remove(i);
        }
    }

}
