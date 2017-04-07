package net.zhanqi.jfinal.admin.service.sys.impl;

import net.zhanqi.jfinal.admin.service.sys.FileService;

import java.io.File;

public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(File file, String fileName) {
        return fileName;
    }

    @Override
    public File downloadFile(String fileName) {
        return null;
    }

}
