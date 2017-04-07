package net.zhanqi.jfinal.frame.util;

public class DaoUtils {

    public static String createInStatement(int repeat) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < repeat; i++) {
            sb.append(", ?");
        }

        return "(" + sb.toString().substring(2) + ")";
    }


}
