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

import java.net.URL;
import jonburney.version7.kingsgatemediaplayer.Exceptions.Http.HttpRequestException;

public class HttpRequest implements IHttpRequest {

    private URL url;
    private String method;

    @Override
    public void setUrl(String url) throws HttpRequestException {

        if (url == "") {
            throw new HttpRequestException("The target URL must be specified");
        }

        try {
            this.url = new URL(url);
        } catch (Exception e) {
            throw new HttpRequestException(e.getMessage());
        }
    }

    /**
     * Gets the URL specified for this request
     * @throws HttpRequestException
     */
    @Override
    public URL getUrl() throws HttpRequestException {

        if (this.url == null) {
            throw new HttpRequestException("The target URL must be specified");
        }

        return this.url;
    }

    /**
     * Set the HTTP method to use for the request
     * @param method
     * @throws HttpRequestException
     */
    @Override
    public void setMethod(String method) throws HttpRequestException {

        // Be nice and let people use lower-case
        method = method.toUpperCase();

        // Check that we are using supported HTTP verbs. The only current use-cases are for GET
        switch (method) {
            case "GET":
            case "POST":
            case "PUT":
            case "DELETE":
                this.method = method;
                return;
        }

        // Something else was passed so throw
        throw new HttpRequestException("The specified HTTP method is not supported");

    }

    @Override
    public String getMethod() {
        return this.method;
    }

}
