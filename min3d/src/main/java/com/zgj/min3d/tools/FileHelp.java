package com.zgj.min3d.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHelp
{
    /**
     * 图片缩放并生成单个文件到指定路径下
     * 拷贝文件路径（带文件名)
     *
     * @return
     */
    public static void copyFile(String fromFile, String toFile)
    {
        boolean proportion = true;
        int outputWidth = 480;
        int outputHeight = 270;
        try {
            File f2 = new File(fromFile);

            BufferedImage bi2 = ImageIO.read(f2);
            if (bi2.getWidth() <= outputWidth && bi2.getHeight() <= outputHeight) {
                proportion = false;
            }
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            if (proportion == true) {
                // 为等比缩放计算输出的图片宽度及高度
                double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
                double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
                // 根据缩放比率大的进行缩放控制
                double rate = rate1 < rate2 ? rate1 : rate2;
                newWidth = (int) (((double) bi2.getWidth(null)) / rate);
                newHeight = (int) (((double) bi2.getHeight(null)) / rate);
            } else {
                newWidth = outputWidth; // 输出的图片宽度
                newHeight = outputHeight; // 输出的图片高度
            }
            BufferedImage to = new BufferedImage(newWidth, newHeight,

                    BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = to.createGraphics();
            Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, "png", new File(toFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 特殊字符处理
     *
     * @param name 待处理的字符串
     * @return 返回处理后的字符串
     */
    public static String clearStr(String name)
    {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        return m.replaceAll("").trim();
    }

    /**
     * 文件生成自定义MD5后缀码
     *
     * @param path 文件路径
     * @return MD5
     */
    public static String getMyMd5(String path)
    {
        try {
            String File_md5 = MD5Util.getMD5(path);
            String md5 = MD5Util.getMD5String(File_md5 + "20160822");
            System.out.println("加上自定义密钥计算后的md5串号为:" + md5);
            return md5;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMyMd5(InputStream is)
    {
        try {
            String File_md5 = MD5Util.getMD5(is);
            String md5 = MD5Util.getMD5String(File_md5 + "20160822");
            System.out.println("加上自定义密钥计算后的md5串号为:" + md5);
            return md5;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException
    {
        File ftptempFile = new File(path + File.separator + filename);
        if (ftptempFile.exists()) {
            ftptempFile.delete();
        }

        File parentFile = ftptempFile.getParentFile();
        parentFile.mkdirs();
        OutputStream os = new BufferedOutputStream(new FileOutputStream(ftptempFile));

        int len = 0;
        byte[] buffer = new byte[500];

        while (-1 != (len = stream.read(buffer))) {
            os.write(buffer, 0, len);
        }
        stream.close();
        os.flush();
        os.close();

    }
}
