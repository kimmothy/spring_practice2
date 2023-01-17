package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository repository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String userName = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(userName, age);

        repository.save(member);

        ModelView view = new ModelView("save-result");
        //Model에 데이터를 보관한다.
        Map<String, Object> model = view.getModel();
        model.put("member", member);

        // 넘기기
        return view;
    }
}
