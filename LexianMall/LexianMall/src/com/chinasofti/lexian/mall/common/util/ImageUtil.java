package com.chinasofti.lexian.mall.common.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import org.springframework.web.multipart.MultipartFile;
public class ImageUtil {

	public static void generateThumbnail(MultipartFile part, File thumbnailFilePath, int maxWidth, int maxHeight)
			throws IOException {
		saveImageAsJpg(part, thumbnailFilePath, maxWidth, maxHeight);
	}

	private static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		
		// 这里想实现在targetW，targetH范围内实现等比缩放。
		// 如果不需要等比缩放,则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 
	 * @param fromFileStr
	 *            需要压缩的源文件
	 * @param saveToFileStr
	 *            目的文件
	 * @param width
	 *            指定压缩后的宽度
	 * @param hight
	 *            指定压缩后的高度
	 * @throws IOException
	 */
	private static void saveImageAsJpg(MultipartFile part, File saveToFileStr, int width, int hight)
			throws IOException {
		String fromFileStr=part.getOriginalFilename();
		BufferedImage srcImage;
		String imgType = "JPEG";
		if (fromFileStr.toLowerCase().endsWith(".png")) {
			imgType = "PNG"; 
		}
		srcImage = ImageIO.read(part.getInputStream());
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveToFileStr);

	}

	/**
	 * 
	 * @param part
	 *            上传后的文件
	 * @param fileName
	 *            文件名陈
	 * @param fix
	 *            后缀名
	 * @param qulity
	 *            压缩质量比列
	 * @return
	 */
	public static boolean compressPic(MultipartFile part, String fileName, String fix, float qulity) {

		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams = null;
		// 指定写图片的方式为 jpg
		if ("PNG".equalsIgnoreCase(fix)) {
			try {
				part.transferTo(new File(fileName));
			} catch (Exception e) {
				return false;
			}
			return true;
		} else if ("BMP".equalsIgnoreCase(fix)) {
			imgWrier = ImageIO.getImageWritersByFormatName(fix).next();
			imgWriteParams = new BMPImageWriteParam();
		} else {
			imgWrier = ImageIO.getImageWritersByFormatName("jpeg").next();
			imgWriteParams = new JPEGImageWriteParam(null);
		}
		
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		
		// 这里指定压缩的程度，参数qality是取值0~1范围内
		imgWriteParams.setCompressionQuality(qulity);
		imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(
				new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(24, 24)));
		try {
			src = ImageIO.read(part.getInputStream());
			out = new FileOutputStream(fileName);
			imgWrier.reset();
			imgWrier.setOutput(ImageIO.createImageOutputStream(out));
			imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				out = null;
			}
		}
		return true;
	}
}
