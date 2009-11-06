package cn.kejikeji.common;

import java.util.Properties;

/**
 * 通过此类读取application.properties的属性
 * @author liugang
 *
 */
public class ApplicationProperties {
	private static Properties prop=new Properties();
	
	/**
	 * 搜索引擎的二进制文件Path路径
	 */
	public static String SEARCH_INDEX_HOME="search.index.home";
	
	/**
	 * 提供下载的文件，如doc, wmv, mp3 等
	 */
	public static String UPLOAD_FILE_ROOT="upload.file.root";
	
	/**
	 * web下所使用的图片文件夹
	 */
	public static String UPLOAD_WEB_PATH="upload.web.path";
	
	/**
	 * smtp主机
	 */
	public static String MAIL_SMTP_HOST="mail.smtp.host";
	
	/**
	 * smtp用户名
	 */
	public static String MAIL_SMTP_USER="mail.smtp.user";
	/**
	 * smtp密码
	 */
	public static String MAIL_SMTP_PASSWD="mail.smtp.passwd";
	/**
	 * 发送人邮件地址
	 */
	public static String MAIL_SENDER_ADDR="mail.sender.addr";	
	
	/**
	 * 服务器地址，申思维增加
	 */
	public static String EDITOR_SERVER_IP="editor.server.ip";
	
	/**
	 * 服务器端口,申思维增加
	 */
	public static String EDITOR_SERVER_PORT="editor.server.port";
	
	/**
	 * 系统应用名,申思维增加
	 */
	public static String EDITOR_SERVER_CONTEXT="editor.server.context";
	
	
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		ApplicationProperties.prop = prop;
	}
	
	public static String getProproperty(String key){
		return prop.getProperty(key);
	}
	
	public static void setProperty(String key,String value){
		prop.setProperty(key, value);
	}
}
