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

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.HttpClientException;
import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.HttpRequestException;
import kotlin.jvm.Throws;

import static junit.framework.Assert.*;
import static junit.extensions.TestSetup.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class HttpClientTest {

    public HttpClientTest() {
    }

    @Mock HttpRequest httpRequestMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testMakingARequestWithNoUrlThorwsException() throws HttpClientException, HttpRequestException {

        thrown.expect(HttpClientException.class);

        HttpRequest httpRequest = new HttpRequest();

        HttpClient httpClient = new HttpClient();
        httpClient.execute(httpRequest);
        verify(httpRequestMock.getUrl(), times(1));
    }

    @Test
    public void testTestMakingARequestWithNoUrlThorwsException() throws Exception {

    }
}