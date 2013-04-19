package com.nearinfinity.examples.guava;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static com.google.common.base.CharMatcher.inRange;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CharMatcherTest {

    @Test
    public void testRemoveAllDigits() {
        String input = "hello2world";
        String result = CharMatcher.DIGIT.removeFrom(input);
        assertThat(result, is("helloworld"));
    }

    @Test
    public void testRetainAllDigits() {
        String secretCode = "this1is2a3really4good5code";
        String result = CharMatcher.DIGIT.retainFrom(secretCode);
        assertThat(result, is("12345"));
    }

    @Test
    public void testMatchingDigitsOrAlphabeticCharacters() {
        CharMatcher matcher = CharMatcher.DIGIT.or(inRange('a', 'z').or(inRange('A', 'Z')));
        assertTrue(matcher.matchesAllOf("this1will2match3"));
        assertFalse(matcher.matchesAllOf("this-will-not-match"));
    }

    @Test
    public void testMatchingPartOfAString() {
        CharMatcher matcher = CharMatcher.DIGIT.or(inRange('a', 'z').or(inRange('A', 'Z'))).or(CharMatcher.is('*'));
        assertTrue(matcher.matchesAnyOf("@#$@hello@#$@"));
        assertFalse(matcher.matchesAnyOf("%#$##$#@@"));
    }

    @Test
    public void testStrippingWhitespace() {
        String input = "  secret passcode  ";
        String result = CharMatcher.WHITESPACE.trimFrom(input);
        assertThat(result, is("secret passcode"));
    }
}
