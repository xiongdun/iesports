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
 * �������û���ʾ��������
 * @author xiongdun
 * @created 2016��11��19�� ����1:01:14
 * @since 
 */
public class MainFrame extends Frame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String imagePath = "D:\\develop\\workspace\\iesports\\WebContent\\images\\game\\pushbox\\";
	
	public MainFrame() {
		// �����ӣ�Ŀ�ĵأ�
		this.setLongziAttribute();
		// ������
		this.initPersonAttribute();
		// ������
		this.initBoxAttribute(3);
		// ���ϰ�
		this.initobstacleAttribute();
		// ������
		this.setFrameBackground();
		// ������
		this.setFrameAttribute();
		// ʹ���ڼල�û��ǲ��ǵ���˼���
		this.addKeyListener(this);
	}
	
	// Ϊ�������һ�����Լ����û��м����¼��ļ�����
	@Override
	public void keyTyped(KeyEvent e) {
		// ��ηֱ��û������ʹ�����ϵ�ĳ������
		// ͨ������ֵ��õ���İ���
		// w W�� aA �� dD �� s S��
		// �����ƶ��������ӦͼƬ�ı任
		char keyChar = e.getKeyChar();
		// shang
		if (keyChar == 'w' || keyChar == 'W') {
			
			// �����������ƶ�
			// һ��Ҫ֪���ƶ���λ��ǰ����û���ϰ���
			// ���û���ϰ��ƶ���������ϰ��ʲô���鶼����
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
				
				// ������������ӣ������ڷ������ƶ�һ��
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x, y - 50);
				icon = new ImageIcon(imagePath + "wolf_bm.png");
				lbl_wolf.setIcon(icon);
				wolfY -= 1;
				
			}
			
			if (datas[wolfY - 1][wolfX] == 4 && datas[wolfY - 2][wolfX] == 0) {
				//�����ڷ�����Ҳ�ƶ�һ�����ӵ�ʵ������4��λ����Ҫ�����仯0�����ӵ���ʾλ��ҲҪ�����仯4
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
				
				// ������������ӣ������ڷ������ƶ�һ��
				int x = (int) lbl_wolf.getLocation().getX();
				int y = (int) lbl_wolf.getLocation().getY();
				lbl_wolf.setLocation(x, y + 50);
				icon = new ImageIcon(imagePath + "wolf_zm.png");
				lbl_wolf.setIcon(icon);
				wolfY += 1;
				
			}
			
			if (datas[wolfY + 1][wolfX] == 4 && datas[wolfY + 2][wolfX] == 0) {
				//�����ڷ�����Ҳ�ƶ�һ�����ӵ�ʵ������4��λ����Ҫ�����仯0�����ӵ���ʾλ��ҲҪ�����仯4
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
			
			// ��ײ���
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
			
			// ��ײ���
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
			System.out.println("ʤ����");
			//��һ����Ҫ����Ҫ���³�ʼ��
		}
	}
	
	JLabel[][] sheeps = new JLabel[12][16];
	
	JLabel lbl_obst;
	int wolfX = 0;
	int wolfY = 0;
	int sheepX = 0;
	int sheepY = 0;
	// ��ʼ������
	private void initPersonAttribute() {
		wolfX = 3;
		wolfY = 3;
		// ��ͼƬ��ģ������
		icon = new ImageIcon(imagePath + "wolf_zm.png");
		lbl_wolf = new JLabel(icon);
		lbl_wolf.setBounds(10 + 50 * wolfX, 33 + 50 * wolfY, 50, 50);
		this.add(lbl_wolf);
	}
	// ��ʼ������
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
	// 1Ϊ�ϰ��0Ϊ���ϰ�
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
	// ��ʼ���ϰ���
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
	// ����Ŀ�ĵ�����
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
	 * ���������ô�������
	 * @author xiongdun
	 * @created 2016��11��20�� ����10:20:14
	 * @since
	 */
	private void setFrameAttribute() {
		this.setResizable(false);
		// ���ô���Ĳ��ָ�ʽΪ���ɲ���
		this.setLayout(null);
		// �����Ƿ���ʾ����
		this.setVisible(true);
		// ���ô��ڳߴ磬���Ϳ�
		this.setSize(800, 600);
		// ���ô��ڵ�λ��
		this.setLocation(300, 100);
		// ���ñ���
		this.setTitle("������");
	}
	
	// ������ʼ��
	private void setFrameBackground() {
		// ���������ʵ�ֵģ�ʹ��JLabel���
		
		// ����һ��ͼƬ����
		icon = new ImageIcon(imagePath + "floor.png");
		// ��������
		JLabel lbl_bg = new JLabel(icon);
		lbl_bg.setBounds(10, 33, 780, 557);
		// �������ӽ�����,��Ϊ����̳еľ���һ��Frame���Ա��������һ������
		this.add(lbl_bg);
	}
	
	
}
