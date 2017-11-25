/**
 * 
 */
package com.iesports.test.game.pushbox;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 描述：用户显示得主窗体
 * @author xiongdun
 * @created 2016年11月19日 下午1:01:14
 * @since 
 */
public class MainFrame extends Frame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String imagePath = "D:\\develop\\workspace\\iesports\\WebContent\\images\\game\\pushbox\\";
	
	public MainFrame() {
		// 做笼子（目的地）
		this.setLongziAttribute();
		// 做人物
		this.initPersonAttribute();
		// 做箱子
		this.initBoxAttribute(3);
		// 做障碍
		this.initobstacleAttribute();
		// 做背景
		this.setFrameBackground();
		// 做窗体
		this.setFrameAttribute();
		// 使窗口监督用户是不是点击了键盘
		this.addKeyListener(this);
	}
	
	// 为窗口添加一个可以监听用户有键盘事件的监听器
	@Override
	public void keyTyped(KeyEvent e) {
		// 如何分辨用户点击的使键盘上的某个按键
		// 通过键码值获得点击的按键
		// w W上 aA 左 dD 右 s S下
		// 人物移动后进行相应图片的变换
		char keyChar = e.getKeyChar();
		// shang
		if (keyChar == 'w' || keyChar == 'W') {
			
			// 让人物向右移动
			// 一定要知道移动的位置前面有没有障碍物
			// 如果没有障碍移动，如果有障碍物，什么事情都不做
			if (datas[wolfY - 1][wolfX] == 0) {
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x, y - 50);
				icon = new ImageIcon(imagePath + "wolf_bm.png");
				lbl_wolf.setIcon(icon);
				wolfY -= 1;
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 1) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 1) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 4) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 12) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 12 && datas[wolfY - 2][wolfX] == 1) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 12 && datas[wolfY - 2][wolfX] == 4) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 12 && datas[wolfY - 2][wolfX] == 12) {
				return;
			}
			
			if (datas[wolfY - 1][wolfX] == 8) {
				
				// 如果遇到了箱子，人物在方向上移动一格，
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x, y - 50);
				icon = new ImageIcon(imagePath + "wolf_bm.png");
				lbl_wolf.setIcon(icon);
				wolfY -= 1;
				
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 0) {
				//箱子在方向上也移动一格，箱子的实际数据4的位置需要发生变化0，箱子的显示位置也要发生变化4
				datas[wolfY - 1][wolfX] = 0;
				datas[wolfY - 2][wolfX] = 4;
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 0) {
				datas[wolfY - 1][wolfX] = 0;
				datas[wolfY - 2][wolfX] = 12;
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 0) {
				datas[wolfY - 1][wolfX] = 8;
				datas[wolfY - 2][wolfX] = 4;
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 0) {
				datas[wolfY - 1][wolfX] = 8;
				datas[wolfY - 2][wolfX] = 12;
			}
			
			sheeps[wolfY - 1][wolfX].setLocation(10 + wolfX * 50, 33 + wolfY * 50 - 100);
			sheeps[wolfY - 2][wolfX] = sheeps[wolfY - 1][wolfX];
			sheeps[wolfY - 1][wolfX] = null;
			
			wolfY = wolfY - 1;
			int x = (int) lbl_wolf.getLocation().getX();
			int y = (int) lbl_wolf.getLocation().getY();
			lbl_wolf.setLocation(x, y - 50);
			icon = new ImageIcon(imagePath + "wolf_bm.png");
			lbl_wolf.setIcon(icon);
			victory();
			return;
		}
		
		/// xia
		if (keyChar == 's' || keyChar == 'S') {
			
			if (datas[wolfY + 1][wolfX] == 0) {
				
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x, y + 50);
				icon = new ImageIcon(imagePath + "wolf_zm.png");
				lbl_wolf.setIcon(icon);
				wolfY += 1;
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 1) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 1) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 4) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 12) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 12 && datas[wolfY + 2][wolfX] == 1) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 12 && datas[wolfY + 2][wolfX] == 4) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 12 && datas[wolfY + 2][wolfX] == 12) {
				return;
			}
			
			if (datas[wolfY + 1][wolfX] == 8) {
				
				// 如果遇到了箱子，人物在方向上移动一格，
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x, y + 50);
				icon = new ImageIcon(imagePath + "wolf_zm.png");
				lbl_wolf.setIcon(icon);
				wolfY += 1;
				
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 0) {
				//箱子在方向上也移动一格，箱子的实际数据4的位置需要发生变化0，箱子的显示位置也要发生变化4
				datas[wolfY + 1][wolfX] = 0;
				datas[wolfY + 2][wolfX] = 4;
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 0) {
				datas[wolfY + 1][wolfX] = 0;
				datas[wolfY - 2][wolfX] = 12;
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 0) {
				datas[wolfY + 1][wolfX] = 8;
				datas[wolfY + 2][wolfX] = 4;
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 0) {
				datas[wolfY + 1][wolfX] = 8;
				datas[wolfY + 2][wolfX] = 12;
			}
			
			sheeps[wolfY + 1][wolfX].setLocation(10 + wolfX * 50, 33 + wolfY * 50 + 100);
			sheeps[wolfY + 2][wolfX] = sheeps[wolfY + 1][wolfX];
			sheeps[wolfY + 1][wolfX] = null;
			
			wolfY = wolfY + 1;
			int x = (int) lbl_wolf.getLocation().getX();
			int y = (int) lbl_wolf.getLocation().getY();
			lbl_wolf.setLocation(x, y + 50);
			icon = new ImageIcon(imagePath + "wolf_zm.png");
			lbl_wolf.setIcon(icon);
			victory();
			return;
			
		}
		if (keyChar == 'a' || keyChar == 'A') {
			
			if (datas[wolfY][wolfX - 1] == 0) {
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x - 50, y);
				icon = new ImageIcon(imagePath + "wolf_zuom.png");
				lbl_wolf.setIcon(icon);
				wolfX -= 1;
				return;
			}
			
			// 碰撞检测
			if (datas[wolfY][wolfX - 1] == 1) {
				return;
			}
			
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 1) {
				return;
			}
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 4) {
				return;
			}
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 12) {
				return;
			}
			if (datas[wolfY][wolfX - 1] == 12 && datas[wolfY][wolfX - 2] == 1) {
				return;
			}
			if (datas[wolfY][wolfX - 1] == 12 && datas[wolfY][wolfX - 2] == 4) {
				return;
			}
			if (datas[wolfY][wolfX - 1] == 12 && datas[wolfY][wolfX - 2] == 12) {
				return;
			}
			
			if (datas[wolfY][wolfX - 1] == 8) {
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x - 50, y);
				icon = new ImageIcon(imagePath + "wolf_zuom.png");
				lbl_wolf.setIcon(icon);
				wolfX -= 1;
				return;
			}
			
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 0) {
				datas[wolfY][wolfX - 1] = 0;
				datas[wolfY][wolfX - 2] = 4;
			}
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 8) {
				datas[wolfY][wolfX - 1] = 0;
				datas[wolfY][wolfX - 2] = 12;
			}
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 0) {
				datas[wolfY][wolfX - 1] = 8;
				datas[wolfY][wolfX - 2] = 4;
			}
			if (datas[wolfY][wolfX - 1] == 4 && datas[wolfY][wolfX - 2] == 8) {
				datas[wolfY][wolfX - 1] = 8;
				datas[wolfY][wolfX - 2] = 12;
			}
			
			sheeps[wolfY][wolfX - 1].setLocation(10 + wolfX * 50 - 100, wolfY * 50 + 33);
			sheeps[wolfY][wolfX - 2] = sheeps[wolfY][wolfX - 1];
			sheeps[wolfY][wolfX - 1] = null;
			int x = (int) lbl_wolf.getLocation().getX();
			int y = (int) lbl_wolf.getLocation().getY();
			lbl_wolf.setLocation(x - 50, y);
			icon = new ImageIcon(imagePath + "wolf_zuom.png");
			lbl_wolf.setIcon(icon);
			wolfX -= 1;
			victory();
			return;
		}
		if (keyChar == 'd' || keyChar == 'D') {
			
			if (datas[wolfY][wolfX + 1] == 0) {
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x + 50, y);
				icon = new ImageIcon(imagePath + "wolf_ym.png");
				lbl_wolf.setIcon(icon);
				wolfX += 1;
				return;
			}
			
			// 碰撞检测
			if (datas[wolfY][wolfX + 1] == 1) {
				return;
			}
			
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 1) {
				return;
			}
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 4) {
				return;
			}
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 12) {
				return;
			}
			if (datas[wolfY][wolfX + 1] == 12 && datas[wolfY][wolfX + 2] == 1) {
				return;
			}
			if (datas[wolfY][wolfX + 1] == 12 && datas[wolfY][wolfX + 2] == 4) {
				return;
			}
			if (datas[wolfY][wolfX + 1] == 12 && datas[wolfY][wolfX + 2] == 12) {
				return;
			}
			
			if (datas[wolfY][wolfX + 1] == 8) {
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x + 50, y);
				icon = new ImageIcon(imagePath + "wolf_ym.png");
				lbl_wolf.setIcon(icon);
				wolfX += 1;
				return;
			}
			
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 0) {
				datas[wolfY][wolfX + 1] = 0;
				datas[wolfY][wolfX + 2] = 4;
			}
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 8) {
				datas[wolfY][wolfX + 1] = 0;
				datas[wolfY][wolfX + 2] = 12;
			}
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 0) {
				datas[wolfY][wolfX + 1] = 8;
				datas[wolfY][wolfX + 2] = 4;
			}
			if (datas[wolfY][wolfX + 1] == 4 && datas[wolfY][wolfX + 2] == 8) {
				datas[wolfY][wolfX + 1] = 8;
				datas[wolfY][wolfX + 2] = 12;
			}
			
			sheeps[wolfY][wolfX + 1].setLocation(10 + wolfX * 50 + 100, wolfY * 50 + 33);
			sheeps[wolfY][wolfX + 2] = sheeps[wolfY][wolfX + 1];
			sheeps[wolfY][wolfX + 1] = null;
			int x = (int) lbl_wolf.getLocation().getX();
			int y = (int) lbl_wolf.getLocation().getY();
			lbl_wolf.setLocation(x + 50, y);
			icon = new ImageIcon(imagePath + "wolf_ym.png");
			lbl_wolf.setIcon(icon);
			wolfX += 1;
			victory();
			return;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	Icon icon;
	JLabel lbl_wolf;
	JLabel lbl_sheep1;
	JLabel lbl_sheep2;
	JLabel lbl_sheep3;
	
	int num = 0;
	int total = 3;
	
	private void victory() {
		if (num == total) {
			System.out.println("胜利！");
			//下一关需要，需要重新初始化
		}
	}
	
	JLabel[][] sheeps = new JLabel[12][16];
	
	JLabel lbl_obst;
	int wolfX = 0;
	int wolfY = 0;
	int sheepX = 0;
	int sheepY = 0;
	// 初始化人物
	private void initPersonAttribute() {
		wolfX = 3;
		wolfY = 3;
		// 用图片来模拟人物
		icon = new ImageIcon(imagePath + "wolf_zm.png");
		lbl_wolf = new JLabel(icon);
		lbl_wolf.setBounds(10 + 50 * wolfX, 33 + 50 * wolfY, 50, 50);
		this.add(lbl_wolf);
	}
	// 初始化箱子
	private void initBoxAttribute(int sheepNum) {
		icon = new ImageIcon(imagePath + "sheep_ku.png");
		lbl_sheep1 = new JLabel(icon);
		lbl_sheep1.setBounds(350 + 10, 150 + 33 + 0, 50, 50);
		datas[3][7] = 4;
		sheeps[3][7] = lbl_sheep1;
		this.add(lbl_sheep1);
		
		lbl_sheep2 = new JLabel(icon);
		lbl_sheep2.setBounds(350 + 10, 150 + 33 + 100, 50, 50);
		datas[5][7] = 4;
		sheeps[5][7] = lbl_sheep2;
		this.add(lbl_sheep2);
		
		lbl_sheep3 = new JLabel(icon);
		lbl_sheep3.setBounds(350 + 10, 150 + 33 + 200, 50, 50);
		datas[7][7] = 4;
		sheeps[7][7] = lbl_sheep3;
		this.add(lbl_sheep3);
		
	}
	// 1为障碍物，0为无障碍
	int[][] datas = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
	};
	// 初始化障碍物
	private void initobstacleAttribute() {
		icon = new ImageIcon(imagePath + "za.png");
		
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[i].length; j++) {
				if (datas[i][j] == 1) {
					lbl_obst = new JLabel(icon);
					lbl_obst.setBounds(10 + 50 * j, 33 + 50 * i, 50, 50);
					this.add(lbl_obst);
				}
			}
		}
		
	}
	// 制作目的地笼子
	private void setLongziAttribute() {
		icon = new ImageIcon(imagePath + "lz.png");
		int up = 150;
		for (int i = 0; i < 3; i++) {
			lbl_obst = new JLabel(icon);
			lbl_obst.setBounds(10 + 14 * 50, 33 + up, 50, 50);
			up += 50;
			this.add(lbl_obst);
		}
	}
	
	/**
	 * 描述：设置窗体属性
	 * @author xiongdun
	 * @created 2016年11月20日 上午10:20:14
	 * @since
	 */
	private void setFrameAttribute() {
		this.setResizable(false);
		// 设置窗体的布局格式为自由布局
		this.setLayout(null);
		// 设置是否显示窗口
		this.setVisible(true);
		// 设置窗口尺寸，长和宽
		this.setSize(800, 600);
		// 设置窗口的位置
		this.setLocation(300, 100);
		// 设置标题
		this.setTitle("推箱子");
	}
	
	// 背景初始化
	private void setFrameBackground() {
		// 背景是如何实现的，使用JLabel组件
		
		// 创建一个图片对象
		icon = new ImageIcon(imagePath + "floor.png");
		// 制作背景
		JLabel lbl_bg = new JLabel(icon);
		lbl_bg.setBounds(10, 33, 780, 557);
		// 将组件添加进窗体,因为本类继承的就是一个Frame所以本对象就是一个窗体
		this.add(lbl_bg);
	}
	
	
}
