package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.umc10th.domain.member.enums.Gender;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final BCryptPasswordEncoder passwordEncoder; // ✅ 추가

    // 회원가입: 실제 DB 저장 + BCrypt 암호화
    @Transactional
    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {

        // 이메일 중복 체크
        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new ProjectException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        // 비밀번호 BCrypt 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // String → LocalDate 변환 (YYYY-MM-DD)
        LocalDate birthDate = LocalDate.parse(request.birthday_Date());

        // String → Gender enum 변환
        Gender gender = switch (request.gender()) {
            case "남" -> Gender.MALE;
            case "여" -> Gender.FEMALE;
            default  -> Gender.NONE;
        };

        Member member = Member.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .gender(gender)
                .birthDate(birthDate)
                .address(request.address())
                .build();

        Member savedMember = memberRepository.save(member);

        return MemberResDTO.SignUp.builder()
                .memberId(savedMember.getId())
                .name(savedMember.getName())
                .email(savedMember.getEmail())
                .gender(savedMember.getGender().name())
                .birthday_Date(savedMember.getBirthDate().toString())
                .phoneNumber(request.phoneNumber()) // Member 엔티티에 없으므로 요청값 그대로
                .build();
    }

    public MemberResDTO.HomeInfo getInfo() {
        return MemberResDTO.HomeInfo.builder()
                .memberId(1L)
                .name("세영")
                .email("im3zero@email.com")
                .reviews(List.of())
                .build();
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        // Spring Security formLogin 필터가 처리하므로 이 메서드는 사용 안 함
        return MemberResDTO.Login.builder()
                .email("im3zero@email.com")
                .build();
    }

    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toMyPageDTO(member);
    }

    public MemberResDTO.MissionPageResponse getChallengingMissions(
            MemberReqDTO.MissionPageRequest request, Integer page) {

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<MemberMission> result = memberMissionRepository
                .findByMemberIdAndStatus(request.memberId(), MissionStatus.CHALLENGING, pageRequest);
        return MemberConverter.toMissionPageResponse(result);
    }

    public MemberResDTO.ReviewPageResponse getMyReviews(
            Long memberId, String cursor, Integer pageSize, String sortBy) {

        List<Review> reviews;
        boolean isSortByScore = "star".equals(sortBy);

        if (cursor == null || cursor.equals("-1")) {
            reviews = isSortByScore
                    ? reviewRepository.findByMemberIdOrderByScoreDesc(memberId, PageRequest.of(0, pageSize + 1))
                    : reviewRepository.findByMemberIdOrderByIdDesc(memberId, PageRequest.of(0, pageSize + 1));
        } else {
            reviews = isSortByScore
                    ? reviewRepository.findByMemberIdAndScoreLessThanOrderByScoreDesc(memberId, Integer.parseInt(cursor), PageRequest.of(0, pageSize + 1))
                    : reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, Long.parseLong(cursor), PageRequest.of(0, pageSize + 1));
        }

        return MemberConverter.toReviewPageResponse(reviews, pageSize);
    }
}