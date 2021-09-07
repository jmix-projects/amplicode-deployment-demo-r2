package io.jmix2mvp.petclinic;

import io.jmix2mvp.petclinic.entity.TestEntity;
import io.jmix2mvp.petclinic.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
class PetclinicApplicationTests {
    @Autowired
    private TestRepository testRepository;

    private static final String STRING_VALUE = "VALUE";

    @Test
    void contextLoads() {
    }

    @Test
    void initTestData() throws MalformedURLException {
        TestEntity testEntity = new TestEntity();

        byte[] bytes = STRING_VALUE.getBytes(StandardCharsets.UTF_8);
        char[] chars = STRING_VALUE.toCharArray();
        long currentTimeMillis = System.currentTimeMillis();

        testEntity.setString(STRING_VALUE);
        testEntity.setBool(Boolean.TRUE);
        testEntity.setBoolPrimitive(true);

        testEntity.setDoubleTest(2.5);
        testEntity.setIntTest(2);
        testEntity.setLongTest(2L);
        testEntity.setBigDecimal(new BigDecimal(3));

        //TODO: SQL Types
        testEntity.setDate(new Date(currentTimeMillis));
        testEntity.setTime(new Time(currentTimeMillis));
        testEntity.setTimeStamp(new Timestamp(currentTimeMillis));

        testEntity.setDateUtil(new java.util.Date(currentTimeMillis));
        testEntity.setUuidTest(UUID.randomUUID());

        testEntity.setByteTest(Byte.valueOf((byte) 15));
        testEntity.setBytePrimitive(Byte.valueOf((byte) 15));

        testEntity.setFloatTest(2.5F);
        testEntity.setShortTest((short) 2);
        testEntity.setShortPrimitive((short) 2);
        testEntity.setBigInt(new BigInteger("2"));

        testEntity.setUrl(new URL("http://localhost:8080"));

        testEntity.setDuration(Duration.ofDays(3));
        testEntity.setInstant(Instant.now());
        testEntity.setLocalDate(LocalDate.now());
        testEntity.setLocalDateTime(LocalDateTime.now());
        testEntity.setLocalTime(LocalTime.now());
        testEntity.setOffsetDateTime(OffsetDateTime.now());
        testEntity.setOffsetTime(OffsetTime.now());
        testEntity.setZonedDateTime(ZonedDateTime.now());
        testEntity.setCalendar(Calendar.getInstance());

        testEntity.setCurrency(Currency.getInstance(Locale.FRANCE));
        testEntity.setLocale(Locale.FRANCE);

        testEntity.setCharPrimitive(chars[0]);
        testEntity.setCharacter(chars[0]);

        testEntity.setBytePrimitiveArray(bytes);
        testEntity.setByteArray(IntStream.range(0, bytes.length)
                .mapToObj(i -> bytes[i])
                .toArray(Byte[]::new));

        testEntity.setCharPrimitiveArray(chars);

        testEntity.setCharArray(IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i])
                .toArray(Character[]::new));

        testRepository.save(testEntity);
    }

}
