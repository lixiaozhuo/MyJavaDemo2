package com.lixiaozhuo._03_behavioral._06_command;

/**
 * 命令接口
 */
public interface Command {
	/**
	 * 这个方法是一个返回结果为空的方法。
	 * 实际项目中，可以根据需求设计多个不同的方法
	 */
	void execute();
}

/**
 * 命令对象
 */
class ConcreteCommand implements Command {
    //命令的真正的执行者
	private Receiver receiver;
	
	public ConcreteCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		//命令真正执行前或后，执行相关的处理！
		receiver.action();
	}
	
}