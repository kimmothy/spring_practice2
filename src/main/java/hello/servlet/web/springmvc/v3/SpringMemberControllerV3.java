package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository repository = MemberRepository.getInstance();

    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("username") int age,
            Model model
    ) {
        Member member = new Member(username, age);
        repository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping("/")
    public String members(Model model){
        List<Member> members = repository.findAll();
        model.addAttribute("members", members);

        return "members";
    }

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }
}
