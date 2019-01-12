package com.lixiaozhuo._02_structural._07_composite;

//组合模式结构

//抽象组件
public interface Component {
	void operation();
}

//叶子组件
interface Leaf extends Component {

}

//容器组件
interface Composite extends Component {
	void add(Component c);
	void remove(Component c);
	Component getChild(int index);
}