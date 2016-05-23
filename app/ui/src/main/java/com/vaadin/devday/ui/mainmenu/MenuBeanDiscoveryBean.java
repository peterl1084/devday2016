package com.vaadin.devday.ui.mainmenu;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.literal.AnyLiteral;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.devday.ui.MainMenu;
import com.vaadin.devday.ui.MenuItem;
import com.vaadin.devday.ui.UIInitializedEvent;
import com.vaadin.navigator.View;

@NormalUIScoped
public class MenuBeanDiscoveryBean {

	@Inject
	private BeanManager beanManager;

	@Inject
	private Instance<MainMenu> mainMenuLookup;

	protected void doMenuItemLookup(@Observes UIInitializedEvent event) {
		if (mainMenuLookup.isAmbiguous()) {
			return;
		}

		if (mainMenuLookup.isUnsatisfied()) {
			return;
		}

		MainMenu mainMenu = mainMenuLookup.get();
		Set<Bean<?>> beans = beanManager.getBeans(View.class, new AnyLiteral());

		List<Bean<?>> menuItemBeans = beans.stream()
				.filter(bean -> bean.getBeanClass().isAnnotationPresent(MenuItem.class)).sorted(new BeanNameComparer())
				.collect(Collectors.toList());

		menuItemBeans.forEach(menuItemBean -> {
			MenuItem menuItemAnnotation = menuItemBean.getBeanClass().getAnnotation(MenuItem.class);
			CDIView viewAnnotation = menuItemBean.getBeanClass().getAnnotation(CDIView.class);

			mainMenu.addMenuItem(menuItemAnnotation.name(), menuItemAnnotation.icon(), viewAnnotation.value());
		});
	}

	private static class BeanNameComparer implements Comparator<Bean<?>> {

		@Override
		public int compare(Bean<?> a, Bean<?> b) {
			MenuItem aAnnotation = a.getBeanClass().getAnnotation(MenuItem.class);
			MenuItem bAnnotation = b.getBeanClass().getAnnotation(MenuItem.class);

			return aAnnotation.order() - bAnnotation.order();
		}
	}
}
