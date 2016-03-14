/*
 * Kingsgate Media Player
 * Copyright (C) 2016 Jon Burney (jon@version7.co.uk)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package jonburney.version7.kingsgatemediaplayer.Services.Http;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.HttpRequestException;

public class HttpRequestTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public HttpRequestTest() {

    }

    @Test
    public void testPassingAMalformedUrlThrowsExcaption() throws HttpRequestException {
        thrown.expect(HttpRequestException.class);

        IHttpRequest request = new HttpRequest();
        request.setUrl("BLAH:BLAH.BLAH.COM/BLAH");
    }

    @Test
    public void testPassingValidUrl() throws HttpRequestException {
        String targetUrl = "http://www.google.com";
        IHttpRequest request = new HttpRequest();

        try {
            request.setUrl(targetUrl);
        } catch (HttpRequestException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(targetUrl, request.getUrl().toString());
    }

    @Test
    public void testFetchingUrlWithoutSettingThrowsException() throws HttpRequestException {

        thrown.expect(HttpRequestException.class);
        thrown.expectMessage("The target URL must be specified");

        IHttpRequest request = new HttpRequest();
        request.getUrl();
    }

    @Test
    public void testHttpMethodPutIsReturnedCorrectly() throws HttpRequestException {
        String method = "PUT";
        IHttpRequest request = new HttpRequest();

        request.setMethod(method);
        Assert.assertEquals(method, request.getMethod());
    }

    @Test
    public void testHttpMethodPostIsReturnedCorrectly() throws HttpRequestException {
        String method = "POST";
        IHttpRequest request = new HttpRequest();

        request.setMethod(method);
        Assert.assertEquals(method, request.getMethod());
    }

    @Test
    public void testHttpMethodDeleteIsReturnedCorrectly() throws HttpRequestException {
        String method = "DELETE";
        IHttpRequest request = new HttpRequest();

        request.setMethod(method);
        Assert.assertEquals(method, request.getMethod());
    }

    @Test
    public void testHttpMethodGetIsReturnedCorrectly() throws HttpRequestException {
        String method = "GET";
        IHttpRequest request = new HttpRequest();

        request.setMethod(method);
        Assert.assertEquals(method, request.getMethod());
    }

    @Test
    public void testLowerCaseHttpMethodIsAllowedAndConverted() throws HttpRequestException {
        String methodLowerCase = "get";
        IHttpRequest request = new HttpRequest();

        request.setMethod(methodLowerCase);
        Assert.assertEquals("GET", request.getMethod());
    }

    @Test
    public void testUnsupportedHttpMethodThrowsException() throws HttpRequestException {
        thrown.expect(HttpRequestException.class);
        thrown.expectMessage("The specified HTTP method is not supported");

        IHttpRequest request = new HttpRequest();
        request.setMethod("UNSUPPORTED");
    }

    @Test
    public void testEmptyUrlThrowsException() throws HttpRequestException {

        thrown.expect(HttpRequestException.class);
        thrown.expectMessage("The target URL must be specified");

        IHttpRequest request = new HttpRequest();
        request.setUrl("");
    }
}
