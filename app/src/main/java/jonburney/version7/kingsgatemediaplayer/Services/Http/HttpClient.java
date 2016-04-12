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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.HttpClientException;

/**
 * HttpClient used for making Http requests defined in a HttpRequest object
 */
public class HttpClient implements IHttpClient {

    public HttpClient() {

    }

    /**
     * Execute a HttpRequest using the supplied HttpRequest object
     *
     * @param request The instance of HttpRequest that we are executing
     * @return HttpResponse
     * @throws HttpClientException
     */
    @Override
    public HttpResponse execute(HttpRequest request) throws HttpClientException {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) request.getUrl().openConnection();
        } catch (Exception e) {
            throw new HttpClientException(e.getMessage());
        }

        try {
            conn.setRequestMethod(request.getMethod());
        } catch (ProtocolException e) {
            throw new HttpClientException(e.getMessage());
        }

        try {
            conn.connect();
            return new HttpResponse(conn.getInputStream());
        } catch (IOException e) {
            throw new HttpClientException(e.getMessage());
        }
    }
}