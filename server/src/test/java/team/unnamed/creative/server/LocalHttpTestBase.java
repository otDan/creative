/*
 * This file is part of creative, licensed under the MIT license
 *
 * Copyright (c) 2021-2024 Unnamed Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.unnamed.creative.server;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import team.unnamed.creative.BuiltResourcePack;
import team.unnamed.creative.serialize.minecraft.MinecraftResourcePackWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

class LocalHttpTestBase {

    private static final int PORT = 7270;

    protected static BuiltResourcePack resourcePack;
    private static ResourcePackServer server;

    @BeforeAll
    static void setup() throws Exception {
        resourcePack = MinecraftResourcePackWriter.minecraft().build(resourcePack ->
                resourcePack.packMeta(8, "Resource pack!"));
        server = ResourcePackServer.server()
                .address(new InetSocketAddress(PORT))
                .pack(resourcePack, true)
                .build();
        server.start();
    }

    @AfterAll
    static void stopServer() {
        server.stop(0);
    }

    HttpURLConnection open(String path) throws IOException {
        return (HttpURLConnection) new URL("http://localhost:" + PORT + path)
                .openConnection();
    }

    void stream(InputStream input, OutputStream output) throws IOException {
        byte[] buf = new byte[1024];
        int read;
        while ((read = input.read(buf)) != -1) {
            output.write(buf, 0, read);
        }
    }

    String streamToString(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        stream(input, output);
        return output.toString();
    }

}
