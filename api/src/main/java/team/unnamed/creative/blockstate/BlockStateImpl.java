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
package team.unnamed.creative.blockstate;

import net.kyori.adventure.key.Key;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

final class BlockStateImpl implements BlockState {

    private final Key key;
    private final Map<String, MultiVariant> variants;
    private final List<Selector> multipart;

    BlockStateImpl(
            final @NotNull Key key,
            final @NotNull Map<String, MultiVariant> variants,
            final @NotNull List<Selector> multipart
    ) {
        this.key = requireNonNull(key, "key");
        this.variants = requireNonNull(variants, "variants");
        this.multipart = requireNonNull(multipart, "multipart");
        validate();
    }

    private void validate() {
        if (variants.isEmpty() && multipart.isEmpty()) {
            throw new IllegalArgumentException("variants and multipart cannot be both empty!");
        }
    }

    @Override
    public @NotNull Key key() {
        return key;
    }

    public Map<String, MultiVariant> variants() {
        return variants;
    }

    public List<Selector> multipart() {
        return multipart;
    }

    @Override
    public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(
                ExaminableProperty.of("key", key),
                ExaminableProperty.of("variants", variants),
                ExaminableProperty.of("multipart", multipart)
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
        BlockStateImpl that = (BlockStateImpl) o;
        return key.equals(that.key)
                && variants.equals(that.variants)
                && multipart.equals(that.multipart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, variants, multipart);
    }

}
