package io.jmix2mvp.petclinic.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class TestInputDTO extends BaseDTO {

    private String string;

    private Boolean bool;

    private Double doubleTest;

    private Integer intTest;

    private Long longTest;

    private BigDecimal bigDecimal;

    private Date date;

    private Time time;

    private Timestamp timeStamp;

    private java.util.Date dateUtil;

    private UUID uuidTest;

    private Byte byteTest;

    private Character character;

//    private Class classTest;

    private Float floatTest;

    private Short shortTest;

    private BigInteger bigInt;

    private URL url;
//
//    private Blob blob;
//
//    private Clob clob;
//
//    private NClob nclob;

    private Duration duration;

    private Instant instant;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    private LocalTime localTime;

    private OffsetDateTime offsetDateTime;

    private OffsetTime offsetTime;

    private ZonedDateTime zonedDateTime;

    private Calendar calendar;

    private Currency currency;

    private Locale locale;

    private boolean boolPrimitive;

    private byte bytePrimitive;

    private char charPrimitive;

    private short shortPrimitive;

//    private Byte[] byteArray;
//
//    private Character[] charArray;
//
//    private byte[] bytePrimitiveArray;
//
//    private char[] charPrimitiveArray;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Double getDoubleTest() {
        return doubleTest;
    }

    public void setDoubleTest(Double doubleTest) {
        this.doubleTest = doubleTest;
    }

    public Integer getIntTest() {
        return intTest;
    }

    public void setIntTest(Integer intTest) {
        this.intTest = intTest;
    }

    public Long getLongTest() {
        return longTest;
    }

    public void setLongTest(Long longTest) {
        this.longTest = longTest;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public java.util.Date getDateUtil() {
        return dateUtil;
    }

    public void setDateUtil(java.util.Date dateUtil) {
        this.dateUtil = dateUtil;
    }

    public UUID getUuidTest() {
        return uuidTest;
    }

    public void setUuidTest(UUID uuidTest) {
        this.uuidTest = uuidTest;
    }

    public Byte getByteTest() {
        return byteTest;
    }

    public void setByteTest(Byte byteTest) {
        this.byteTest = byteTest;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

//    public Class getClassTest() {
//        return classTest;
//    }
//
//    public void setClassTest(Class classTest) {
//        this.classTest = classTest;
//    }

    public Float getFloatTest() {
        return floatTest;
    }

    public void setFloatTest(Float floatTest) {
        this.floatTest = floatTest;
    }

    public Short getShortTest() {
        return shortTest;
    }

    public void setShortTest(Short shortTest) {
        this.shortTest = shortTest;
    }

    public BigInteger getBigInt() {
        return bigInt;
    }

    public void setBigInt(BigInteger bigInt) {
        this.bigInt = bigInt;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

//    public Blob getBlob() {
//        return blob;
//    }
//
//    public void setBlob(Blob blob) {
//        this.blob = blob;
//    }
//
//    public Clob getClob() {
//        return clob;
//    }
//
//    public void setClob(Clob clob) {
//        this.clob = clob;
//    }
//
//    public NClob getNclob() {
//        return nclob;
//    }
//
//    public void setNclob(NClob nclob) {
//        this.nclob = nclob;
//    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public OffsetTime getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(OffsetTime offsetTime) {
        this.offsetTime = offsetTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isBoolPrimitive() {
        return boolPrimitive;
    }

    public void setBoolPrimitive(boolean boolPrimitive) {
        this.boolPrimitive = boolPrimitive;
    }

    public byte getBytePrimitive() {
        return bytePrimitive;
    }

    public void setBytePrimitive(byte bytePrimitive) {
        this.bytePrimitive = bytePrimitive;
    }

    public char getCharPrimitive() {
        return charPrimitive;
    }

    public void setCharPrimitive(char charPrimitive) {
        this.charPrimitive = charPrimitive;
    }

    public short getShortPrimitive() {
        return shortPrimitive;
    }

    public void setShortPrimitive(short shortPrimitive) {
        this.shortPrimitive = shortPrimitive;
    }
//
//    public Byte[] getByteArray() {
//        return byteArray;
//    }
//
//    public void setByteArray(Byte[] byteArray) {
//        this.byteArray = byteArray;
//    }
//
//    public Character[] getCharArray() {
//        return charArray;
//    }
//
//    public void setCharArray(Character[] charArray) {
//        this.charArray = charArray;
//    }
//
//    public byte[] getBytePrimitiveArray() {
//        return bytePrimitiveArray;
//    }
//
//    public void setBytePrimitiveArray(byte[] bytePrimitiveArray) {
//        this.bytePrimitiveArray = bytePrimitiveArray;
//    }
//
//    public char[] getCharPrimitiveArray() {
//        return charPrimitiveArray;
//    }
//
//    public void setCharPrimitiveArray(char[] charPrimitiveArray) {
//        this.charPrimitiveArray = charPrimitiveArray;
//    }
}
