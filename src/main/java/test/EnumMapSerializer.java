/*
 * Copyright 2010 Martin Grotzke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;

/**
 * A serializer for {@link EnumMap}s.
 *
 * @author <a href="mailto:martin.grotzke@javakaffee.de">Martin Grotzke</a>
 */
public class EnumMapSerializer extends Serializer<EnumMap<? extends Enum<?>, ?>> {

    private static final Field TYPE_FIELD;

    static {
        try {
            TYPE_FIELD = EnumMap.class.getDeclaredField("keyType");
            TYPE_FIELD.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException("The EnumMap class seems to have changed, could not access expected field.", e);
        }
    }

    // Workaround reference reading, this should be removed sometimes. See also
    // https://groups.google.com/d/msg/kryo-users/Eu5V4bxCfws/k-8UQ22y59AJ
    private static final Object FAKE_REFERENCE = new Object();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public EnumMap<? extends Enum<?>, ?> copy(Kryo kryo, EnumMap<? extends Enum<?>, ?> original) {
        // Make a shallow copy to copy the private key type of the original map without using reflection.
        // This will work for empty original maps as well.
        EnumMap copy = new EnumMap(original);
        for (Map.Entry entry : original.entrySet()) {
            copy.put((Enum) entry.getKey(), kryo.copy(entry.getValue()));
        }
        return copy;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private EnumMap<? extends Enum<?>, ?> create(Kryo kryo, Input input) {
        Class<? extends Enum<?>> keyType = kryo.readClass(input).getType();
        return new EnumMap(keyType);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public EnumMap<? extends Enum<?>, ?> read(Kryo kryo, Input input,
                                              Class<? extends EnumMap<? extends Enum<?>, ?>> type) {
        kryo.reference(FAKE_REFERENCE);
        EnumMap<? extends Enum<?>, ?> result = create(kryo, input);
        Class<Enum<?>> keyType = getKeyType(result);
        Enum<?>[] enumConstants = keyType.getEnumConstants();
        int size = input.readInt(true);
        for (int i = 0; i < size; i++) {
            int ordinal = input.readVarInt(true);
            Enum<?> key = enumConstants[ordinal];
            Object value = kryo.readClassAndObject(input);
            ((EnumMap) result).put(key, value);
        }
        return result;
    }

    @Override
    public void write(Kryo kryo, Output output, EnumMap<? extends Enum<?>, ?> map) {
        kryo.writeClass(output, getKeyType(map));
        output.writeInt(map.size(), true);
        for (Map.Entry<? extends Enum<?>, ?> entry : map.entrySet()) {
            output.writeVarInt(entry.getKey().ordinal(), true);
            kryo.writeClassAndObject(output, entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Enum<?>> getKeyType(Map<?, ?> map) {
        try {
            return (Class<Enum<?>>) TYPE_FIELD.get(map);
        } catch (Exception e) {
            throw new RuntimeException("Could not access keys field.", e);
        }
    }
}
