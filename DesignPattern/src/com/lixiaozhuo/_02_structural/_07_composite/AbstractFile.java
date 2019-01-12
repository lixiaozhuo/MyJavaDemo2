package com.lixiaozhuo._02_structural._07_composite;

import java.util.ArrayList;
import java.util.List;

//组合模式案例

//抽象组件
public interface AbstractFile {
	void killVirus();  //杀毒
}

//叶子组件
interface File extends AbstractFile {
}

//容器组件
interface Folder extends AbstractFile {
    void add(AbstractFile c);
    void remove(AbstractFile c);
    AbstractFile getChild(int index);
}



//叶子组件
class ImageFile implements File {
	private String name;
	
	public ImageFile(String name) {
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("---图像文件："+name+",进行查杀！");
	}
	
}

//叶子组件
class TextFile implements File {
	private String name;
	
	public TextFile(String name) {
		this.name = name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("---文本文件："+name+",进行查杀！");
	}
}

//叶子组件
class VideoFile implements File {
	private String name;
	
	public VideoFile(String name) {
		this.name = name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("---视频文件："+name+",进行查杀！");
	}
}


//容器组件
class CustomFolder implements Folder {
	private String name;
	//定义容器，用来存放本容器构建下的子节点
	private List<AbstractFile> list = new ArrayList<>();
	
	public CustomFolder(String name) {
		this.name = name;
	}

	public void add(AbstractFile file){
		list.add(file);
	}
	public void remove(AbstractFile file){
		list.remove(file);
	}
	public AbstractFile getChild(int index){
		return list.get(index);
	}

	@Override
	public void killVirus() {
		System.out.println("---文件夹："+name+",进行查杀");
		for (AbstractFile file : list) {
			file.killVirus();
		}
	}
	
}

