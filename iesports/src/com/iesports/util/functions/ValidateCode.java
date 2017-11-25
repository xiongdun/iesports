/**
 * 
 */
package com.iesports.util.functions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * ������������֤�빤����
 * @author xiongdun
 * @created 2016��9��19�� ����7:06:19
 * @since 
 */
public class ValidateCode {
	
	public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	private static Random random = new Random();
	
	/**
	 * ������ʹ��ϵͳĬ���ַ�Դ������֤��
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:47:44
	 * @since 
	 * @param verifySize
	 * @return
	 */
	public static String genernateVerifyCode(int verifySize) {
		return genernateVerifyCode(verifySize, VERIFY_CODES);
	}
	
	/**
	 * ������ʹ���Զ���ָ�����ַ�Դ������֤��
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:48:32
	 * @since 
	 * @param verifySize
	 * @param source
	 * @return
	 */
	public static String genernateVerifyCode(int verifySize, String source) {
		if (source == null || source.length() == 0) {
			source = VERIFY_CODES;
		}
		int codesLen = source.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(source.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}
	
	/**
	 * �����������֤���ļ�������������֤��ֵ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:58:52
	 * @since 
	 * @param width
	 * @param height
	 * @param outputFile
	 * @param verifySize
	 * @return
	 * @throws IOException 
	 */
	public static String outputVerifyImage(int width, int height, File outputFile, int verifySize) throws IOException {
		String verifyCode = genernateVerifyCode(verifySize);
		outputImage(width, height, outputFile, verifyCode);
		return verifyCode;
	}
	
	/**
	 * �����������֤��ͼƬ������������֤��ֵ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:59:25
	 * @since 
	 * @param width
	 * @param height
	 * @param os
	 * @param verifySize
	 * @return
	 * @throws IOException 
	 */
	public static String outputVerifyImage(int width, int height, OutputStream os, int verifySize) throws IOException {
		String verifyCode = genernateVerifyCode(verifySize);
		outputImage(width, height, os, verifyCode);
		return verifyCode;
	}
	
	/**
	 * ������������ɵ�ͼƬͼ���ļ�
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:46:57
	 * @since 
	 * @param width
	 * @param height
	 * @param outputFile
	 * @param code
	 * @throws IOException 
	 */
	public static void outputImage(int width, int height, File outputFile, String code) throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			outputImage(width, height, fos, code);
			fos.close();
		} catch (IOException e) {
			throw e;
		}
		
		
	}
	
	/**
	 * ������������ɵ�ͼƬ��
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:47:16
	 * @since 
	 * @param width
	 * @param height
	 * @param os
	 * @param code
	 * @throws IOException 
	 */
	public static void outputImage(int width, int height, OutputStream os, String code) throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Random random = new Random();
		Graphics2D graphics2d = image.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { 
				Color.WHITE, 
				Color.CYAN, 
				Color.GRAY, 
				Color.LIGHT_GRAY, 
				Color.MAGENTA, 
				Color.ORANGE, 
				Color.PINK, 
				Color.YELLOW,
				Color.RED,
				Color.GREEN
			};
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[random.nextInt(colorSpaces.length)];
			fractions[i] = random.nextFloat();
		}
		Arrays.sort(fractions);  
        
        graphics2d.setColor(colorSpaces[random.nextInt(colorSpaces.length)]);// ���ñ߿�ɫ  
        graphics2d.fillRect(0, 0, width, height);  
          
        Color c = getRandomColor(200, 250);  
        graphics2d.setColor(c);// ���ñ���ɫ  
        graphics2d.fillRect(0, 2, width, height - 4);  
          
        //���Ƹ�����  
        Random rand = new Random();  
        graphics2d.setColor(getRandomColor(100, 255));// ������������ɫ  
        for (int i = 0; i < 500; i++) {  
            int x = random.nextInt(width - 1);  
            int y = random.nextInt(height - 1);  
            int xl = random.nextInt(6) + 1;  
            int yl = random.nextInt(12) + 1;  
            graphics2d.drawLine(x, y, x + xl + 40, y + yl + 20);  
        }  
          
        // ������  
        float yawpRate = 0.1f;// ������  
        int area = (int)(yawpRate * width * height);  
        for (int i = 0; i < area; i++) {  
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        } 
        shear(graphics2d, width, height, c);// ʹͼƬŤ��  
  
        graphics2d.setColor(getRandomColor(80, 160));  
        int fontSize = height - 4;  
        Font font = new Font("Axure Handwriting", Font.ITALIC, fontSize);  
        graphics2d.setFont(font);  
        char[] chars = code.toCharArray();  
        for(int i = 0; i < verifySize; i++) {  
            AffineTransform affine = new AffineTransform();  
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize / 2, height / 2);  
            graphics2d.setTransform(affine);  
            graphics2d.drawChars(chars, i, 1, ((width-10) / verifySize) * i + 5, height / 2 + fontSize/2 - 10);  
        }
        graphics2d.dispose();
        ImageIO.write(image, "jpg", os);  
	}
	
	/**
	 * ��������ȡ�����ɫֵ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:02:02
	 * @since 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandomColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
        if (bc > 255) {
        	bc = 255; 
        }
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
	}
	
	/**
	 * ��������ȡ���������ɫֵ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:01:07
	 * @since 
	 * @return
	 */
	private static int getRandomIntColor() {
		int[] rgb = getRandomRGB();  
        int color = 0;  
        for (int c : rgb) {  
            color = color << 8;  
            color = color | c;  
        }  
        return color;
	}
	
	/**
	 * ��������ȡ�����rgbֵ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:03:42
	 * @since 
	 * @return
	 */
	private static int[] getRandomRGB() {
		int[] rgb = new int[3];
		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}
	
	/**
	 * ����������ͼƬ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:12:04
	 * @since 
	 * @param graphics ͼƬ��Ƭ
	 * @param width ���
	 * @param height �߶�
	 * @param color ��ɫ
	 */
	private static void shear(Graphics graphics, int width, int height, Color color) {
		shearX(graphics, width, height, color);
		shearY(graphics, width, height, color);
	}
	
	/**
	 * ��������X�����ͼƬ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:13:41
	 * @since 
	 * @param graphics
	 * @param width
	 * @param height
	 * @param color
	 */
	private static void shearX(Graphics graphics, int width, int height, Color color) {
		int period = random.nextInt(2);
		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);
		for (int i = 0; i < height; i++) {
			double d = (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			graphics.copyArea(0, i, width, 1, (int) d, 0);
			if (borderGap) {
				graphics.setColor(color);
				graphics.drawLine((int) d, i, 0, i);
				graphics.drawLine((int) d + width, i, width, i);
			}
		}
	}
	
	/**
	 * ��������Y�����ͼƬ
	 * @author xiongdun
	 * @created 2016��9��27�� ����11:13:56
	 * @since 
	 * @param graphics
	 * @param width
	 * @param height
	 * @param color
	 */
	private static void shearY(Graphics graphics, int width, int height, Color color) {
		int period = random.nextInt(40) + 10;
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < width; i++) {
			double d =  (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			graphics.copyArea(i, 0, 1, height, 0, (int) d);
			if (borderGap) {
				graphics.setColor(color);
				graphics.drawLine(i, (int) d, i, 0);
				graphics.drawLine(i, (int) d + height, i, height);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		File dir = new File("F:/ProjectTestImage/validateImage");
		int width = 80, height = 30;
		for (int i = 0; i < 50; i++) {
			String verifyCode = genernateVerifyCode(4);
			File file = new File(dir, verifyCode + ".jpg");
			outputImage(width, height, file, verifyCode);
		}
	}
	
}
