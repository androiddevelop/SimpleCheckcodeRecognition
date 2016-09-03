package me.codeboy.checkcode.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片操作，读取图片，得到像素点
 * 该操作具有针对性,本类仅处理资源中验证码,如果要用于其他验证码的识别,请重新实现
 *
 * @author Yuedong Li
 */
public class CBImageUtil extends ICBImageUtil {

    // 合法尺寸
    private final int WIDTH = 40;
    private final int HEIGHT = 10;

    // 验证码特征
    private final int NUMBER = 4;
    private final int SINGLE_WIDTH = WIDTH / NUMBER;
    private final int MAIN_COLOR = 0xd30000; // 主色

    /**
     * 有选择的返回验证码的有效像素点
     *
     * @param image 图片
     * @return 颜色数组
     * @throws IOException
     */
    @Override
    protected int[][] getRGB(BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        if (width != WIDTH || height != HEIGHT)
            throw new IOException(
                    "Pictures are not legitimate because the size");

        int[][] rgb = new int[HEIGHT + 2][WIDTH + 2];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < NUMBER; j++) {
                int startPixel = SINGLE_WIDTH * j + 2;
                for (int k = startPixel; k < startPixel + 6; k++) {
                    rgb[i + 1][k + 1] = (image.getRGB(k, i) & 0xffffff) == MAIN_COLOR ? 1
                            : 0;
                }
            }
        }
        return rgb;
    }

    /**
     * 进一步处理图片，去除干扰
     *
     * @param rgb 像素值
     * @return 处理后的rgb
     */
    @Override
    protected int[][] furtherProcessRGB(int[][] rgb) {
        for (int i = 1; i < rgb.length - 1; i++) {
            for (int j = 1; j < rgb[0].length - 1; j++) {
                if (rgb[i][j] == 0)
                    continue;
                if (rgb[i - 1][j - 1] == 0
                        && rgb[i - 1][j] == 0
                        && rgb[i - 1][j + 1] == 0
                        && rgb[i][j - 1] == 0
                        && rgb[i][j + 1] == 0
                        && rgb[i + 1][j - 1] == 0
                        && rgb[i + 1][j] == 0
                        && rgb[i + 1][j + 1] == 0) {
                    rgb[i][j] = 0;
                }
            }
        }
        return rgb;
    }

    @Override
    public double[][] getFormatData(File file) throws IOException {
        int[][] rgb = processAndGetRGB(file);
        double[][] formatData = new double[4][60];
        for (int j = 0; j < 4; j++) {
            int startPixel = j * 10 + 3;
            int index = 0;
            for (int i = 1; i < rgb.length - 1; i++) {
                for (int k = startPixel; k < startPixel + 6; k++) {
                    formatData[j][index++] = rgb[i][k] == 0 ? 0 : 1;
                }
            }
        }
        return formatData;
    }
}
