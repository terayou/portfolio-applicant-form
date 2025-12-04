package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;


@Controller
public class FormController {

    @Autowired
    private FormRepository formRepository;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String ShowLogin() {
        return "login";
    } 
    
    @GetMapping("/newRegistration")
    public String ShownewRegistration() {
        return "newRegistration";
    } 
    
    @GetMapping("/forgetpassword")
    public String ShowforgetPassword() {
        return "forgetpassword";
    } 
    
    @GetMapping("/form")
    public String ShowForm() {
        return "form";
    }
    
    //応募者一覧
    @GetMapping("/list")
    public String ShowList(Model model) {
    	List<FormEntity> list = formRepository.findAll();
    	model.addAttribute("list", list);
    	return "list";
    }
    
    //詳細ページへ
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {

        Optional<FormEntity> applicantOpt = formRepository.findById(id);

        if (applicantOpt.isPresent()) {
            model.addAttribute("applicant", applicantOpt.get());
            return "detail";
        } else {
            return "redirect:/list";  // いなければ一覧へ戻る
        }
    }
    
    
    @PostMapping("/login")
    public String LoginForm(@RequestParam String username,
    						@RequestParam String password,
    						Model model) {
    	
    	Optional<FormEntity> founduser = formRepository.findByUsernameAndPassword(username, password);

    	if(founduser.isPresent()) {
    		FormEntity user = founduser.get();
    		
    		if("admin".equals(user.getRole())) {
    			//管理者のパスなら管理者メニューに移行
    			return "admin";
    		}else if("user".equals(user.getRole())){
    			//応募者のパスなら応募フォームに移行
    			return "form";
    		}else {
        		return "login";
    		}	
    	}
    	model.addAttribute("error", "ユーザー名かパスワードが違います");
        return "login";
    }
    
    @PostMapping("/newRegistration")
    public String NewRegistrationForm(@RequestParam String username,
									  @RequestParam String password,
									  Model model) {
    	
    	// ユーザー名が既に存在するか確認
        if(formRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "このユーザー名若しくはパスワードは既に使われています");
            return "newRegistration";  // 元の登録ページに戻す
        }
    	
    	FormEntity user = new FormEntity();
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setRole("user");
    	formRepository.save(user);
    	return "registrationComplete";
    }
    
    @PostMapping("/forgetpassword")
    public String ForgetpasswordForm(@RequestParam String username,
									  Model model) {
    	Optional<FormEntity> founduser = formRepository.findByUsername(username);
    	
    	if(founduser.isPresent()) {
    		FormEntity user = founduser.get();
    		//メールアドレスに再設定のメールを飛ばす
    		try {
    			emailService.sendSimpleMessage(user.getUsername(), "パスワード再設定", 
    										  "パスワードを再設定してください。リンク: http://localhost:8080/reset"
						  					  );
                model.addAttribute("message", "パスワード再設定メールを送信しました");
            } catch (Exception e) {
            	e.printStackTrace();
                model.addAttribute("error", "メール送信失敗しました: " + e.getMessage());
            }
    		
    	}else {
    		model.addAttribute("error", "ユーザー名が存在しません");
    	}
    	return "forgetpassword";
    }
    
    @PostMapping("/submit")
    public String submitForm(@RequestParam String name,
							@RequestParam int year,
							@RequestParam int month,
							@RequestParam int day,
							@RequestParam int age,
							@RequestParam String email,
							@RequestParam String callnumber,
							@RequestParam String live,
							@RequestParam String finalbackground,
							@RequestParam String skills,
							@RequestParam String job,
							@RequestParam String PR,
							@RequestParam String motivation,	
							Model model) {

        FormEntity entity = new FormEntity();
        entity.setName(name);
        entity.setYear(year);
        entity.setMonth(month);
        entity.setDay(day);
        entity.setAge(age);
        entity.setEmail(email);
        entity.setCallnumber(callnumber);
        entity.setLive(live);
        entity.setFinalbackground(finalbackground);
        entity.setSkills(skills);
        entity.setJob(job);
        entity.setPR(PR);
        entity.setMotivation(motivation);

        formRepository.save(entity);

        // 全体をまとめて1つのオブジェクトで渡す
        model.addAttribute("formData", entity);

        return "index";
    }
    
    @PostMapping("/updateScore")
    public String updateScore(@RequestParam("id") Long id,
                              @RequestParam("score") Integer score) {
        Optional<FormEntity> applicantOpt = formRepository.findById(id);
        if(applicantOpt.isPresent()) {
            FormEntity applicant = applicantOpt.get();
            applicant.setScore(score);
            formRepository.save(applicant);
        }
        return "redirect:/detail/" + id; // 保存後に一覧に戻る
    }
    
    @PostMapping("/list")
    public String SearchList(@RequestParam String keyword, Model model) {

        List<FormEntity> list;

        if (keyword == null || keyword.isEmpty()) {
            list = formRepository.findAll();  // 検索ワードなし → 全件表示
        } else {
            list = formRepository.findByNameContaining(keyword);
        }

        model.addAttribute("list", list);
        model.addAttribute("keyword", keyword);  // 入力保持
        return "list";
    }    

}