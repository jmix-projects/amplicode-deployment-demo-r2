package io.jmix2mvp.petclinic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
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

@Table(name = "test_entity")
@Entity
public class TestEntity extends BaseEntity {
    @Column(name = "string")
    private String string;

    @Column(name = "bool")
    private Boolean bool;

    @Column(name = "double_test")
    private Double doubleTest;

    @Column(name = "int_test")
    private Integer intTest;

    @Column(name = "long_test")
    private Long longTest;

    @Column(name = "big_decimal", precision = 19, scale = 2)
    private BigDecimal bigDecimal;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "time_stamp")
    private Timestamp timeStamp;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_util")
    private java.util.Date dateUtil;

    @Column(name = "uuid_test")
    private UUID uuidTest;

    @Column(name = "byte_test")
    private Byte byteTest;

    @Column(name = "character")
    private Character character;

    @Column(name = "class_test")
    private Class classTest;

    @Column(name = "float_test")
    private Float floatTest;

    @Column(name = "short_test")
    private Short shortTest;

    @Column(name = "big_int")
    private BigInteger bigInt;

    @Column(name = "url")
    private URL url;

    @Lob
    @Column(name = "blob")
    private Blob blob;

    @Lob
    @Column(name = "clob")
    private Clob clob;

//    @Lob
//    @Column(name = "nclob")
//    private NClob nclob;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "instant")
    private Instant instant;

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @Column(name = "local_time")
    private LocalTime localTime;

    @Column(name = "offset_date_time")
    private OffsetDateTime offsetDateTime;

    @Column(name = "offset_time")
    private OffsetTime offsetTime;

    @Column(name = "zoned_date_time")
    private ZonedDateTime zonedDateTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "calendar")
    private Calendar calendar;

//    @Column(name = "currency")
//    private Currency currency;

    @Column(name = "locale")
    private Locale locale;

    @Column(name = "bool_primitive")
    private boolean boolPrimitive;

    @Column(name = "byte_primitive")
    private byte bytePrimitive;

    @Column(name = "char_primitive")
    private char charPrimitive;

    @Column(name = "short_primitive")
    private short shortPrimitive;

    @Lob
    @Column(name = "byte_array")
    private Byte[] byteArray;

    @Column(name = "char_array")
    private Character[] charArray;

    @Lob
    @Column(name = "byte_primitive_array")
    private byte[] bytePrimitiveArray;

    @Column(name = "char_primitive_array")
    private char[] charPrimitiveArray;

    public char[] getCharPrimitiveArray() {
        return charPrimitiveArray;
    }

    public void setCharPrimitiveArray(char[] charPrimitiveArray) {
        this.charPrimitiveArray = charPrimitiveArray;
    }

    public byte[] getBytePrimitiveArray() {
        return bytePrimitiveArray;
    }

    public void setBytePrimitiveArray(byte[] bytePrimitiveArray) {
        this.bytePrimitiveArray = bytePrimitiveArray;
    }

    public Character[] getCharArray() {
        return charArray;
    }

    public void setCharArray(Character[] charArray) {
        this.charArray = charArray;
    }

    public Byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(Byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public short getShortPrimitive() {
        return shortPrimitive;
    }

    public void setShortPrimitive(short shortPrimitive) {
        this.shortPrimitive = shortPrimitive;
    }

    public char getCharPrimitive() {
        return charPrimitive;
    }

    public void setCharPrimitive(char charPrimitive) {
        this.charPrimitive = charPrimitive;
    }

    public byte getBytePrimitive() {
        return bytePrimitive;
    }

    public void setBytePrimitive(byte bytePrimitive) {
        this.bytePrimitive = bytePrimitive;
    }

    public boolean getBoolPrimitive() {
        return boolPrimitive;
    }

    public void setBoolPrimitive(boolean boolPrimitive) {
        this.boolPrimitive = boolPrimitive;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

//    public Currency getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(Currency currency) {
//        this.currency = currency;
//    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public OffsetTime getOffsetTime() {
        return offsetTime;
    }

    public void setOffsetTime(OffsetTime offsetTime) {
        this.offsetTime = offsetTime;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

//    public NClob getNclob() {
//        return nclob;
//    }
//
//    public void setNclob(NClob nclob) {
//        this.nclob = nclob;
//    }

    public Clob getClob() {
        return clob;
    }

    public void setClob(Clob clob) {
        this.clob = clob;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public BigInteger getBigInt() {
        return bigInt;
    }

    public void setBigInt(BigInteger bigInt) {
        this.bigInt = bigInt;
    }

    public Short getShortTest() {
        return shortTest;
    }

    public void setShortTest(Short shortTest) {
        this.shortTest = shortTest;
    }

    public Float getFloatTest() {
        return floatTest;
    }

    public void setFloatTest(Float floatTest) {
        this.floatTest = floatTest;
    }

    public Class getClassTest() {
        return classTest;
    }

    public void setClassTest(Class classTest) {
        this.classTest = classTest;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Byte getByteTest() {
        return byteTest;
    }

    public void setByteTest(Byte byteTest) {
        this.byteTest = byteTest;
    }

    public UUID getUuidTest() {
        return uuidTest;
    }

    public void setUuidTest(UUID uuidTest) {
        this.uuidTest = uuidTest;
    }

    public java.util.Date getDateUtil() {
        return dateUtil;
    }

    public void setDateUtil(java.util.Date dateUtil) {
        this.dateUtil = dateUtil;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Long getLongTest() {
        return longTest;
    }

    public void setLongTest(Long longTest) {
        this.longTest = longTest;
    }

    public Integer getIntTest() {
        return intTest;
    }

    public void setIntTest(Integer intTest) {
        this.intTest = intTest;
    }

    public Double getDoubleTest() {
        return doubleTest;
    }

    public void setDoubleTest(Double doubleTest) {
        this.doubleTest = doubleTest;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
