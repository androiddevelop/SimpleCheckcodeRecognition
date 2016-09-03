package me.codeboy.checkcode.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ICBImageUtil {
	/**
	 * 读取图片
	 * 
	 * @param image
	 *            图片
	 * @return
	 * @throws IOException
	 */
	private BufferedImage getImage(File image) throws IOException {
		return ImageIO.read(image);
	}

	/**
	 * 有选择的返回验证码的有效像素点
	 * 
	 * @param image
	 *            图片
	 * @return 颜色数组
	 * @throws IOException
	 */
	protected abstract int[][] getRGB(BufferedImage image) throws IOException ;
	
	/**
	 * 进一步处理图片，去除干扰
	 * 
	 * @param rgb
	 *            像素值
	 * @return 处理后的rgb
	 */
	protected abstract int[][] furtherProcessRGB(int[][] rgb) ;

	/**
	 * 一步获取处理后图片的rgb值
	 * 
	 * @param image file
	 * @return rgb
	 * @throws IOException
	 */
	public int[][] processAndGetRGB(File image)
			throws IOException {
		return furtherProcessRGB(getRGB(getImage(image)));
	}


	/**
	 * 将图片转化为标准化数据
	 *
	 * @param file file
	 * @return 图片格式化后的数据
	 * @throws IOException
	 */
	 public abstract double[][] getFormatData(File file) throws IOException;

}
