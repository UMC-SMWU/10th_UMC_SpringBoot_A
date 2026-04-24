package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.MemberTermRepository;
import com.example.umc10th.domain.member.repository.TermRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OnboardingService{

    private final MemberRepository memberRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;

    public AuthResDTO.OnboardingResult onboarding(Long memberId, AuthReqDTO.Onboarding dto){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원번호 입니다."));

        // 1. member 정보 업데이트
        member.updateOnboardingInfo(
                dto.name(),
                dto.gender(),
                dto.birth(),
                dto.address()
        );

        // 2. 약관 저장
        for (Long termId : dto.agreedTermIds()) {
            Term term = termRepository.findById(termId)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 약관입니다."));

            MemberTerm memberTerm = MemberTerm.builder()
                    .member(member)
                    .term(term)
                    .build();

            memberTermRepository.save(memberTerm);
        }

        // 3. 음식 저장
        for (Long foodId : dto.preferredFoodCategoryIds()) {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 음식 카테고리입니다."));

            MemberFood memberFood = MemberFood.builder()
                    .member(member)
                    .food(food)
                    .build();

            memberFoodRepository.save(memberFood);
        }

        return new AuthResDTO.OnboardingResult(
                member.getId(),
                member.getName()
        );
    }
}