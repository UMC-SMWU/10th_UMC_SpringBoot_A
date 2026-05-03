package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionItem(
            Long missionId,
            String title,
            String content,
            Integer reward,
            String status
    ) {}

    @Builder
    public record MissionList(
            List<MissionItem> missions,
            int page,
            int size,
            int totalPages,
            long totalElements
    ) {}

    @Builder
    public record MissionDetail(
            Long missionId,
            String title,
            String content,
            Integer reward,
            String status,
            String createdAt,
            String deadline
    ) {}
}