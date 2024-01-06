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
package team.unnamed.creative.server.util;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@ApiStatus.Internal
public final class UndashedUUID {
    private static final int HEX_RADIX = 16;

    private UndashedUUID() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static @NotNull UUID fromUndashedString(final @NotNull String s) {
        if (s.length() != 32) {
            throw new IllegalArgumentException("Undashed string must be 32 characters long");
        }
        final long msb = parseUnsignedHexLong(s.substring(0, 16));
        final long lsb = parseUnsignedHexLong(s.substring(16));
        return new UUID(msb, lsb);
    }

    private static long parseUnsignedHexLong(final @NotNull String s) {

        // from Long.parseUnsignedLong in Java 9+
        final int len = s.length();

        if (s.charAt(0) == '-') {
            throw new IllegalArgumentException("Illegal leading minus sign on unsigned string " + s);
        }

        final long first = Long.parseLong(s.substring(0, len - 1), HEX_RADIX);
        final int second = Character.digit(s.charAt(len - 1), HEX_RADIX);
        if (second < 0) {
            throw new IllegalArgumentException("Bad digit at end of " + s);
        }
        final long result = first * HEX_RADIX + second;

        final int guard = HEX_RADIX * (int) (first >>> 57);
        if (guard >= 128 || (result >= 0 && guard >= 128 - Character.MAX_RADIX)) {
            throw new IllegalArgumentException("String value " + s
                    + " exceeds range of unsigned long");
        }
        return result;
    }
}
