/*
 * This file is part of uracle, licensed under the MIT license
 *
 * Copyright (c) 2021-2022 Unnamed Team
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
package team.unnamed.uracle;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import team.unnamed.uracle.font.FontRegistry;
import team.unnamed.uracle.lang.Language;
import team.unnamed.uracle.model.BlockState;
import team.unnamed.uracle.model.Model;
import team.unnamed.uracle.sound.SoundRegistry;
import team.unnamed.uracle.texture.Texture;

import java.util.Map;

import static java.util.Objects.requireNonNull;
import static team.unnamed.uracle.util.MoreCollections.immutableMapOf;

/**
 * Represents a Minecraft: Java Edition resources
 * pack
 *
 * @since 1.0.0
 */
public class ResourcePack {

    private final PackMeta meta;
    @Nullable private final Writable icon;

    private final Map<Key, FontRegistry> fonts;
    private final Map<Key, Language> languages;
    private final Map<Key, Model> models;
    private final Map<Key, BlockState> blockStates;
    private final Map<String, SoundRegistry> sounds;
    private final Map<Key, Texture> textures;
    private final Map<String, Writable> extra;

    private ResourcePack(
            PackMeta meta,
            @Nullable Writable icon,

            Map<Key, FontRegistry> fonts,
            Map<Key, Language> languages,
            Map<Key, Model> models,
            Map<Key, BlockState> blockStates,
            Map<String, SoundRegistry> sounds,
            Map<Key, Texture> textures,
            Map<String, Writable> extra
    ) {
        requireNonNull(meta, "meta");
        requireNonNull(fonts, "fonts");
        requireNonNull(languages, "languages");
        requireNonNull(models, "models");
        requireNonNull(blockStates, "blockStates");
        requireNonNull(sounds, "sounds");
        requireNonNull(textures, "textures");
        requireNonNull(extra, "extra");
        this.meta = meta;
        this.icon = icon;
        this.fonts = immutableMapOf(fonts);
        this.languages = immutableMapOf(languages);
        this.models = immutableMapOf(models);
        this.blockStates = immutableMapOf(blockStates);
        this.sounds = immutableMapOf(sounds);
        this.textures = immutableMapOf(textures);
        this.extra = immutableMapOf(extra);
    }

    /**
     * Returns this resource-pack meta-data, which
     * contains the resource-pack version, description
     * and language registrations
     *
     * @return The resource-pack meta-data
     * @since 1.0.0
     */
    public PackMeta meta() {
        return meta;
    }

    /**
     * Returns this resource-pack icon, which must
     * be a PNG image. It will be shown in the
     * resource-pack list menu
     *
     * @return The resource-pack icon, nullable
     * @since 1.0.0
     */
    public @Nullable Writable icon() {
        return icon;
    }

    /**
     * Returns an unmodifiable map of the resource-pack
     * font registrations
     *
     * @return The resource-pack fonts
     * @since 1.0.0
     */
    public @Unmodifiable Map<Key, FontRegistry> fonts() {
        return fonts;
    }

    /**
     * Returns an unmodifiable map of the resource-pack
     * language translations (not to be confused with
     * language registrations)
     *
     * @return The resource-pack translations
     * @since 1.0.0
     */
    public @Unmodifiable Map<Key, Language> languages() {
        return languages;
    }

    /**
     * Returns an unmodifiable map of the resource-pack
     * item and block models
     *
     * @return The resource-pack models
     * @since 1.0.0
     */
    public @Unmodifiable Map<Key, Model> models() {
        return models;
    }

    /**
     * Returns an unmodifiable map of the resource-pack
     * block state model overrides
     *
     * @return The resource-pack block states
     * @since 1.0.0
     */
    public @Unmodifiable Map<Key, BlockState> blockStates() {
        return blockStates;
    }

    /**
     * Returns an unmodifiable map of the resource-pack
     * sound registries (which represent the sounds.json
     * file) by namespaces
     *
     * @return The resource-pack sound registries
     * @since 1.0.0
     */
    public @Unmodifiable Map<String, SoundRegistry> sounds() {
        return sounds;
    }

    /**
     * Returns an unmodifiable map of the resource-pack
     * registered textures
     *
     * @return The resource-pack textures
     * @since 1.0.0
     */
    public @Unmodifiable Map<Key, Texture> textures() {
        return textures;
    }

    /**
     * Returns an unmodifiable map of extra files for
     * the resource-pack, this is only used when a high
     * level part is not implemented
     *
     * @return The resource-pack extra files
     * @since 1.0.0
     */
    public @Unmodifiable Map<String, Writable> extra() {
        return extra;
    }

}
