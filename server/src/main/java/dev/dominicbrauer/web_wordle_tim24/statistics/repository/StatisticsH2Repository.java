package dev.dominicbrauer.web_wordle_tim24.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dominicbrauer.web_wordle_tim24.statistics.model.StatisticsEntity;

public interface StatisticsH2Repository extends JpaRepository<StatisticsEntity, Long> {

}
