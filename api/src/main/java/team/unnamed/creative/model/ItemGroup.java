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
package team.unnamed.creative.model;

import net.kyori.adventure.key.Key;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;
import team.unnamed.creative.base.Vector3Float;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ItemGroup implements Examinable {

    private final String name;
    private final Vector3Float origin;
    private final int color;
    private final List<Integer> groupIndexes;
//    @Unmodifiable private final List<ItemPredicate> predicate;

    private ItemGroup(
            String name,
            Vector3Float origin,
            int color,
            List<Integer> groupIndexes
    ) {
        requireNonNull(origin, "origin");
        requireNonNull(groupIndexes, "groupIndexes");
        this.name = name;
        this.origin = origin;
        this.color = color;
        this.groupIndexes = groupIndexes;
    }

    @Override
    public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(
                ExaminableProperty.of("groupIndexes", groupIndexes),
                ExaminableProperty.of("color", color),
                ExaminableProperty.of("origin", origin),
                ExaminableProperty.of("name", name)
        );
    }

    @Override
    public String toString() {
        return examine(StringExaminer.simpleEscaping());
    }

    @Override
    public boolean equals(Object o) {
        return false;
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ItemOverride that = (ItemOverride) o;
//        return predicate.equals(that.predicate)
//                && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupIndexes, origin, name);
    }

    /**
     * Creates a new {@link ItemOverride} instance
     * from the given values
     *
     * @param name The group name
     * @return A new {@link ItemOverride} instance
     * @since 1.0.0
     */
    public static ItemGroup of(
            String name,
            Vector3Float origin,
            int color,
            List<Integer> groupIndexes
    ) {
        return new ItemGroup(name, origin, color, groupIndexes);
    }

    public static ItemGroup of(
            Vector3Float origin,
            int color,
            List<Integer> groupIndexes
    ) {
        return new ItemGroup("", origin, color, groupIndexes);
    }

    public static ItemGroup of(
            Vector3Float origin,
            List<Integer> groupIndexes
    ) {
        return new ItemGroup("", origin, 0, groupIndexes);
    }

    /**
     * Creates a new {@link ItemOverride} instance
     * from the given values
     *
     * @param name The group name
     * @return A new {@link ItemOverride} instance
     * @since 1.0.0
     */
    public static ItemGroup of(
            String name,
            Vector3Float origin,
            int color,
            Integer... groupIndexes
    ) {
        return new ItemGroup(name, origin, color, Arrays.asList(groupIndexes));
    }

}