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
package team.unnamed.creative.metadata;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a resource-pack resource metadata
 * part. Every resource-pack resource can have
 * metadata, metadata is compound by multiple
 * metadata parts
 *
 * @since 1.0.0
 */
public interface MetadataPart extends Examinable {
    /**
     * Returns the type of this metadata part.
     *
     * <p>By default this method will just call
     * {@code getClass()}, but it has another
     * function.</p>
     *
     * <p>The main function of this method is to
     * identify this metadata part type. So, classes
     * that implement the same metadata part type
     * should return the same class value.</p>
     *
     * @return The metadata part type
     * @since 1.4.0
     */
    default @NotNull Class<? extends MetadataPart> type() {
        return getClass();
    }
}
