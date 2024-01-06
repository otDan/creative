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
package team.unnamed.creative.serialize.minecraft.metadata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import team.unnamed.creative.metadata.animation.AnimationFrame;
import team.unnamed.creative.metadata.animation.AnimationMeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimationMetaTest {
    @Test
    @DisplayName("Test simple AnimationMeta serialization")
    void test_serialization() {
        final AnimationMeta meta = AnimationMeta.animation()
                .frameTime(6)
                .interpolate(false)
                .frames(0, 1, 2, 3, 2, 1)
                .build();

        assertEquals(
                "{\"frametime\":6,\"frames\":[0,1,2,3,2,1]}",
                AnimationMetaCodec.INSTANCE.toJson(meta)
        );
    }

    @Test
    @DisplayName("Test AnimationMeta serialization with specific frame time")
    void test_combined_serialization() {
        final AnimationMeta meta = AnimationMeta.animation()
                .frameTime(6)
                .interpolate(true)
                .frames(
                        AnimationFrame.frame(0),
                        AnimationFrame.frame(1, 6), // <-- frame time specified, but same as container
                        AnimationFrame.frame(2),
                        AnimationFrame.frame(3, 10), // <-- longer frame time
                        AnimationFrame.frame(2),
                        AnimationFrame.frame(1)
                )
                .build();

        assertEquals(
                "{\"interpolate\":true,\"frametime\":6,\"frames\":[0,1,2,{\"index\":3,\"time\":10},2,1]}",
                AnimationMetaCodec.INSTANCE.toJson(meta)
        );
    }
}
