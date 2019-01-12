package com.lixiaozhuo._02_structural._07_composite;

/**
 * 组合模式测试
 */
public class Client {
	public static void main(String[] args) {
		AbstractFile f2,f3,f4,f5;
		Folder f11 = new CustomFolder("我的收藏");
		f2 = new ImageFile("老高的大头像.jpg");
		f3 = new TextFile("Hello.txt");
		f11.add(f2);
        f11.add(f3);
		///////////
		Folder f12 = new CustomFolder("电影");
		f4 = new VideoFile("笑傲江湖.avi");
		f5 = new VideoFile("神雕侠侣.avi");
		f12.add(f4);
		f12.add(f5);
		///////////
		f11.add(f12);
		//扫描病毒
		f11.killVirus();
		
		
	}
}
