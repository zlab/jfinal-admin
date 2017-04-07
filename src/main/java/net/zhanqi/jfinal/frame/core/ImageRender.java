package net.zhanqi.jfinal.frame.core;

import com.jfinal.render.Render;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.RenderedImage;
import java.io.IOException;

/**
 * Created by zhanqi on 2015/2/5.
 */
public class ImageRender extends Render {

    private RenderedImage image;

    public ImageRender(RenderedImage image) {
        this.image = image;
    }

    @Override
    public void render() {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream sos = null;

        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "jpeg", sos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
