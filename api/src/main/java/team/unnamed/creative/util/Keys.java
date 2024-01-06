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
package team.unnamed.creative.util;

import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.IntPredicate;

@ApiStatus.Internal
public final class Keys {

    // taken from KeyImpl (adventure-key)
    private static final IntPredicate NAMESPACE_PREDICATE = value -> value == '_' || value == '-' || (value >= 'a' && value <= 'z') || (value >= '0' && value <= '9') || value == '.';

    private Keys() {
    }

    public static void validateNamespace(
            @Subst(Key.MINECRAFT_NAMESPACE) String namespace
    ) {
        Key.key(namespace, "dummy");
    }

    public static boolean isValidNamespace(String namespace) {
        // taken from KeyImpl (adventure-key)
        for (int i = 0, length = namespace.length(); i < length; i++) {
            if (!NAMESPACE_PREDICATE.test(namespace.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
