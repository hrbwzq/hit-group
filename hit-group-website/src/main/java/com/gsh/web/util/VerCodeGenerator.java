package com.gsh.web.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成工具类
 * @author HIT1140320209
 *
 */
public class VerCodeGenerator
{
	private int imgWidth;
	private int imgHeight;
	private int jamFlag;
	private String str;
	private Random rand;

	/**
	 * 构造函数
	 * @param imgWidth 验证码图片宽度
	 * @param imgHeight 验证码图片高度
	 * @param str 验证码文字
	 * @param jamFlag 干扰线强度（条数）
	 */
	public VerCodeGenerator(int imgWidth, int imgHeight, String str, int jamFlag)
	{
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.str = str;
		this.jamFlag = jamFlag;
		this.rand = new Random();
	}
	
	/**
	 * 生成验证码图片
	 * @return 内存中的图片对象
	 */
	public BufferedImage generate()
	{
		BufferedImage img = new BufferedImage(this.imgWidth, this.imgHeight, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D g = (Graphics2D)img.getGraphics();
		draw(g);
		jam(g);
		g.dispose();
		return img;
	}
	
	/**
	 * 随机生成一个待渲染字符属性，包括位置偏移，旋转，字号，颜色
	 * @return 属性数组
	 */
	private int[] randAttribute()
	{
		int[] randAttr = new int[7];
		//随机X位置偏移+-3
		randAttr[0] = rand.nextInt(7) - 3;
		//随机Y位置偏移+-3
		randAttr[1] = rand.nextInt(7) - 3;
		//随机旋转角度+-45
		randAttr[2] = rand.nextInt(91) - 45;
		//随机字号+-3
		randAttr[3] = rand.nextInt(7) - 3;
		//随机颜色R0-255
		randAttr[4] = rand.nextInt(256);
		//随机颜色G0-255
		randAttr[5] = rand.nextInt(256);
		//随机颜色B0-255
		randAttr[6] = rand.nextInt(256);
		return randAttr;
	}
	
	/**
	 * 渲染文字
	 * @param g
	 */
	private void draw(Graphics2D g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, imgWidth, imgHeight);

		int sliceNum = str.length();
		double sliceLen = imgWidth / sliceNum;
		for(int i = 0; i < sliceNum; i++)
		{
			int[] randAttr = randAttribute();
			double posX = sliceLen * i + sliceLen / 4 + randAttr[0];
			double posY = imgHeight / 3 * 2 + randAttr[1];
			double rotate = randAttr[2];
			int fontSize = new Double((imgHeight + randAttr[3]) / 2).intValue();
			g.setColor(new Color(randAttr[4], randAttr[5], randAttr[6]));
			AffineTransform orig = g.getTransform();
			rotate = rotate / Math.PI;
			g.rotate(rotate, posX + (double)fontSize / 2, posY - (double)fontSize / 2);
			g.setFont(new Font("Serif", Font.BOLD, fontSize));
			g.drawString(String.valueOf(str.charAt(i)), (int)posX, (int)posY);
			g.setTransform(orig);
		}
	}
	
	/**
	 * 渲染干扰线
	 * @param g
	 */
	private void jam(Graphics2D g)
	{
		for(int i = 0; i < jamFlag; i++)
		{
			int x1 = rand.nextInt(imgWidth);
			int y1 = rand.nextInt(imgHeight);
			double length = ((double)rand.nextInt(10)) / 50 * imgWidth;
			int degree = rand.nextInt(360);
			int x2 = (int)(Math.cos(degree / Math.PI) * length + x1);
			int y2 = (int)(y1 - Math.sin(degree / Math.PI) * length);
			int R = rand.nextInt(256);
			int G = rand.nextInt(256);
			int B = rand.nextInt(256);
			g.setColor(new Color(R, G, B));
			g.drawLine(x1, y1, x2, y2);
		}
	}
}
