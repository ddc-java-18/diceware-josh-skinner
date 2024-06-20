package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.PassphraseService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/passphrases")
@Validated
public class PassphraseController {

  private final PassphraseService passphraseService;

  @Autowired
  public PassphraseController(PassphraseService passphraseService) {
    this.passphraseService = passphraseService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> get(
      @RequestParam(name = "length", required = false, defaultValue = "6")
      @Min(0)
      @Max(20)
      int numWords) {
    return passphraseService.generate(numWords);
  }
  @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
  public String get(
      Model model,
      @RequestParam(name = "length", required = false, defaultValue = "6")
      @Min(0)
      @Max(20)
      int numWords) {
    model.addAttribute("words", get(numWords));
    return "passphrases";

  }

}
