package com.github.hborders.streamsnonnulljsr305;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    class Foo {
        @Nonnull
        private final String string;

        Foo(@Nonnull String string) {
            this.string = string;
        }

        @Nonnull
        String getString() {
            return string;
        }
    }

    class Bar {
        @Nullable
        private final Foo foo;

        Bar(@Nullable Foo foo) {
            this.foo = foo;
        }

        @Nullable
        Foo getFoo() {
            return foo;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bar bar1 = new Bar(new Foo("foo"));
        final Bar bar2 = new Bar(null);
        final Bar[] bars = new Bar[]{
                null,
                bar1,
                bar2,
        };
        final List<String> strings = Arrays
                .stream(bars)
                .map(Bar::getFoo)
                .filter(Objects::nonNull)
                .map(Foo::getString)
                .collect(Collectors.toList());
        System.out.println("strings: " + strings);

    }
}
