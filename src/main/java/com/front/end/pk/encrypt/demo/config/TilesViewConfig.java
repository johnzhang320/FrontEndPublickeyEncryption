package com.front.end.pk.encrypt.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/*
 * Following is the tiles configuration. You require to register view class as TilesView.class 
 * and also configure the location of tiles configuration file. In our case it is tiles.xml 
 * inside directory name ui.
 */
/*
 * Same as dispatcher-servlet.xml
 *  <bean id="tilesViewResolver" class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
	     <property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"/>       
    </bean>

    <bean id="tilesConfigurer" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
		<property name="definitions">		
			<list>			
				 <value>/WEB-INF/tiles-spring.xml</value>			
			</list>
	    </property>

	</bean>   
 */
@Configuration
public class TilesViewConfig {
	/*
	 * <bean id="tilesViewResolver" class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
	     <property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"/>       
    </bean>
	 */
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	/*
	 *  <bean id="tilesConfigurer" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
			<property name="definitions">
				<list>
					 <value>/WEB-INF/tiles-spring.xml</value>
				</list>
			</property>
		</bean>
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {

		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions("classpath:tiles.xml");
		return tiles;

	}

}
