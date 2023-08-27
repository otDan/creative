/*
 * This file is part of creative, licensed under the MIT license
 *
 * Copyright (c) 2021-2023 Unnamed Team
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
package team.unnamed.creative.serialize.minecraft.sound;

import org.jetbrains.annotations.ApiStatus;
import team.unnamed.creative.ResourcePack;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.serialize.minecraft.ResourceCategory;
import team.unnamed.creative.sound.Sound;

import java.io.IOException;
import java.io.UncheckedIOException;

@ApiStatus.Internal
public class SoundSerializer {

    public static final ResourceCategory<Sound.File> CATEGORY = new ResourceCategory<>(
            "sounds",
            ".ogg",
            ResourcePack::sound,
            ResourcePack::sounds,
            (key, input) -> {
                try {
                    return Sound.File.of(key, Writable.copyInputStream(input));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            },
            (sound, output) -> {
                try {
                    sound.data().write(output);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
    );

}
