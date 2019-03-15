package kr.co.sist.sc.admin.util;

import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.sist.sc.admin.view.SCAMainView;

public class SCAClock extends Thread {
	private SCAMainView scamv;
	private Graphics g;
	
	private static final int MAX_SECOND = 60;
	private static final int MAX_MINUTE = 60;
	private static final int MAX_HOUR = 12;
	
	private int x, y;
	
	public SCAClock(SCAMainView scamv, Graphics g) {
		this.scamv = scamv;
		this.g = g;
		
		this.x = 875;
		this.y = 140;
		
		startThread();
		
	} // SCAClock
	
	public void paintClock() {
		String realTime = getTime();
		
		String nowTime = realTime.split(" ")[1];
		
		int hour = Integer.parseInt(nowTime.split(":")[0]);
		int min = Integer.parseInt(nowTime.split(":")[1]);
		int sec = Integer.parseInt(nowTime.split(":")[2]);
		
		if (sec == MAX_SECOND) {
			sec = 0;
			min++;
		} // end if
		
		if (min == MAX_MINUTE) {
			min = 0;
			hour++;
		} // end if
		
		if (min == MAX_MINUTE && hour == MAX_HOUR) {
			hour = 0;
		} // end if
		
		drawClock();
		
		draw(x, y, x, (y - 100), 65, (sec * 6));
		draw(x, y, x, (y - 100), 50, (min * 6));
		draw(x, y, x, (y - 100), 35, ((hour * 30) + (min / 2)));
	} // paintClock
	
	private void drawClock() {
		int dot = 1;
		
		for (int i = 1; i < MAX_MINUTE + 1; i++) {
			if (i % 5 == 0) {
				drawTime(x, y, (i * 6), dot + "");
				dot++;
			} // end if
		} // end for
	} // drawClock
	
	private void drawTime(int x, int y, int angle, String i) {
		x = (this.x - 2) + (int) (70 * Math.sin(angle * Math.PI / 180));
		y = (this.y + 6) - (int) (70 * Math.cos(angle * Math.PI / 180));
		
		g.drawString(i, x, y);
	} // drawTime
	
	private void draw(int ox, int oy, int x, int y, int l, int angle) {
		x = ox + (int) (l * Math.sin(angle * Math.PI / 180));
		y = oy - (int) (l * Math.cos(angle * Math.PI / 180));
		
		g.drawLine(ox, oy, x, y);
	} // draw
	
	private void startThread() {
		Thread t = new Thread(this);
		t.start();
	} // startThread
	
	@Override
	public void run() {
		try {
			while (true) {
				scamv.repaint();
				
				Thread.sleep(1000);
			} // end while
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} // end catch
	} // run
	
	private String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(new Date());
	} // getTime
	
} // class
