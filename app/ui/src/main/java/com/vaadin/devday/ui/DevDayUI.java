package com.vaadin.devday.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.cdi.CDIUI;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@CDIUI("")
@Theme("devday")
@Viewport("width=device-width, initial-scale=1")
@Push
// @Widgetset("com.vaadin.devday.ui.DevDayWidgetset")
public class DevDayUI extends UI {
	private static final long serialVersionUID = -6541105928485822011L;

	@Inject
	private ViewDisplay viewDisplay;

	@Inject
	private javax.enterprise.event.Event<UIInitializedEvent> event;

	private int uiId;

	private String embedId;

	@Override
	protected void init(VaadinRequest request) {
		addStyleName(ValoTheme.UI_WITH_MENU);
		Responsive.makeResponsive(this);

		setContent((Component) viewDisplay);

		event.fire(new UIInitializedEvent());
	}

	// @WebServlet(value = "/*", asyncSupported = true)
	// @VaadinServletConfiguration(productionMode = false, ui = DevDayUI.class)
	// public static class Servlet extends VaadinCDIServlet {
	// private static final long serialVersionUID = 7162195880902284938L;
	//
	// @Override
	// protected void writeStaticResourceResponse(HttpServletRequest request,
	// HttpServletResponse response,
	// URL resourceUrl) throws IOException {
	// URLConnection connection = null;
	// InputStream is = null;
	// String urlStr = resourceUrl.toExternalForm();
	//
	// if (is == null) {
	// // precompressed resource not available, get non compressed
	// connection = resourceUrl.openConnection();
	// try {
	// is = connection.getInputStream();
	// } catch (FileNotFoundException e) {
	// response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	// return;
	// }
	// }
	//
	// try {
	// int length = connection.getContentLength();
	// if (length >= 0) {
	// response.setContentLength(length);
	// }
	// } catch (Throwable e) {
	// // This can be ignored, content length header is not required.
	// // Need to close the input stream because of
	// // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4257700 to
	// // prevent it from hanging, but that is done below.
	// }
	//
	// try {
	// streamContent(response, is);
	// } finally {
	// is.close();
	// }
	// }
	//
	// private void streamContent(HttpServletResponse response, InputStream is)
	// throws IOException {
	// final OutputStream os = response.getOutputStream();
	// final byte buffer[] = new byte[DEFAULT_BUFFER_SIZE];
	// int bytes;
	// while ((bytes = is.read(buffer)) >= 0) {
	// os.write(buffer, 0, bytes);
	// }
	// }
	// }

}
