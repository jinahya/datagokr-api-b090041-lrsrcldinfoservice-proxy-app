package com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.proxy.web.bind;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.client.message.Item;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

import static com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.client.message.Item.MAX_DAY_OF_MONTH_LUNAR;
import static com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.client.message.Item.MIN_DAY_OF_MONTH_LUNAR;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * A class for binding {@code /:response/:body/:item} path.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
public class ItemModel extends RepresentationModel<ItemModel> implements Serializable {

    private static final long serialVersionUID = -4071620406720872635L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A value for representing non-leaping year or month. The value is {@value}.
     *
     * @see #LEAP
     */
    public static final String NORMAL = "\ud3c9";

    /**
     * A value for representing a leaping year or month. The value is {@value}.
     */
    public static final String LEAP = "\uc724";

    private static final String PATTERN_REGEXP_NORMAL_OR_LEAP = '[' + NORMAL + LEAP + ']';

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The formatter for {@code solYear} and {@code lunYear}.
     */
    public static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("uuuu");

    /**
     * The formatter for {@code solMonth} and {@code lunMonth}.
     */
    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("MM");

    /**
     * The formatter for {@code solDay} and {@code lunDay}.
     */
    public static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("dd");

    public static String formatDay(final int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException("invalid dayOfMonth: " + dayOfMonth);
        }
        return String.format("%1$02d", dayOfMonth);
    }

    /**
     * The formatter for {@code solWeek}.
     */
    public static final DateTimeFormatter WEEK_FORMATTER = DateTimeFormatter.ofPattern("E", Locale.KOREAN);

    // -----------------------------------------------------------------------------------------------------------------
    public static final Comparator<ItemModel> COMPARING_IN_LUNAR = Comparator.comparing(ItemModel::getLunarYear)
            .thenComparing(ItemModel::getLunarMonth)
            .thenComparing(ItemModel::getLunarLeapMonth)
            .thenComparing(ItemModel::getLunarDayOfMonth);

    public static final Comparator<ItemModel> COMPARING_IN_SOLAR = Comparator.comparing(ItemModel::getSolarDate);

    // -----------------------------------------------------------------------------------------------------------------
    public static final String REL_YEAR_LUNAR = "yearLunar";

    public static final String REL_MONTH_LUNAR = "monthLunar";

    public static final String REL_DATE_LUNAR = "dateLunar";

    public static final String REL_YEAR_SOLAR = "yearSolar";

    public static final String REL_MONTH_SOLAR = "monthSolar";

    public static final String REL_DATE_SOLAR = "dateSolar";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance on top of specified item.
     *
     * @param item the item on which this model is based.
     */
    public ItemModel(final Item item) {
        super();
        this.item = requireNonNull(item, "item is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + '{'
               + "item=" + item
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        final ItemModel that = (ItemModel) obj;
        return item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), item);
    }

    // ----------------------------------------------------------------------------------------------------------- lunar
    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @NotNull
    public int getLunarYear() {
        return item.getLunYear().getValue();
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @NotNull
    public int getLunarMonth() {
        return item.getLunMonth().getValue();
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @Max(MAX_DAY_OF_MONTH_LUNAR)
    @Min(MIN_DAY_OF_MONTH_LUNAR)
    public int getLunarDayOfMonth() {
        return item.getLunDay();
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    public boolean getLunarLeapMonth() {
        return item.getLunLeapmonth();
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 2)
    @NotNull
    public String getLunarGanzhiForYearKore() {
        return item.getLunSecha().substring(0, 2);
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 2)
    @NotNull
    public String getLunarGanzhiForYearHans() {
        return item.getLunSecha().substring(3, 5);
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 2)
    @Nullable
    public String getLunarGanzhiForMonthKore() {
        return ofNullable(item.getLunWolgeon()).map(s -> s.substring(0, 2)).orElse(null);
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 2)
    @Nullable
    public String getLunarGanzhiForMonthHans() {
        return ofNullable(item.getLunWolgeon()).map(s -> s.substring(3, 5)).orElse(null);
    }

    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 2)
    @NotNull
    public String getLunarGanzhiForDayOfMonthKore() {
        return item.getLunIljin().substring(0, 2);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 2)
    @NotNull
    public String getLunarGanzhiForDayOfMonthHans() {
        return item.getLunIljin().substring(3, 5);
    }

    // ----------------------------------------------------------------------------------------------------------- solar
    @JsonProperty(required = true, access = JsonProperty.Access.READ_ONLY)
    @NotNull
    public LocalDate getSolarDate() {
        return item.getSolarDate();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @JsonIgnore
    @Valid
    @NotNull
    @Getter
    private final Item item;
}
