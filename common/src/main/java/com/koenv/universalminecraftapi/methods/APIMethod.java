package com.koenv.universalminecraftapi.methods;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Denotes an API method.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIMethod {
    /**
     * Namespace of the command, that can only be used if {@link #operatesOn()} if empty.
     * <p>
     * This namespace will always be needed to call the command. If the namespace is `players` and the name of the
     * method is `getPlayers()`, the method will always be called using `players.getPlayers()`.
     *
     * @return The namespace of the command
     */
    @NotNull String namespace() default "";

    /**
     * The class this method operates on, if any. When this is used, {@link #namespace()} cannot be used.
     * <p>
     * For example, if the class `Player` needs a method `getName()`, create a method `getName()` as follows:
     * <pre>
     *     &#64;APIMethod(operatesOn = Player.class)
     *     public static String getName(Player self) {
     *         return self.getName():
     *     }
     * </pre>
     *
     * @return The class this command operates on
     */
    Class<?> operatesOn() default DEFAULT.class;

    /**
     * Default {@link #operatesOn()} class to see whether {@link #operatesOn()} has been set.
     */
    final class DEFAULT {
    }
}