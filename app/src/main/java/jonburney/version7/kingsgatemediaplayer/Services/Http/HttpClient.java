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

/**
 * Created by jburney on 20/02/2016.
 */
public class HttpClient implements IHttpClient {


    public HttpClient() {

    }

    @Override
    public HttpResponse execute(HttpRequest request) throws IOException {

        HttpURLConnection conn = (HttpURLConnection) request.getUrl().openConnection();
        conn.setRequestMethod(request.getMethod());
        conn.connect();

        return new HttpResponse(conn.getInputStream());
    }
}