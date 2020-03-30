package com.kraken.runtime.backend.docker;

import com.kraken.runtime.entity.task.ContainerStatus;
import com.kraken.runtime.entity.task.FlatContainer;
import com.kraken.runtime.entity.task.TaskType;
import com.kraken.runtime.backend.api.EnvironmentLabels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
final class StringToFlatContainer implements Function<String, FlatContainer> {

  public static String FORMAT = String.format("{{.ID}};{{.Names}};{{.CreatedAt}};{{.Label \"%s\"}};{{.Label \"%s\"}};{{.Label \"%s\"}};{{.Label \"%s\"}};{{.Label \"%s\"}};{{.Label \"%s\"}};{{.Label \"%s\"}};{{.Label \"%s\"}}",
      EnvironmentLabels.COM_KRAKEN_TASKID,
      EnvironmentLabels.COM_KRAKEN_TASKTYPE,
      EnvironmentLabels.COM_KRAKEN_CONTAINER_NAME,
      EnvironmentLabels.COM_KRAKEN_HOSTID,
      EnvironmentLabels.COM_KRAKEN_EXPECTED_COUNT,
      EnvironmentLabels.COM_KRAKEN_LABEL,
      EnvironmentLabels.COM_KRAKEN_APPLICATION_ID,
      EnvironmentLabels.COM_KRAKEN_DESCRIPTION);
  private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z z";

  @Override
  public FlatContainer apply(final String str) {
    final var split = str.split("[;]", 11);
    final var id = split[0];
    final var status = split[1];
    final var dateStr = split[2];
    final var taskId = split[3];
    final var taskType = split[4];
    final var name = split[5];
    final var hostId = split[6];
    final var expectedCount = split[7];
    final var label = split[8];
    final var applicationId = split[9];
    final var description = split[10];

    var date = new Date().getTime();
    try {
      date = new SimpleDateFormat(DATE_FORMAT).parse(dateStr).getTime();
    } catch (ParseException e) {
      log.error("Failed to parse container date", e);
    }

    return FlatContainer.builder()
        .id(id)
        .hostId(hostId)
        .taskId(taskId)
        .taskType(TaskType.valueOf(taskType))
        .label(label)
        .name(name)
        .description(description)
        .startDate(date)
        .status(ContainerStatus.parse(status))
        .expectedCount(Integer.valueOf(expectedCount))
        .applicationId(applicationId)
        .build();
  }
}
