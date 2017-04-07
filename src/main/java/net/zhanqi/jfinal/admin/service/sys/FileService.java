package net.zhanqi.jfinal.admin.service.sys;

import java.io.File;

/**
 * 文件操作
 *
 * @author zhanqi
 * @since 2013-06-09
 */
public interface FileService {

    /**
     * 上传文件到文件服务器
     *
     * @param file
     * @param fileName
     * @return
     */
    String uploadFile(File file, String fileName);

    /**
     * 从文件服务器下载文件
     *
     * @param fileName
     * @return
     */
    File downloadFile(String fileName);
}
