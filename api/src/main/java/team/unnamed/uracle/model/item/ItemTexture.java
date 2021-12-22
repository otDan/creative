/*
 * This file is part of uracle, licensed under the MIT license
 *
 * Copyright (c) 2021 Unnamed Team
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
package team.unnamed.uracle.model.item;

import net.kyori.adventure.key.Key;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import team.unnamed.uracle.model.ItemModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;

/**
 * Object holding an {@link ItemModel} textures,
 * every texture is stored in form of a {@link Key}
 * (resource location)
 *
 * @since 1.0.0
 */
public class ItemTexture implements Examinable {

    /**
     * Specifies the item texture layers. Only used
     * to specify the icon of the item used in the
     * inventory.
     *
     * <p>There can be more than just one layer (e.g.
     * for spawn eggs), but the amount of possible
     * layers is hardcoded for each item.</p>
     *
     * <p>Works only in combination with
     * {@link ItemModel#ITEM_GENERATED}</p>
     */
    @Unmodifiable private final List<Key> layers;

    /**
     * What texture to load particles from. Used to
     * determine the "crumb" particles generated by
     * food items, as well as to determine the barrier
     * particle (but it always uses items/barrier.png
     * as blockbreaking particle), which otherwise uses
     * the layer 0 texture
     */
    @Nullable private final Key particle;

    /**
     * Map to define custom texture variables and assign
     * textures to them
     */
    @Unmodifiable private final Map<String, Key> variables;

    private ItemTexture(
            List<Key> layers,
            @Nullable Key particle,
            Map<String, Key> variables
    ) {
        requireNonNull(layers, "layers");
        this.layers = unmodifiableList(new ArrayList<>(layers));
        this.particle = particle;
        this.variables = unmodifiableMap(new HashMap<>(variables));
    }

    /**
     * Returns the item texture layers list,
     * unmodifiable. These textures specify
     * the item icon in the inventory
     *
     * @return The texture layers
     */
    public @Unmodifiable List<Key> layers() {
        return layers;
    }

    /**
     * Returns the item particle texture resource
     * location
     *
     * @return The particle texture
     */
    public @Nullable Key particle() {
        return particle;
    }

    /**
     * Returns an unmodifiable map of the texture
     * variable definitions
     *
     * @return The texture variables
     */
    public @Unmodifiable Map<String, Key> variables() {
        return variables;
    }

    @Override
    public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(
                ExaminableProperty.of("layers", layers),
                ExaminableProperty.of("particle", particle),
                ExaminableProperty.of("variables", variables)
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
        ItemTexture that = (ItemTexture) o;
        return layers.equals(that.layers)
                && Objects.equals(particle, that.particle)
                && variables.equals(that.variables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(layers, particle, variables);
    }

    /**
     * Creates a new {@link ItemTexture} from
     * the given values
     *
     * @param layers The texture layers
     * @param particle The particle texture
     * @param variables The texture variables
     * @return A new {@link ItemTexture} instance
     * @since 1.0.0
     */
    public static ItemTexture of(
            List<Key> layers,
            @Nullable Key particle,
            Map<String, Key> variables
    ) {
        return new ItemTexture(layers, particle, variables);
    }

    /**
     * Static factory method for instantiating our
     * builder implementation, which eases the construction
     * of new {@link ItemTexture} instances
     *
     * @return A new {@link Builder} instance
     * @since 1.0.0
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder implementation for {@link ItemTexture}
     * objects
     *
     * @since 1.0.0
     */
    public static class Builder {

        private List<Key> layers = Collections.emptyList();
        private Key particle;
        private Map<String, Key> variables = Collections.emptyMap();

        private Builder() {
        }

        public Builder layers(List<Key> layers) {
            this.layers = requireNonNull(layers, "layers");
            return this;
        }

        public Builder particle(@Nullable Key particle) {
            this.particle = particle;
            return this;
        }

        public Builder variables(Map<String, Key> variables) {
            this.variables = requireNonNull(variables, "variables");
            return this;
        }

        /**
         * Finishes the building of the {@link ItemTexture}
         * instance by instantiating it using the previously
         * set values
         *
         * @return A new {@link ItemTexture} instance
         */
        public ItemTexture build() {
            return new ItemTexture(layers, particle, variables);
        }

    }

}
