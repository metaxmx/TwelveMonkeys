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

package com.twelvemonkeys.servlet.fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.Part;

import java.io.File;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@code HttpFileUploadRequest} implementation, based on
 * <a href="http://jakarta.apache.org/commons/fileupload/">Jakarta Commons FileUpload</a>.
 *
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @version $Id: HttpFileUploadRequestWrapper.java#1 $
 */
class HttpFileUploadRequestWrapper extends HttpServletRequestWrapper implements HttpFileUploadRequest {

    private final Map<String, String[]> parameters = new HashMap<String, String[]>();
    private final Map<String, UploadedFile[]> files = new HashMap<String, UploadedFile[]>();

    public HttpFileUploadRequestWrapper(HttpServletRequest pRequest, File pUploadDir, long pMaxSize) throws ServletException {
        super(pRequest);

        // TODO: Defer request parsing??
        try {

            //noinspection unchecked
            for (Part item : pRequest.getParts()) {
                processeFile(item);
            }
            for (Map.Entry<String, String[]> parameter : pRequest.getParameterMap().entrySet()) {
                for (String value : parameter.getValue()) {
                    processFormField(parameter.getKey(), value);
                }
            }
        }
        catch (java.io.IOException e) {
            throw new FileUploadException(e);
        }
    }

    private void processeFile(final Part pItem) {
        UploadedFile value = new UploadedFileImpl(pItem);
        String name = pItem.getName();

        UploadedFile[] values;
        UploadedFile[] oldValues = files.get(name);

        if (oldValues != null) {
            values = new UploadedFile[oldValues.length + 1];
            System.arraycopy(oldValues, 0, values, 0, oldValues.length);
            values[oldValues.length] = value;
        }
        else {
            values = new UploadedFile[] {value};
        }

        files.put(name, values);

        // Also add to normal fields
        processFormField(name, value.getName());
    }

    private void processFormField(String pName, String pValue) {
        // Multiple parameter values are not that common, so it's
        // probably faster to just use arrays...
        // TODO: Research and document...
        String[] values;
        String[] oldValues = parameters.get(pName);

        if (oldValues != null) {
            values = new String[oldValues.length + 1];
            System.arraycopy(oldValues, 0, values, 0, oldValues.length);
            values[oldValues.length] = pValue;
        }
        else {
            values = new String[] {pValue};
        }

        parameters.put(pName, values);
    }

    public Map getParameterMap() {
        // TODO: The spec dicates immutable map, but what about the value arrays?!
        // Probably just leave as-is, for performance
        return Collections.unmodifiableMap(parameters);
    }

    public Enumeration getParameterNames() {
        return Collections.enumeration(parameters.keySet());
    }

    public String getParameter(String pString) {
        String[] values = getParameterValues(pString);
        return values != null ? values[0] : null;
    }

    public String[] getParameterValues(String pString) {
        // TODO: Optimize?
        return parameters.get(pString).clone();
    }

    public UploadedFile getUploadedFile(String pName) {
        UploadedFile[] files = getUploadedFiles(pName);
        return files != null ? files[0] : null;
    }

    public UploadedFile[] getUploadedFiles(String pName) {
        // TODO: Optimize?
        return files.get(pName).clone();
    }
}