package com.lixiaozhuo._02_structural._04_bridge;
/**
 * 未使用桥接模式
 */

//电脑
public interface Computer0 {
	void sale();
}
//电脑分类
class Desktop0 implements Computer0 {
	@Override
	public void sale() {
		System.out.println("销售台式机！");
	}
}

class Laptop0 implements Computer0 {
	@Override
	public void sale() {
		System.out.println("销售笔记本！");
	}
}
class Pad0 implements Computer0 {
	@Override
	public void sale() {
		System.out.println("销售平板电脑！");
	}
}

//不同品牌
class LenovoDesktop0 extends Desktop0 {
	@Override
	public void sale() {
		System.out.println("销售联想台式机");
	}
}
class LenovoLaptop0 extends Laptop0 {
	@Override
	public void sale() {
		System.out.println("销售联想笔记本");
	}
}
class LenovoPad0 extends Pad0 {
	@Override
	public void sale() {
		System.out.println("销售联想平板电脑");
	}
}



class ShenZhouDesktop0 extends Desktop0 {
	@Override
	public void sale() {
		System.out.println("销售神舟台式机");
	}
}
class ShenZhouLaptop0 extends Laptop0 {
	@Override
	public void sale() {
		System.out.println("销售神舟笔记本");
	}
}
class ShenZhouPad0 extends Pad0 {
	@Override
	public void sale() {
		System.out.println("销售神舟平板电脑");
	}
}


class DellDesktop0 extends Desktop0 {
	@Override
	public void sale() {
		System.out.println("销售戴尔台式机");
	}
}
class DellLaptop0 extends Laptop0 {
	@Override
	public void sale() {
		System.out.println("销售戴尔笔记本");
	}
}
class DellPad0 extends Pad0 {
	@Override
	public void sale() {
		System.out.println("销售戴尔平板电脑");
	}
}


