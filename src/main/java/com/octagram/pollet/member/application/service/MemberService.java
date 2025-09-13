package com.octagram.pollet.member.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octagram.pollet.global.exception.BusinessException;
import com.octagram.pollet.member.domain.model.Member;
import com.octagram.pollet.member.domain.model.Point;
import com.octagram.pollet.member.domain.model.PointHistory;
import com.octagram.pollet.member.domain.model.MemberTag;
import com.octagram.pollet.member.domain.model.type.Role;
import com.octagram.pollet.member.domain.model.type.TransactionType;
import com.octagram.pollet.member.domain.repository.MemberRepository;
import com.octagram.pollet.member.domain.repository.PointHistoryRepository;
import com.octagram.pollet.member.domain.repository.PointRepository;
import com.octagram.pollet.member.domain.repository.MemberTagRepository;
import com.octagram.pollet.member.domain.status.MemberErrorCode;
import com.octagram.pollet.member.presentation.dto.request.MemberUpdateRequest;
import com.octagram.pollet.survey.domain.model.Survey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final MemberTagRepository memberTagRepository;
	private final PointRepository pointRepository;
	private final PointHistoryRepository pointHistoryRepository;

	@Transactional
	public void updateMember(String memberId, MemberUpdateRequest request) {
		Member member = findByMemberId(memberId);

		if (memberRepository.existsByNicknameAndIdNot(request.nickname(), member.getId())) {
			throw new BusinessException(MemberErrorCode.DUPLICATE_NICKNAME);
		}

		member.updateInfo(
			request.nickname().trim(),
			request.gender().trim(),
			request.yearOfBirth().trim(),
			request.job().trim(),
			request.phoneNumber().trim(),
			Role.MEMBER
		);
	}

	@Transactional(readOnly = true)
	public Member findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public List<MemberTag> findMemberTags(String memberId) {
		Member member = findByMemberId(memberId);
		return memberTagRepository.findByMemberId(member.getId());
	}

	@Transactional
	public void surveyPointHistory(Member member, Survey survey, long amount) {
		long balance = updatePoint(member, amount);
		saveSurveyPointHistory(member, survey, amount, balance);
	}

	private long updatePoint(Member member, long amount) {
		if (!pointRepository.existsByMember(member)) {
			return savePoint(member, amount);
		}

		Point point = pointRepository.findByMember(member)
			.orElseThrow(() -> new BusinessException(MemberErrorCode.POINT_NOT_FOUND));

		validateBalance(point, amount);
		return point.updateBalance(amount);
	}

	private void validateBalance(Point point, long amount) {
		if (amount < 0 && point.getBalance() < -amount) {
			throw new BusinessException(MemberErrorCode.BALANCE_NOT_ENOUGH);
		}
	}

	private long savePoint(Member member, long amount) {
		Point point = Point.builder()
			.member(member)
			.balance(amount)
			.build();
		pointRepository.save(point);
		return point.getBalance();
	}

	private void saveSurveyPointHistory(Member member, Survey survey, long amount, long balance) {
		PointHistory pointHistory = PointHistory.builder()
			.member(member)
			.survey(survey)
			.transactionType(TransactionType.SURVEY)
			.transactionDescription(survey.getTitle())
			.amount(amount)
			.afterAmount(balance)
			.build();
		pointHistoryRepository.save(pointHistory);
	}
}
