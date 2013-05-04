package com.rcr.service.member;

import com.rcr.domain.account.AccountTransaction;
import com.rcr.domain.account.MembershipPayment;
import com.rcr.domain.member.MembershipDetail;
import com.rcr.domain.member.MembershipDetails;
import com.rcr.domain.member.MembershipType;
import com.rcr.repository.account.AccountTransactionRepository;
import com.rcr.repository.member.MembershipDetailRepository;
import com.rcr.repository.member.MembershipTypeRepository;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    private MembershipTypeRepository membershipTypeRepository;
    private MembershipDetailRepository membershipDetailRepository;
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public MembershipServiceImpl(MembershipTypeRepository membershipTypeRepository, MembershipDetailRepository membershipDetailRepository, AccountTransactionRepository accountTransactionRepository) {
        this.membershipTypeRepository = membershipTypeRepository;
        this.membershipDetailRepository = membershipDetailRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    public List<MembershipType> lisMembershipTypes() {
        return membershipTypeRepository.getAllActive();
    }

    @Override
    public MembershipType getMembershipTypeDetails(long id) {
        return membershipTypeRepository.get(id);
    }

    @Override
    public void saveMembershipTypeDetails(MembershipType membershipType) {
        membershipTypeRepository.save(membershipType);
    }

    @Override
    public void deleteMembershipTypeDetails(MembershipType membershipType) {
        MembershipType membershipTypeDb = membershipTypeRepository.get(membershipType.getId());
        membershipTypeDb.setDeleted(true);
        membershipTypeRepository.save(membershipTypeDb);
    }

    @Override
    public MembershipDetails getMembershipDetails(long memberId) {
        List<MembershipDetail> membershipDetails = membershipDetailRepository.findMembershipDetailsByMemberId(memberId);
        return new MembershipDetails(membershipDetails);
    }

    @Override
    public void renewMembership(MembershipDetail membershipDetail) {
        membershipDetailRepository.save(membershipDetail);
    }

    @Override
    public MembershipDetail getMembershipDetail(long id) {
        return membershipDetailRepository.get(id);
    }

    @Override
    public Date getRenewalDate(long memberId) {
        MembershipDetails membershipDetails = getMembershipDetails(memberId);
        if (membershipDetails.getMembershipDetailList().isEmpty()) return new Date(System.currentTimeMillis());
        Date renewalDate = membershipDetails.getCurrentMembership().getEndDate();
        Calendar instance = Calendar.getInstance();
        instance.setTime(renewalDate);
        instance.add(Calendar.DATE, 1);
        return instance.getTime();
    }

    @Override
    public List<MembershipPayment> getMembershipPaymentDetails(long memberId) {
        List<AccountTransaction> paymentTransactionDetails = accountTransactionRepository.getMemberPaymentTransactionDetails(memberId);
        final List<MembershipPayment> membershipPayments = new ArrayList<MembershipPayment>();
        CollectionUtils.forAllDo(paymentTransactionDetails, new Closure() {
            @Override
            public void execute(Object input) {
                AccountTransaction accountTransaction = (AccountTransaction) input;
                membershipPayments.add((MembershipPayment) accountTransaction.buildPayment());
            }
        });
        return membershipPayments;
    }
}
