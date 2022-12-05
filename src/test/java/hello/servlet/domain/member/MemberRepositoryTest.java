package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("chan", 20);
        Member member2 = new Member("sion", 30);
        List<Member> members = new ArrayList<Member>();
        //when
        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);
        members.add(savedMember1);
        members.add(savedMember2);
        //then
        List<Member> members2 = memberRepository.findAll();
        assertThat(members).isEqualTo(members2);
        assertThat(members2).contains(member1, member2);
    }

}
