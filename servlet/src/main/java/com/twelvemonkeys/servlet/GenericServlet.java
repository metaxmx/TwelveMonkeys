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

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.twelvemonkeys.lang.BeanUtil;

/**
 * Defines a generic, protocol-independent servlet.
 * <p>
 * {@code GenericServlet} has an auto-init system, that automatically invokes
 * the method matching the signature {@code void setX(&lt;Type&gt;)},
 * for every init-parameter {@code x}. Both camelCase and lisp-style parameter
 * naming is supported, lisp-style names will be converted to camelCase.
 * Parameter values are automatically converted from string representation to
 * most basic types, if necessary.
 * </p>
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haku $
 * 
 * @version $Id: GenericServlet.java#1 $
 */
@Deprecated
public abstract class GenericServlet extends javax.servlet.GenericServlet {
    // TODO: Rewrite to use ServletConfigurator instead of BeanUtil

    /**
     * Called by the web container to indicate to a servlet that it is being
     * placed into service.
     * <p>
     * This implementation stores the {@code ServletConfig} object it
     * receives from the servlet container for later use. When overriding this
     * form of the method, call {@code super.init(config)}.
     * </p>
     * <p>
     * This implementation will also set all configured key/value pairs, that
     * have a matching setter method annotated with {@link InitParam}.
     * </p>
     *
     * @param pConfig the servlet config
     * @throws ServletException if the servlet could not be initialized.
     *
     * @see javax.servlet.GenericServlet#init
     * @see #init() init
     * @see BeanUtil#configure(Object, java.util.Map, boolean)
     */
    @Override
    public void init(final ServletConfig pConfig) throws ServletException {
        if (pConfig == null) {
            throw new ServletConfigException("servlet config == null");
        }

        try {
            BeanUtil.configure(this, ServletUtil.asMap(pConfig), true);
        }
        catch (InvocationTargetException e) {
            throw new ServletConfigException("Could not configure " + getServletName(), e.getCause());
        }

        super.init(pConfig);
    }
}
