/*
 * Copyright (c) 2008, Harald Kuhr
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.twelvemonkeys.servlet;

import com.twelvemonkeys.util.MapAbstractTest;
import jakarta.servlet.descriptor.JspConfigDescriptor;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import jakarta.servlet.*;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * ServletConfigMapAdapterTestCase
 * <p/>
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @version $Id: //depot/branches/personal/haraldk/twelvemonkeys/release-2/twelvemonkeys-servlet/src/test/java/com/twelvemonkeys/servlet/ServletConfigMapAdapterTestCase.java#3 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AbstractServletConfigMapAdapterTest.ServletConfigMapTest.class, AbstractServletConfigMapAdapterTest.FilterConfigMapTest.class, AbstractServletConfigMapAdapterTest.ServletContextMapTest.class})
public final class ServletConfigMapAdapterTest {
}

abstract class AbstractServletConfigMapAdapterTest extends MapAbstractTest {

    public boolean isPutAddSupported() {
        return false;
    }

    public boolean isPutChangeSupported() {
        return false;
    }

    public boolean isRemoveSupported() {
        return false;
    }

    public boolean isSetValueSupported() {
        return false;
    }

    private static class TestConfig implements ServletConfig, FilterConfig, ServletContext, Serializable, Cloneable {
        Map map = new HashMap();

        public String getServletName() {
            return "dummy"; // Not needed for this test
        }

        public String getFilterName() {
            return getServletName();
        }

        public String getServletContextName() {
            return getServletName();
        }


        public ServletContext getServletContext() {
            throw new UnsupportedOperationException("Method getSerlvetContext not implemented");
        }

        @Override
        public String getContextPath() {
            throw new UnsupportedOperationException("Method getContextPath not implemented");
        }

        @Override
        public int getEffectiveMajorVersion() {
            throw new UnsupportedOperationException("Method getEffectiveMajorVersion not implemented");
        }

        @Override
        public int getEffectiveMinorVersion() {
            throw new UnsupportedOperationException("Method getEffectiveMinorVersion not implemented");
        }

        @Override
        public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
            throw new UnsupportedOperationException("Method addServlet not implemented");
        }

        @Override
        public boolean setInitParameter(String name, String value) {
            throw new UnsupportedOperationException("Method setInitParameter not implemented");
        }

        public String getInitParameter(String s) {
            return (String) map.get(s);
        }

        public Enumeration getInitParameterNames() {
            //noinspection unchecked
            return Collections.enumeration(map.keySet());
        }

        public ServletContext getContext(String uripath) {
            throw new UnsupportedOperationException("Method getContext not implemented");
        }

        public int getMajorVersion() {
            throw new UnsupportedOperationException("Method getMajorVersion not implemented");
        }

        public int getMinorVersion() {
            throw new UnsupportedOperationException("Method getMinorVersion not implemented");
        }

        public String getMimeType(String file) {
            throw new UnsupportedOperationException("Method getMimeType not implemented");
        }

        public Set getResourcePaths(String path) {
            throw new UnsupportedOperationException("Method getResourcePaths not implemented");
        }

        public URL getResource(String path) throws MalformedURLException {
            throw new UnsupportedOperationException("Method getResource not implemented");
        }

        public InputStream getResourceAsStream(String path) {
            throw new UnsupportedOperationException("Method getResourceAsStream not implemented");
        }

        public RequestDispatcher getRequestDispatcher(String path) {
            throw new UnsupportedOperationException("Method getRequestDispatcher not implemented");
        }

        public RequestDispatcher getNamedDispatcher(String name) {
            throw new UnsupportedOperationException("Method getNamedDispatcher not implemented");
        }

        public Servlet getServlet(String name) throws ServletException {
            throw new UnsupportedOperationException("Method getServlet not implemented");
        }

        public Enumeration getServlets() {
            throw new UnsupportedOperationException("Method getServlets not implemented");
        }

        public Enumeration getServletNames() {
            throw new UnsupportedOperationException("Method getServletNames not implemented");
        }

        public void log(String msg) {
            throw new UnsupportedOperationException("Method log not implemented");
        }

        public void log(Exception exception, String msg) {
            throw new UnsupportedOperationException("Method log not implemented");
        }

        public void log(String message, Throwable throwable) {
            throw new UnsupportedOperationException("Method log not implemented");
        }

        public String getRealPath(String path) {
            throw new UnsupportedOperationException("Method getRealPath not implemented");
        }

        public String getServerInfo() {
            throw new UnsupportedOperationException("Method getServerInfo not implemented");
        }

        public Object getAttribute(String name) {
            throw new UnsupportedOperationException("Method getAttribute not implemented");
        }

        public Enumeration getAttributeNames() {
            throw new UnsupportedOperationException("Method getAttributeNames not implemented");
        }

        public void setAttribute(String name, Object object) {
            throw new UnsupportedOperationException("Method setAttribute not implemented");
        }

        public void removeAttribute(String name) {
            throw new UnsupportedOperationException("Method removeAttribute not implemented");
        }

        @Override
        public ServletRegistration.Dynamic addServlet(String servletName, String className) {
            throw new UnsupportedOperationException("Method addServlet not implemented");
        }

        @Override
        public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
            throw new UnsupportedOperationException("Method addServlet not implemented");
        }

        @Override
        public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
            throw new UnsupportedOperationException("Method addJspFile not implemented");
        }

        @Override
        public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
            throw new UnsupportedOperationException("Method createServlet not implemented");
        }

        @Override
        public ServletRegistration getServletRegistration(String servletName) {
            throw new UnsupportedOperationException("Method getServletRegistration not implemented");
        }

        @Override
        public Map<String, ? extends ServletRegistration> getServletRegistrations() {
            throw new UnsupportedOperationException("Method getServletRegistrations not implemented");
        }

        @Override
        public FilterRegistration.Dynamic addFilter(String filterName, String className) {
            throw new UnsupportedOperationException("Method addFilter not implemented");
        }

        @Override
        public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
            throw new UnsupportedOperationException("Method addFilter not implemented");
        }

        @Override
        public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
            throw new UnsupportedOperationException("Method addFilter not implemented");
        }

        @Override
        public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException {
            throw new UnsupportedOperationException("Method createFilter not implemented");
        }

        @Override
        public FilterRegistration getFilterRegistration(String filterName) {
            throw new UnsupportedOperationException("Method getFilterRegistration not implemented");
        }

        @Override
        public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
            throw new UnsupportedOperationException("Method getFilterRegistrations not implemented");
        }

        @Override
        public SessionCookieConfig getSessionCookieConfig() {
            throw new UnsupportedOperationException("Method getSessionCookieConfig not implemented");
        }

        @Override
        public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
            throw new UnsupportedOperationException("Method setSessionTrackingModes not implemented");
        }

        @Override
        public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
            throw new UnsupportedOperationException("Method getDefaultSessionTrackingModes not implemented");
        }

        @Override
        public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
            throw new UnsupportedOperationException("Method getEffectiveSessionTrackingModes not implemented");
        }

        @Override
        public void addListener(String className) {
            throw new UnsupportedOperationException("Method addListener not implemented");
        }

        @Override
        public <T extends EventListener> void addListener(T t) {
            throw new UnsupportedOperationException("Method addListener not implemented");
        }

        @Override
        public void addListener(Class<? extends EventListener> listenerClass) {
            throw new UnsupportedOperationException("Method addListener not implemented");
        }

        @Override
        public <T extends EventListener> T createListener(Class<T> clazz) throws ServletException {
            throw new UnsupportedOperationException("Method createListener not implemented");
        }

        @Override
        public JspConfigDescriptor getJspConfigDescriptor() {
            throw new UnsupportedOperationException("Method getJspConfigDescriptor not implemented");
        }

        @Override
        public ClassLoader getClassLoader() {
            throw new UnsupportedOperationException("Method getClassLoader not implemented");
        }

        @Override
        public void declareRoles(String... roleNames) {
            throw new UnsupportedOperationException("Method declareRoles not implemented");
        }

        @Override
        public String getVirtualServerName() {
            throw new UnsupportedOperationException("Method getVirtualServerName not implemented");
        }

        @Override
        public int getSessionTimeout() {
            throw new UnsupportedOperationException("Method getSessionTimeout not implemented");
        }

        @Override
        public void setSessionTimeout(int sessionTimeout) {
            throw new UnsupportedOperationException("Method getSessionTimeout not implemented");
        }

        @Override
        public String getRequestCharacterEncoding() {
            throw new UnsupportedOperationException("Method getRequestCharacterEncoding not implemented");
        }

        @Override
        public void setRequestCharacterEncoding(String encoding) {
            throw new UnsupportedOperationException("Method setRequestCharacterEncoding not implemented");
        }

        @Override
        public String getResponseCharacterEncoding() {
            throw new UnsupportedOperationException("Method getResponseCharacterEncoding not implemented");
        }

        @Override
        public void setResponseCharacterEncoding(String encoding) {
            throw new UnsupportedOperationException("Method setResponseCharacterEncoding not implemented");
        }
    }

    public static final class ServletConfigMapTest extends AbstractServletConfigMapAdapterTest {

        public Map makeEmptyMap() {
            ServletConfig config = new TestConfig();
            return new ServletConfigMapAdapter(config);
        }

        public Map makeFullMap() {
            ServletConfig config = new TestConfig();
            addSampleMappings(((TestConfig) config).map);
            return new ServletConfigMapAdapter(config);
        }
    }

    public static final class FilterConfigMapTest extends AbstractServletConfigMapAdapterTest {

        public Map makeEmptyMap() {
            FilterConfig config = new TestConfig();
            return new ServletConfigMapAdapter(config);
        }

        public Map makeFullMap() {
            FilterConfig config = new TestConfig();
            addSampleMappings(((TestConfig) config).map);
            return new ServletConfigMapAdapter(config);
        }
    }

    public static final class ServletContextMapTest extends AbstractServletConfigMapAdapterTest {

        public Map makeEmptyMap() {
            ServletContext config = new TestConfig();
            return new ServletConfigMapAdapter(config);
        }

        public Map makeFullMap() {
            FilterConfig config = new TestConfig();
            addSampleMappings(((TestConfig) config).map);
            return new ServletConfigMapAdapter(config);
        }
    }
}
