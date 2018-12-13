package yogi.paging;

import java.io.Serializable;

import yogi.paging.command.PagingActionCommand;

public class TableActionConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -385955755608420141L;
	private String text;
	private String commandName;
	private String viewName;
	private String iconCls;
	private int shortcutKeyCode;
	private boolean ctrlPressed;
	private boolean shiftPressed;
	private boolean altPressed;
	private String serverType;
	
	public TableActionConfig(String name, Class<? extends PagingActionCommand<?>> commandClass, String viewName, String iconCls, int shortcutKeyCode, boolean ctrlPressed, boolean shiftPressed, boolean altPressed) {
		super();
		this.text = name;
		if(commandClass!=null) this.commandName = commandClass.getName();
		this.viewName = viewName;
		this.iconCls = iconCls;
		this.shortcutKeyCode = shortcutKeyCode;
		this.ctrlPressed = ctrlPressed;
		this.shiftPressed = shiftPressed;
		this.altPressed = altPressed;
	}
	
	public TableActionConfig(String name, Class<? extends PagingActionCommand<?>> commandClass, String viewName, String iconCls, int shortcutKeyCode) {
		super();
		this.text = name;
		if(commandClass!=null) this.commandName = commandClass.getName();
		this.viewName = viewName;
		this.iconCls = iconCls;
		this.shortcutKeyCode = shortcutKeyCode;
		this.ctrlPressed = true;
		this.shiftPressed = false;
		this.altPressed = false;
	}
	
	
	public TableActionConfig(String name, Class<? extends PagingActionCommand<?>> commandClass, String viewName, String iconCls, String serverType) {
		this(name, commandClass, viewName, iconCls, -1);
		this.serverType = serverType;
	}
	
	public TableActionConfig(String name, Class<? extends PagingActionCommand<?>> commandClass, String viewName, String iconCls) {
		this(name, commandClass, viewName, iconCls, -1);
	}
	
	public String getCommandName() {
		return commandName;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public String getText() {
		return text;
	}
	
	public String getIconCls() {
		return iconCls;
	}
	
	public int getShortcutKeyCode() {
		return shortcutKeyCode;
	}

	public String getServerType() {
		return serverType;
	}
	
	public boolean isCtrlPressed() {
		return ctrlPressed;
	}
	
	public boolean isShiftPressed() {
		return shiftPressed;
	}

	public boolean isAltPressed() {
		return altPressed;
	}
	
}