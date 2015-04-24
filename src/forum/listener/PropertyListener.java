package forum.listener;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 容器加载监听器，初始化本地配置文件
 * @author skywalker
 *
 */
public class PropertyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream("/config.properties"));
			//迭代所有property添加到application范围
			//此处默认属性是int
			String name = null;
			Enumeration<?> propertyNames = properties.propertyNames();
			while(propertyNames.hasMoreElements()) {
				name = (String) propertyNames.nextElement();
				context.setAttribute(name, properties.getProperty(name));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
