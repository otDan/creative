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
package team.unnamed.creative.atlas;

import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

final class DirectoryAtlasSourceImpl implements DirectoryAtlasSource {

    private final String source;
    private final String prefix;

    DirectoryAtlasSourceImpl(
            final @NotNull String source,
            final @NotNull String prefix
    ) {
        this.source = requireNonNull(source, "source");
        this.prefix = requireNonNull(prefix, "prefix");
    }

    @Override
    public @NotNull String source() {
        return source;
    }

    @Override
    public @NotNull String prefix() {
        return prefix;
    }

    @Override
    public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(
                ExaminableProperty.of("source", source),
                ExaminableProperty.of("prefix", prefix)
        );
    }

    @Override
    public String toString() {
        return examine(StringExaminer.simpleEscaping());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryAtlasSourceImpl that = (DirectoryAtlasSourceImpl) o;
        if (!source.equals(that.source)) return false;
        return prefix.equals(that.prefix);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + prefix.hashCode();
        return result;
    }

}
