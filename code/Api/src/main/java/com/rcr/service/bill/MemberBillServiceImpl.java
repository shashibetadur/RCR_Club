package com.rcr.service.bill;

import com.rcr.domain.Bill;
import com.rcr.domain.BillSearchCriteria;
import com.rcr.domain.member.Member;
import com.rcr.domain.member.MemberSearchCriteria;
import com.rcr.repository.bill.BillRepository;
import com.rcr.repository.member.MemberRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberBillServiceImpl implements MemberBillService {

    private BillRepository billRepository;
    private MemberRepository memberRepository;

    @Autowired
    public MemberBillServiceImpl(BillRepository billRepository, MemberRepository memberRepository) {
        this.billRepository = billRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void saveOrder(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public Bill retrieveOrder(long orderId) {
        return (Bill) billRepository.get(orderId);
    }

    @Override
    public List<Bill> search(BillSearchCriteria billSearchCriteria) {
        List<Bill> orderList = new ArrayList<Bill>();
        if (billSearchCriteria.hasBillId()) {
            Bill order = retrieveOrder(Long.valueOf(billSearchCriteria.getBillId()));
            if (order != null) orderList.add(order);
            return orderList;
        } else {
            List<Long> personIds = new ArrayList<Long>();
            List<Member> memberList = new ArrayList<Member>();
            MemberSearchCriteria memberSearchCriteria = new MemberSearchCriteria(billSearchCriteria.getPhone(), billSearchCriteria.getFirstName(),
                    billSearchCriteria.getLastName());
            if(billSearchCriteria.hasSearchMemberId()){
                memberSearchCriteria.setMemberId(billSearchCriteria.getSearchMemberId());
            }
            if (memberSearchCriteria.hasPhoneCriteria() || memberSearchCriteria.hasNameCriteria()) {
                personIds = fetchCustomerIdsMatchingCriteria(memberSearchCriteria);
                // if no customers found - for whom orders need to be looked up
                if (personIds.isEmpty()) return orderList;
            }
            if (billSearchCriteria.hasMemberId()){
                personIds.add(billSearchCriteria.getMemberId());
            }
            for (Long personId : personIds) {
                memberList.add(memberRepository.get(personId));
            }
            List<Bill> memberBills = billRepository.findBy(billSearchCriteria, memberList);
            enrichBills(memberBills);
            orderList.addAll(memberBills);
            return orderList;
        }
    }

    private List<Long> fetchCustomerIdsMatchingCriteria(MemberSearchCriteria memberSearchCriteria) {
        List<Long> memberIds;
        List<Member> members = memberRepository.findBy(memberSearchCriteria);
        memberIds = (List<Long>) CollectionUtils.collect(members, new Transformer() {
            @Override
            public Object transform(Object input) {
                Member member = (Member) input;
                return Long.valueOf(member.getId());
            }
        });
        return memberIds;
    }

    private void enrichBills(List<Bill> memberBills) {
        for (Bill bill : memberBills) {
            if (bill.getMember().getId() > 0) {
                Member member = memberRepository.get(bill.getMember().getId());
                bill.setMember(member);
            }
        }
    }
}
