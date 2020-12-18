package com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.proxy.data.jpa.domain;

import com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.client.message.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@Entity
@Table(name = LunarCalendarDate.TABLE_NAME)
@Setter
@Getter
@Slf4j
public class LunarCalendarDate {

    public static final String TABLE_NAME = "lunar_calendar_date";

    // -----------------------------------------------------------------------------------------------------------------
    public static LunarCalendarDate from(final Response.Body.Item item) {
        requireNonNull(item, "item is null");
        final LunarCalendarDate instance = new LunarCalendarDate();
        instance.setSolarDate(item.getSolarDate());
        instance.setLunarDate(item.getLunarDate());
        instance.setSecha(item.getLunSecha());
        instance.setWolgeon(item.getLunWolgeon());
        instance.setIljin(item.getLunIljin());
        return instance;
    }


    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "solarDate=" + solarDate
               + ",lunarDate=" + lunarDate
               + ",secha=" + secha
               + ",세차=" + get세차()
               + ",歲次=" + get歲次()
               + ",wolgeon=" + wolgeon
               + ",월건=" + get월건()
               + ",月建=" + get月建()
               + ",leapMonth=" + isLeapMonth()
               + ",iljin=" + iljin
               + ",일진=" + get일진()
               + ",日辰=" + get日辰()
               + '}';
    }

    // ------------------------------------------------------------------------------------------------- secha / 세차 / 歲次
    public String getSecha() {
        return secha;
    }

    public void setSecha(final String secha) {
        this.secha = secha;
    }

    public String get세차() {
        return ofNullable(getSecha()).map(v -> v.substring(0, 2)).orElse(null);
    }

    public String get歲次() {
        return ofNullable(getSecha()).map(v -> v.substring(3, 5)).orElse(null);
    }

    // ----------------------------------------------------------------------------------------------- wolgeon / 월건 / 月建
    public String getWolgeon() {
        return wolgeon;
    }

    public void setWolgeon(final String wolgeon) {
        this.wolgeon = wolgeon;
    }

    public String get월건() {
        return ofNullable(getWolgeon()).map(v -> v.substring(0, 2)).orElse(null);
    }

    public String get月建() {
        return ofNullable(getWolgeon()).map(v -> v.substring(3, 5)).orElse(null);
    }

    public boolean isLeapMonth() {
        return getWolgeon() == null;
    }

    // ------------------------------------------------------------------------------------------------- iljin / 일진 / 日辰
    public String getIljin() {
        return iljin;
    }

    public void setIljin(final String iljin) {
        this.iljin = iljin;
    }

    public String get일진() {
        return ofNullable(getIljin()).map(v -> v.substring(0, 2)).orElse(null);
    }

    public String get日辰() {
        return ofNullable(getIljin()).map(v -> v.substring(3, 5)).orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Basic(optional = false)
    @Column(name = "solar_date", nullable = false, insertable = true, updatable = false)
    private LocalDate solarDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = "lunar_date", nullable = false, insertable = true, updatable = false, unique = true)
    private LocalDate lunarDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(min = 6, max = 6)
    @NotNull
    @Basic(optional = false)
    @Column(name = "secha", nullable = false, insertable = true, updatable = false)
    private String secha;

    @Size(min = 6, max = 6)
    @Nullable
    @Basic(optional = true)
    @Column(name = "wolgeon", nullable = true, insertable = true, updatable = false)
    private String wolgeon;

    @Size(min = 6, max = 6)
    @NotNull
    @Basic(optional = false)
    @Column(name = "iljin", nullable = false, insertable = true, updatable = false)
    private String iljin;
}
