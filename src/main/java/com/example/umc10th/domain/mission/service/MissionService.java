package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    // 내 미션 조회
    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionList getMyMissions(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        List<MemberMission> memberMissions = memberMissionRepository.findByMember(member);

        return  MissionConverter.toMyMissionList(memberMissions);
    }

    //진행중인 미션들 조회
    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionList getMyOngoingMissions(
            MissionReqDTO.MyMissionRequest request
    ) {
        PageRequest pageRequest = PageRequest.of(
                request.page(),
                request.size(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<MemberMission> memberMissionPage =
                memberMissionRepository.findAllByMember_IdAndIsCompleted(
                        request.memberId(),
                        MissionCompleteStatus.ONGOING,
                        pageRequest
                );

        return MissionConverter.toMyMissionList(memberMissionPage);
    }

    // 미션 성공 요청
    public MissionResDTO.MissionCompleteResult completeMission(Long memberMissionId) {

        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원 미션입니다."));

        if (memberMission.getIsCompleted() == MissionCompleteStatus.COMPLETED) {
            throw new RuntimeException("이미 완료된 미션입니다.");
        }

        memberMission.complete();

        return new MissionResDTO.MissionCompleteResult(
                memberMission.getId(),
                memberMission.getIsCompleted()
        );
    }

    //가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
        return null;
    }

    //가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetStoreMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        //정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetStoreNission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
