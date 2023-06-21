package org.recipetoria.base;

import com.github.javafaker.Faker;

public class utils {
    Faker faker = new Faker();
    public String generatePassword(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial) {
        if (includeSpecial) {
            char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase).toCharArray();
            char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
            for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
                password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
            }
            return new String(password);
        } else {
            return faker.lorem().characters(minimumLength, maximumLength, includeUppercase);
        }
    }
}
