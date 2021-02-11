package com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.proxy.data.jpa.repository;

import com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.client.message.Item;
import com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.proxy.data.jpa.domain.LunarCalendarDate;
import com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.proxy.data.jpa.domain.LunarCalendarDateMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.YearMonth;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class LunarCalendarDateRepositoryAspectTest {

    @MethodSource("com.github.jinahya.datagokr.api.b090041_.lrsrcldinfoservice.client.message.ResponseResources#items")
    @ParameterizedTest
    void save_(final Item item) {
        final LunarCalendarDate saved1 = lunarCalendarDateRepository.save(lunarCalendarDateMapper.fromItem(item));
        final YearMonth lunarMonthAggregated = current().nextBoolean() ? null : YearMonth.now();
        saved1.setLunarMonthAggregated(lunarMonthAggregated);
        final YearMonth solarMonthAggregated = current().nextBoolean() ? null : YearMonth.now();
        saved1.setSolarMonthAggregated(solarMonthAggregated);
        final LunarCalendarDate saved2 = lunarCalendarDateRepository.save(saved1);
        final LunarCalendarDate saved3 = lunarCalendarDateRepository.save(lunarCalendarDateMapper.fromItem(item));
        assertThat(saved3.getLunarMonthAggregated()).isEqualTo(lunarMonthAggregated);
        assertThat(saved3.getSolarMonthAggregated()).isEqualTo(solarMonthAggregated);
    }

    @Autowired
    private LunarCalendarDateRepository lunarCalendarDateRepository;

    @Autowired
    private LunarCalendarDateMapper lunarCalendarDateMapper;
}
