package me.codeboy.checkcode.util;

import me.codeboy.common.base.io.util.CBFileUtil;

import java.io.File;
import java.io.IOException;

/**
 * 将图片的信息转化为满足SVM的数字格式，保存在文件中
 * 
 * @author Yuedong Li
 * 
 */
public class CBDataConvert {

	private static ICBImageUtil imageUtil = new CBImageUtil();

	/**
	 * 验证码文件夹
	 * 
	 * @param imageDirectoryPath
	 *            验证码文件夹
	 * @param outFilePath
	 *            输出文件路径
	 * @throws IOException
	 */
	public static void convertAndSave(String imageDirectoryPath,
			String outFilePath) throws IOException {
		File imageDir = new File(imageDirectoryPath);
		if (imageDir.isFile()) {
			throw new IOException("It is a file but a directory");
		}

		File[] images = imageDir.listFiles();
		StringBuffer buff = new StringBuffer();

		for (File image : images) {
			String imageName = image.getName();
			int[][] rgb = imageUtil.processAndGetRGB(image);
			for (int j = 0; j < 4; j++) {
				int startPixel = j * 10 + 3;
				int index = 1;
				buff.append(Integer.valueOf(imageName.substring(j, j + 1)));
				for (int i = 1; i < rgb.length - 1; i++) {
					buff.append(" ");
					for (int k = startPixel; k < startPixel + 6; k++) {
						buff.append(index++);
						buff.append(':');
						buff.append(rgb[i][k]);
						buff.append(' ');
					}
				}
				buff.append('\n');
			}
		}

		CBFileUtil.saveContentToFile(buff.toString(), outFilePath);
	}
}
