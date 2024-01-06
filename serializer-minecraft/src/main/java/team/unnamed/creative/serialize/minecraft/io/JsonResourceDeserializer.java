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
package team.unnamed.creative.serialize.minecraft.io;

import com.google.gson.JsonElement;
import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Language;
import team.unnamed.creative.serialize.minecraft.GsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public interface JsonResourceDeserializer<T> extends ResourceDeserializer<T> {

    T deserializeFromJson(JsonElement node, Key key) throws IOException;

    default T deserializeFromJsonString(@Language("JSON") String json, Key key) throws IOException {
        return deserializeFromJson(GsonUtil.parseString(json), key);
    }

    @Override
    default T deserialize(InputStream input, Key key) throws IOException {
        try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            return deserializeFromJson(GsonUtil.parseReader(reader), key);
        }
    }

}
