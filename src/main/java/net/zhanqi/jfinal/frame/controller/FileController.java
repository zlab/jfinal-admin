package net.zhanqi.jfinal.frame.controller;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.upload.UploadFile;

import java.io.File;

/**
 * FileController
 */
public class FileController extends Controller {

    private static final Logger log = Logger.getLogger(FileController.class);
    private static final String FILE_DIR = "/home/file/";

    public void index() {

    }

    public void upload() {
        UploadFile upload = getFile();
        File file = upload.getFile();
        log.info(file.getAbsolutePath());
    }

    public void download() {
        String key = getPara("key");
        File file = new File(FILE_DIR + key);
        if (!file.exists()) {
            renderError(404);
            return;
        }

        renderFile(file);
    }


}